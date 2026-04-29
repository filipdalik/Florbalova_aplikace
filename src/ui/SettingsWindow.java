package ui;

import model.Team;
import model.TeamManager;

import javax.swing.*;
import java.awt.*;

public class SettingsWindow extends JFrame {

    private TeamManager manager = new TeamManager();

    private boolean soundOn = true;

    public SettingsWindow() {

        setTitle("Settings");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1));


        JButton editTeams = new JButton("Edit teams");

        editTeams.addActionListener(e -> {

            if (manager.getTeams().isEmpty()) {
                JOptionPane.showMessageDialog(this, "No teams available.");
                return;
            }

            Team selected = (Team) JOptionPane.showInputDialog(
                    this,
                    "Select team:",
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

        JButton soundButton = new JButton("Sound: ON");

        soundButton.addActionListener(e -> {

            soundOn = !soundOn;
            soundButton.setText(soundOn ? "Sound: ON" : "Sound: OFF");

            System.out.println("Sound enabled: " + soundOn);
        });

        JButton close = new JButton("Close");

        close.addActionListener(e -> {
            dispose();
        });

        add(editTeams);
        add(soundButton);
        add(close);

        setVisible(true);
    }
}