package ui;

import model.*;

import javax.swing.*;
import java.awt.*;

public class StartWindow extends JFrame {

    private TeamManager manager;

    private JComboBox<Team> team1Box;
    private JComboBox<Team> team2Box;

    public StartWindow() {
        manager = new TeamManager();

        setTitle("Start");
        setSize(650, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        team1Box = new JComboBox<>();
        team2Box = new JComboBox<>();

        loadTeams();

        JButton create = new JButton("Create team");
        JButton remove = new JButton("Remove team");
        JButton edit = new JButton("Edit team");
        JButton start = new JButton("Start match");

    }

    private void loadTeams() {
        for (Team t : manager.getTeams()) {
            team1Box.addItem(t);
            team2Box.addItem(t);
        }
    }

    private Team copy(Team original) {
        Team c = new Team(original.getName());

        for (Player p : original.getPlayers()) {
            c.addPlayer(new Player(p.getName(), p.getNumber()));
        }

        return c;
    }
}