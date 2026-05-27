package ui;

import model.Team;
import model.TeamManager;

import javax.swing.*;
import java.awt.*;

public class SettingsWindow extends JFrame {

    private TeamManager manager = new TeamManager();

    public SettingsWindow() {
        setTitle("Settings");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(43, 42, 42));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));
        JLabel titleLabel = new JLabel("SETTINGS", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setBackground(new Color(43, 42, 42));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;

        JButton createTeamButton = new JButton("Create new team");
        styleButton(createTeamButton, new Color(152, 0, 128));
        createTeamButton.addActionListener(e -> {
            String teamName = JOptionPane.showInputDialog(this, "Enter name for the new team:", "Create Team", JOptionPane.PLAIN_MESSAGE);
            if (teamName != null && !teamName.trim().isEmpty()) {
                Team newTeam = new Team(teamName.trim());
                manager.addTeam(newTeam);
                JOptionPane.showMessageDialog(this, "Team '" + teamName + "' successfully created!", "Success", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        buttonPanel.add(createTeamButton, gbc);

        gbc.gridy = 1;
        JButton editTeams = new JButton("Edit teams / Players");
        styleButton(editTeams, new Color(0, 103, 88));
        editTeams.addActionListener(e -> {
            if (manager.getTeams().isEmpty()) {
                JOptionPane.showMessageDialog(this, "No teams available. Create a team first.", "Info", JOptionPane.WARNING_MESSAGE);
                return;
            }
            Team selected = (Team) JOptionPane.showInputDialog(
                    this,
                    "Select team to edit:",
                    "Edit teams",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    manager.getTeams().toArray(),
                    null
            );

            if (selected != null) {
                new TeamEditorWindow(manager, selected);
            }
        });
        buttonPanel.add(editTeams, gbc);

        gbc.gridy = 2;
        JButton close = new JButton("Close");
        styleButton(close, new Color(120, 0, 0));
        close.addActionListener(e -> dispose());
        buttonPanel.add(close, gbc);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);

        setVisible(true);
    }

    private void styleButton(JButton button, Color backgroundColor) {
        button.setFocusPainted(false);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 22));
        button.setBackground(backgroundColor);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setPreferredSize(new Dimension(280, 45));
    }
}