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

        create.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Team name:");
            if (name == null || name.isEmpty()){
                return;
            }
            Team t = new Team(name);
            manager.addTeam(t);
            team1Box.addItem(t);
            team2Box.addItem(t);
        });

        remove.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Team name:");
            if (name == null || name.isEmpty()){
                return;
            }
            manager.removeTeam(name);
        });

        edit.addActionListener(e -> {
            Team t = (Team) team1Box.getSelectedItem();
            if (t != null) {
                new TeamEditorWindow(manager, t);
            }
        });

        start.addActionListener(e -> {
            Team t1 = (Team) team1Box.getSelectedItem();
            Team t2 = (Team) team2Box.getSelectedItem();
            if (t1 == null || t2 == null){
                return;
            }
            if (t1 == t2){
                JOptionPane.showMessageDialog(this, "You can't choose same teams!");
                return;
            }
            Match match = new Match(copy(t1), copy(t2));
            new MainWindow(match);
            dispose();
        });

        add(team1Box);
        add(team2Box);
        JPanel panel = new JPanel(new BorderLayout(100,100));
        panel.add(create, BorderLayout.EAST);
        panel.add(remove, BorderLayout.WEST);
        add(panel);
        add(edit);
        add(start);

        setVisible(true);
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