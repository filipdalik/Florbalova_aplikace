package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TeamEditorWindow extends JFrame {

    private Team team;
    private TeamManager manager;

    private DefaultListModel<Player> model = new DefaultListModel<>();
    private JList<Player> list = new JList<>(model);

    public TeamEditorWindow(TeamManager manager, Team team) {
        this.manager = manager;
        this.team = team;

        setTitle("Team editor - " + team.getName());
        setSize(400, 500);
        setLocationRelativeTo(null);

        load();

        JButton add = new JButton("Add player");
        add.addActionListener(e -> {

            String name = JOptionPane.showInputDialog("Name:");
            String num = JOptionPane.showInputDialog("Number:");

            try {
                Player p = new Player(name, Integer.parseInt(num));
                team.addPlayer(p);

                model.addElement(p);
                manager.addTeam(team);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Write name and number.");
            }
        });
        list.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {

                if (e.getClickCount() == 2) {
                    Player p = list.getSelectedValue();

                    team.removePlayer(p);
                    model.removeElement(p);

                    manager.addTeam(team);
                }
            }
        });
        setLayout(new BorderLayout());
        add(new JScrollPane(list), BorderLayout.CENTER);
        add(add, BorderLayout.SOUTH);

        setVisible(true);
    }
    private void load() {
        model.clear();
        List<Player> players = team.getPlayers();

        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            model.addElement(p);
        }
    }
}