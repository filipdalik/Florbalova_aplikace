package ui;

import model.Player;
import model.Team;
import model.TeamManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StatsWindow extends JFrame {
    private JComboBox<Team> teamComboBox;
    private JTextArea statsArea;

    public StatsWindow() {
        TeamManager teamManager = new TeamManager();
        ArrayList<Team> allTeams = teamManager.getTeams();

        setTitle("Player Statistics");
        setSize(700, 580);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(43, 42, 42));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel northPanel = new JPanel(new GridLayout(2, 1, 0, 10));
        northPanel.setBackground(new Color(43, 42, 42));
        northPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        JLabel titleLabel = new JLabel("PLAYER STATISTICS", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        northPanel.add(titleLabel);

        teamComboBox = new JComboBox<>();
        teamComboBox.setFont(new Font("Segoe UI", Font.BOLD, 16));

        for (int i = 0; i < allTeams.size(); i++) {
            teamComboBox.addItem(allTeams.get(i));
        }

        northPanel.add(teamComboBox);
        mainPanel.add(northPanel, BorderLayout.NORTH);

        statsArea = new JTextArea();
        statsArea.setEditable(false);
        statsArea.setBackground(new Color(30, 30, 30));
        statsArea.setForeground(Color.WHITE);
        statsArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        statsArea.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JScrollPane scrollPane = new JScrollPane(statsArea);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(234, 141, 0), 2));
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(new Color(43, 42, 42));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));

        JButton closeButton = new JButton("CLOSE");
        closeButton.setFocusPainted(false);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(new Font("Segoe UI", Font.BOLD, 20));
        closeButton.setBackground(new Color(120, 0, 0));
        closeButton.setPreferredSize(new Dimension(150, 40));
        closeButton.setBorder(BorderFactory.createEmptyBorder());
        closeButton.addActionListener(e -> dispose());
        bottomPanel.add(closeButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        teamComboBox.addActionListener(e -> updateStatsText());

        updateStatsText();

        add(mainPanel);
        setVisible(true);
    }

    private void updateStatsText() {
        Team selectedTeam = (Team) teamComboBox.getSelectedItem();

        if (selectedTeam == null) {
            statsArea.setText("No team found. Please create teams in Settings first.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("TEAM: ").append(selectedTeam.getName().toUpperCase()).append("\n");
        sb.append(String.format("%-6s %-22s %-8s %-10s %-6s\n", "Numb.", "Name", "Goals", "Assists", "Points"));
        sb.append("-".repeat(56)).append("\n");

        ArrayList<Player> players = selectedTeam.getPlayers();
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            sb.append(String.format("#%-5d %-22s %-8d %-10d %-6d\n",
                    p.getNumber(),
                    p.getName(),
                    p.getGoals(),
                    p.getAssists(),
                    p.getPoints()
            ));
        }

        statsArea.setText(sb.toString());
        statsArea.setCaretPosition(0);
    }
}