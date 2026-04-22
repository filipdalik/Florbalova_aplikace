package ui;

import model.Match;

import javax.swing.*;

public class EventsWindow extends JFrame {
    public EventsWindow(Match match) {
        setTitle("EVENT");
        setSize(550, 1000);
        setLocationRelativeTo(null);
        JTextArea area = new JTextArea();
        area.setEditable(false);

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < match.getEvents().size(); i++) {
            sb.append(match.getEvents().get(i)).append("\n");
        }

        area.setText(sb.toString());

        add(new JScrollPane(area));

        setVisible(true);
    }
}
