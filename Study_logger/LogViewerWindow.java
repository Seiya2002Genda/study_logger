package Study_logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LogViewerWindow extends JFrame {
    private final String LOG_FILENAME = "study_log.csv";

    public LogViewerWindow() {
        setTitle("📂 勉強ログ一覧");
        setSize(800, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        String[] columns = {"日付", "科目", "コメント", "勉強時間", "休憩時間"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        try (BufferedReader reader = new BufferedReader(new FileReader(LOG_FILENAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1); // カンマ分割（コメント内カンマ対応）
                for (int i = 0; i < fields.length; i++) {
                    fields[i] = fields[i].replace("\"", "").trim(); // 引用符除去
                }
                model.addRow(fields);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "CSV読み込み失敗:\n" + e.getMessage(), "エラー", JOptionPane.ERROR_MESSAGE);
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
    }
}

