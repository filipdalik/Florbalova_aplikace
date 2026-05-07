package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

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
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        team1Box = new JComboBox<>();
        team2Box = new JComboBox<>();
        loadTeams();

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10, 10));
        JButton create = new JButton("Create team");
        JButton remove = new JButton("Remove team");
        JButton edit = new JButton("Edit team");
        JButton start = new JButton("Start match");
        JPanel startPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        start.setPreferredSize(new Dimension(150, 40));


        buttonsPanel.add(create);
        buttonsPanel.add(remove);
        buttonsPanel.add(edit);
        startPanel.add(start);

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
            Team selected = (Team) team1Box.getSelectedItem();
            if (selected != null){
                int confirm = JOptionPane.showConfirmDialog(this, "Really delete " + selected.getName() + "?");
                if (confirm == JOptionPane.YES_OPTION){
                    manager.removeTeam(selected.getName());
                    team1Box.removeItem(selected);
                    team2Box.removeItem(selected);
                }
            }else {
                JOptionPane.showMessageDialog(this, "No team selected");
            }
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
                JOptionPane.showMessageDialog(this, "No team selected");
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

        add(Box.createVerticalStrut(20));
        add(new JLabel("  Select Team 1:"));
        add(team1Box);
        add(Box.createVerticalStrut(10));
        add(new JLabel("  Select Team 2:"));
        add(team2Box);
        add(Box.createVerticalStrut(20));
        add(buttonsPanel);
        add(Box.createVerticalStrut(20));
        add(startPanel);
        add(Box.createVerticalStrut(20));

        setVisible(true);
    }

    private void loadTeams() {
        List<Team> teams = manager.getTeams();
        for (int i = 0; i < teams.size(); i++) {
            Team t = teams.get(i);
            team1Box.addItem(t);
            team2Box.addItem(t);
        }
    }

    private Team copy(Team original) {
        Team c = new Team(original.getName());
        List<Player> players = original.getPlayers();
        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            c.addPlayer(new Player(p.getName(), p.getNumber()));
        }
        return c;
    }
}