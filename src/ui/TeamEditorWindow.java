package ui;

import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Tato třída reprezentuje okno (JFrame) sloužící jako editor daného týmu.
 * Zobrazuje seznam všech hráčů daného týmu v listu¨a umožňuje přidávat/odebírat nové hráče.
 * Odebírání je řešeno pomocí dvojkliku na vybraného hráče.
 */
public class TeamEditorWindow extends JFrame {

    private Team team;
    private TeamManager manager;

    private DefaultListModel<Player> model = new DefaultListModel<>();
    private JList<Player> list = new JList<>(model);

    /**
     * Tato metoda zobrazí okno editoru pro vybraný tým.
     * Inicializuje tlačítka pro přidání hráčů a práci s tím je odebírat pomocí dvojkliku.
     * @param manager
     * @param team
     */
    public TeamEditorWindow(TeamManager manager, Team team) {
        this.manager = manager;
        this.team = team;

        setTitle("Team editor - " + team.getName());
        setSize(400, 500);
        setLocationRelativeTo(null);

        list.setBackground(new Color(30, 30, 30));
        list.setForeground(Color.WHITE);
        list.setFont(new Font("Monospaced", Font.BOLD, 16));
        list.setSelectionBackground(new Color(44, 2, 104));
        list.setSelectionForeground(Color.WHITE);

        load();

        JButton add = new JButton("Add player");
        add.setFocusPainted(false);
        add.setForeground(Color.WHITE);
        add.setFont(new Font("Segoe UI", Font.BOLD, 20));
        add.setBackground(new Color(120, 82, 0));
        add.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        add.addActionListener(e -> {

            String name = JOptionPane.showInputDialog("Name:");
            String num = JOptionPane.showInputDialog("Number:");
            try {
                Player p = new Player(name, Integer.parseInt(num));
                team.addPlayer(p);
                model.addElement(p);
                manager.save();

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

                    manager.save();
                }
            }
        });

        setLayout(new BorderLayout());
        add(new JScrollPane(list), BorderLayout.CENTER);
        add(add, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Pomocná metoda, která vyčistí model a znovu do něj nahraje všechny hráče k editování týmu.
     */
    private void load() {
        model.clear();
        List<Player> players = team.getPlayers();

        for (int i = 0; i < players.size(); i++) {
            Player p = players.get(i);
            model.addElement(p);
        }
    }
}