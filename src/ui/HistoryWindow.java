package ui;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class HistoryWindow extends JFrame {

    public HistoryWindow() {
            setTitle("Match History");
            setSize(700, 550);
            setLocationRelativeTo(null);

            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setResizable(false);

            JPanel mainPanel = new JPanel(new BorderLayout());
            mainPanel.setBackground(new Color(43, 42, 42));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            JLabel titleLabel = new JLabel("MATCH HISTORY", SwingConstants.CENTER);
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
            titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
            mainPanel.add(titleLabel, BorderLayout.NORTH);

            JTextArea historyArea = new JTextArea();
            historyArea.setEditable(false);
            historyArea.setBackground(new Color(30, 30, 30));
            historyArea.setForeground(Color.WHITE);
            historyArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
            historyArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JScrollPane scrollPane = new JScrollPane(historyArea);
            scrollPane.setBorder(BorderFactory.createLineBorder(new Color(234, 141, 0), 2));
            mainPanel.add(scrollPane, BorderLayout.CENTER);

            JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            bottomPanel.setBackground(new Color(43, 42, 42));
            bottomPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

            JButton backButton = new JButton("BACK");
            backButton.setFocusPainted(false);
            backButton.setForeground(Color.WHITE);
            backButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
            backButton.setBackground(new Color(120, 0, 0));
            backButton.setPreferredSize(new Dimension(150, 40));
            backButton.setBorder(BorderFactory.createEmptyBorder());

            backButton.addActionListener(e -> {
                dispose();
            });
            bottomPanel.add(backButton);
            mainPanel.add(bottomPanel, BorderLayout.SOUTH);


            File file = new File("history.txt");
            if (!file.exists()) {
                historyArea.setText("No history found yet.\nMatches will appear here after they are completed.");
            } else {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    StringBuilder content = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        content.append(line).append("\n");
                    }
                    historyArea.setText(content.toString());

                    historyArea.setCaretPosition(0);

                } catch (IOException e) {
                    historyArea.setText("Error while reading history file:\n" + e.getMessage());
                }
            }

            add(mainPanel);
            setVisible(true);
        }
}

