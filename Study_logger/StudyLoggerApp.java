package Study_logger;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class StudyLoggerApp extends JFrame {
    private JTextField dateField, subjectField;
    private JTextArea commentArea;
    private JLabel studyTimerLabel;
    private JLabel breakTimerLabel;
    private Timer studyTimer, breakTimer;
    private int studySeconds = 0;
    private int breakSeconds = 0;

    private final String BACKGROUND_IMAGE_PATH = "Study/pexels-rafael-cosquiere-1059286-2041540.jpg";
    private final String LOG_FILENAME = "study_log.csv";

    public StudyLoggerApp() {
        setTitle("Study Logger");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        Image backgroundImage = new ImageIcon(BACKGROUND_IMAGE_PATH).getImage();
        JPanel backgroundPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };

        backgroundPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("SansSerif", Font.BOLD, 14);
        Color labelColor = Color.WHITE;

        dateField = new JTextField(20);
        subjectField = new JTextField(20);
        commentArea = new JTextArea(5, 20);
        commentArea.setLineWrap(true);

        studyTimerLabel = new JLabel("Study Time: 00:00:00", SwingConstants.CENTER);
        breakTimerLabel = new JLabel("Break Time: 00:00:00", SwingConstants.CENTER);
        studyTimerLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        breakTimerLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        studyTimerLabel.setForeground(Color.WHITE);
        breakTimerLabel.setForeground(Color.WHITE);

        JButton startBtn = new JButton("Start");
        JButton endBtn = new JButton("End");
        JButton breakStartBtn = new JButton("Start Break");
        JButton breakEndBtn = new JButton("End Break");
        JButton viewLogBtn = new JButton("📂 View Logs");

        studyTimer = new Timer(1000, e -> {
            studySeconds++;
            studyTimerLabel.setText("Study Time: " + formatTime(studySeconds));
        });

        breakTimer = new Timer(1000, e -> {
            breakSeconds++;
            breakTimerLabel.setText("Break Time: " + formatTime(breakSeconds));
        });

        startBtn.addActionListener(e -> {
            if (isInputValid()) {
                studySeconds = 0;
                breakSeconds = 0;
                studyTimer.start();
                breakTimer.stop();
            } else {
                JOptionPane.showMessageDialog(this, "Please fill in all fields (Date, Subject, Comment).", "Input Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        endBtn.addActionListener(e -> {
            studyTimer.stop();
            breakTimer.stop();

            String studyTimeStr = formatTime(studySeconds);
            String breakTimeStr = formatTime(breakSeconds);

            saveLog(
                    dateField.getText().trim(),
                    subjectField.getText().trim(),
                    commentArea.getText().trim(),
                    studyTimeStr,
                    breakTimeStr
            );

            JOptionPane.showMessageDialog(this,
                    "🧠 Total Study Time: " + studyTimeStr + "\n☕ Total Break Time: " + breakTimeStr,
                    "Session Complete", JOptionPane.INFORMATION_MESSAGE);
        });

        breakStartBtn.addActionListener(e -> {
            studyTimer.stop();
            breakTimer.start();
        });

        breakEndBtn.addActionListener(e -> {
            breakTimer.stop();
            studyTimer.start();
        });

        viewLogBtn.addActionListener(e -> new LogViewerWindow().setVisible(true));

        addLabeledField(backgroundPanel, gbc, 0, "Date", dateField, labelFont, labelColor);
        addLabeledField(backgroundPanel, gbc, 1, "Subject", subjectField, labelFont, labelColor);

        gbc.gridx = 0; gbc.gridy = 2;
        JLabel commentLabel = new JLabel("Comment");
        commentLabel.setFont(labelFont);
        commentLabel.setForeground(labelColor);
        backgroundPanel.add(commentLabel, gbc);
        gbc.gridx = 1;
        backgroundPanel.add(new JScrollPane(commentArea), gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        backgroundPanel.add(startBtn, gbc);
        gbc.gridx = 1;
        backgroundPanel.add(endBtn, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        backgroundPanel.add(breakStartBtn, gbc);
        gbc.gridx = 1;
        backgroundPanel.add(breakEndBtn, gbc);

        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        backgroundPanel.add(studyTimerLabel, gbc);
        gbc.gridy = 6;
        backgroundPanel.add(breakTimerLabel, gbc);

        gbc.gridy = 7;
        backgroundPanel.add(viewLogBtn, gbc);

        setContentPane(backgroundPanel);
    }

    private boolean isInputValid() {
        return !dateField.getText().trim().isEmpty()
                && !subjectField.getText().trim().isEmpty()
                && !commentArea.getText().trim().isEmpty();
    }

    private void addLabeledField(JPanel panel, GridBagConstraints gbc, int row, String labelText, JTextField textField, Font font, Color color) {
        gbc.gridx = 0;
        gbc.gridy = row;
        JLabel label = new JLabel(labelText);
        label.setFont(font);
        label.setForeground(color);
        panel.add(label, gbc);

        gbc.gridx = 1;
        panel.add(textField, gbc);
    }

    private String formatTime(int totalSeconds) {
        int hrs = totalSeconds / 3600;
        int mins = (totalSeconds % 3600) / 60;
        int secs = totalSeconds % 60;
        return String.format("%02d:%02d:%02d", hrs, mins, secs);
    }

    private void saveLog(String date, String subject, String comment, String studyTime, String breakTime) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILENAME, true))) {
            writer.write(String.format("%s,%s,\"%s\",%s,%s%n", date, subject, comment.replace("\"", "\"\""), studyTime, breakTime));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Failed to save to log file:\n" + e.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudyLoggerApp app = new StudyLoggerApp();
            app.setVisible(true);
        });
    }
}
