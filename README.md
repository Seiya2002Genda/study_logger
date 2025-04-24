# 📚 Study Logger App

A simple Java Swing application to track your study and break times, and log your sessions to a CSV file with a clean library-style interface.  
Built with `Java`, `Swing`, and love for productivity. 🕒

---

## ✨ Features

- ⏱ Track study and break durations in real-time
- 💾 Automatically save each session to `study_log.csv`
- 📂 View all your logs in a sortable, scrollable table
- 🖼 Background image interface (customizable)
- 📋 Fields:
  - **Date**
  - **Subject**
  - **Comment**
  - **Study Time**
  - **Break Time**

---

## 🖥️ Screenshots

<img width="800" alt="Screenshot 0007-04-24 at 13 13 54" src="https://github.com/user-attachments/assets/9b081b6d-4353-4313-80f8-4cd49fa60b19" />

---

## 📁 Project Structure
Study_logger/ 
├── StudyLoggerApp.java # Main application UI 
├── LogViewerWindow.java # Log viewer in JTable format 
├── study_log.csv # Auto-created CSV log file 
└── Study/ 
└── pexels-rafael-cosquiere-1059286-2041540.jpg # Background image

---

## ✅ How to Run

### Requirements:
- Java 8+ (JDK)
- IntelliJ IDEA or any Java IDE

### Steps:
1. Clone or download this repository.
2. Open it in your IDE.
3. Make sure your working directory contains `StudyLoggerApp.java` and the image path matches.
4. Run `StudyLoggerApp.java` to launch the application.

---

## 📝 Sample Log Output (CSV)

csv
2025-04-24,Math,"Reviewing Trigonometry",00:35:00,00:05:30
2025-04-25,Physics,"Mechanics and vectors",01:00:00,00:10:00

## 🚀 Future Plans

📊 Add subject-based bar charts (JFreeChart)
🧾 Export to PDF
🔍 Search/filter by date or subject
🌙 Dark mode support

---

## 📄 License

This software is free to use, modify, and enhance for personal or academic purposes.
All rights including copyright belong to Seiya Genda.
Background image credit: "Photo by Rafael Cosquiere from Pexels"

---

## ✨ Author

Developed by: Seiya Genda
