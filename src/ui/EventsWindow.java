package ui;

import model.Match;

import javax.swing.*;
import java.awt.*;

public class EventsWindow extends JFrame {
    public EventsWindow(Match match) {
        setTitle("EVENT");
        setSize(550, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setBackground(new Color(30, 30, 30));
        area.setForeground(Color.WHITE);
        area.setFont(new Font("Monospaced", Font.PLAIN, 16));
        area.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < match.getEvents().size(); i++) {
            sb.append(match.getEvents().get(i)).append("\n");
        }

        area.setText(sb.toString());

        JScrollPane scrollPane = new JScrollPane(area);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(234, 141, 0), 2));
        getContentPane().setBackground(new Color(43, 42, 42));

        add(new JScrollPane(area));

        setVisible(true);
    }
}
