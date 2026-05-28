package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Tato třída reprezentuje okno (JFrame) sloužící jako předzápasové menu aplikace.
 * Umožňuje uživateli vybrat dva týmy, které proti sobě hrají z rozbalovacích seznamů (JComboBox),
 * Umí také spravovat týmy (vytvořit, smazat a upravit) apoté umí spustit samotný zápas.
 */
public class StartWindow extends JFrame {

    private TeamManager manager;
    private JComboBox<Team> team1Box;
    private JComboBox<Team> team2Box;

    /**
     * Vytvoří a zobrazí předzápasové okno.
     * Inicializuje data pro výběr týmů, načte data z manažera a poté spustí hlavní okno.
     */
    public StartWindow() {
        manager = new TeamManager();

        setTitle("Start");
        setSize(650, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(43, 42, 42));
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        team1Box = new JComboBox<>();
        team2Box = new JComboBox<>();

        team1Box.setBackground(new Color(30, 30, 30));
        team1Box.setForeground(Color.WHITE);
        team1Box.setFont(new Font("Segoe UI", Font.BOLD, 16));
        team1Box.setMaximumSize(new Dimension(400, 40));

        team2Box.setBackground(new Color(30, 30, 30));
        team2Box.setForeground(Color.WHITE);
        team2Box.setFont(new Font("Segoe UI", Font.BOLD, 16));
        team2Box.setMaximumSize(new Dimension(400, 40));
        loadTeams();

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,10, 10));
        buttonsPanel.setBackground(new Color(43, 42, 42));
        JButton create = new JButton("Create team");
        JButton remove = new JButton("Remove team");
        JButton edit = new JButton("Edit team");
        JButton start = new JButton("Start match");
        JPanel startPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        startPanel.setBackground(new Color(43, 42, 42));

        create.setFocusPainted(false);
        create.setForeground(Color.WHITE);
        create.setFont(new Font("Segoe UI", Font.BOLD, 16));
        create.setBackground(new Color(0, 120, 60));
        create.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        remove.setFocusPainted(false);
        remove.setForeground(Color.WHITE);
        remove.setFont(new Font("Segoe UI", Font.BOLD, 16));
        remove.setBackground(new Color(30, 0, 120));
        remove.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        edit.setFocusPainted(false);
        edit.setForeground(Color.WHITE);
        edit.setFont(new Font("Segoe UI", Font.BOLD, 16));
        edit.setBackground(new Color(85, 0, 115));
        edit.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        start.setFocusPainted(false);
        start.setForeground(Color.WHITE);
        start.setFont(new Font("Segoe UI", Font.BOLD, 20));
        start.setBackground(new Color(234, 141, 0));
        start.setBorder(BorderFactory.createEmptyBorder());
        start.setPreferredSize(new Dimension(200, 50));


        buttonsPanel.add(create);
        buttonsPanel.add(remove);
        buttonsPanel.add(edit);
        startPanel.add(start);

        create.addActionListener(e -> {
            String name = JOptionPane.showInputDialog("Team name:");
            if (name == null || name.isEmpty()){
                System.out.println("Team name cannot be empty");
                return;
            }
            for (int i = 0; i < manager.getTeams().size(); i++) {
                if (name.equals(manager.getTeams().get(i).getName())){
                    JOptionPane.showMessageDialog(this, "Team already exists!");
                    System.out.println("Team already exists!");
                    return;
                }
            }
            Team t = new Team(name);
            manager.addTeam(t);
            team1Box.addItem(t);
            team2Box.addItem(t);
            System.out.println("Team added");
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
            if (t1.getPlayers().size() < 6 || t2.getPlayers().size() < 6){
                JOptionPane.showMessageDialog(this, "No players in a team. You need min. 6 players.");
                return;
            }
            Match match = new Match(copy(t1), copy(t2));
            new MainWindow(match);
            dispose();
        });
        JLabel label1 = new JLabel("Select Team 1:");
        label1.setForeground(Color.WHITE);
        label1.setFont(new Font("Segoe UI", Font.BOLD, 18));
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel label2 = new JLabel("Select Team 2:");
        label2.setForeground(Color.WHITE);
        label2.setFont(new Font("Segoe UI", Font.BOLD, 18));
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);

        team1Box.setAlignmentX(Component.CENTER_ALIGNMENT);
        team2Box.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalStrut(40));
        add(label1);
        add(Box.createVerticalStrut(10));
        add(team1Box);
        add(Box.createVerticalStrut(30));
        add(label2);
        add(Box.createVerticalStrut(10));
        add(team2Box);
        add(Box.createVerticalStrut(40));
        add(buttonsPanel);
        add(Box.createVerticalStrut(20));
        add(startPanel);
        add(Box.createVerticalStrut(20));

        setVisible(true);
    }

    /**
     * Tato metoda vyčistí rozbalovací seznamy a znovu do nich načte aktuální seznam týmů z manažera.
     */
    private void loadTeams() {
        team1Box.removeAllItems();
        team2Box.removeAllItems();
        List<Team> teams = manager.getTeams();
        for (int i = 0; i < teams.size(); i++) {
            Team t = teams.get(i);
            team1Box.addItem(t);
            team2Box.addItem(t);
        }
    }

    /**
     * Tato metoda vytvoří  kopii týmu včetně všech jeho hráčů.
     * Slouží k tomu, aby se statistiky ze zápasu nepřepisovaly do evidence týmů.
     * @param original team
     * @return Team
     */
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