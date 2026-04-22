package ui;

import model.Match;
import model.Player;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private JLabel scoreLabel;
    private JLabel timeLabel;
    private Timer timer;
    private boolean running = false;
    private Match match;

    public MainWindow(Match match) {

        this.match = match;

        setTitle("Match");
        setSize(1020, 920);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        scoreLabel = new JLabel("0 : 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Times New Roma", Font.BOLD, 120));

        timeLabel = new JLabel("00:00", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 80));

        JButton goal1Button = new JButton("GOAL FOR TEAM 1");
        JButton goal2Button = new JButton("GOAL FOR TEAM 2");
        JButton stopButton = new JButton("START");
        JButton penalty = new JButton("PENALTY");
        JButton events = new JButton("EVENTS");
        JButton addEvent = new JButton("ADD EVENT");

        stopButton.setSize(25,25);
        stopButton.setBackground(Color.RED);
        stopButton.setForeground(Color.WHITE);
        stopButton.setOpaque(true);
        stopButton.setBorderPainted(false);

        timer = new Timer(1000, e -> {
            if (running) {
                match.tick();
                updateUIData();
            }
        });


        goal1Button.addActionListener(e -> {
            Player goal = (Player) JOptionPane.showInputDialog(
                    this,
                    "Choose player:",
                    "Goal team 1",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    match.getTeam1().getPlayers().toArray(),
                    null
            );
            Player asist = (Player) JOptionPane.showInputDialog(
                    this,
                    "Choose player (CANCEL = NO ASSIST):",
                    "Asist team 1",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    match.getTeam1().getPlayers().toArray() ,
                    null
            );

            if (goal != null) {
                match.addGoal(match.getTeam1(), goal, asist);
                updateUIData();
            }
        });

        goal2Button.addActionListener(e -> {
            Player goal = (Player) JOptionPane.showInputDialog(
                    this,
                    "Choose player:",
                    "Goal team 2",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    match.getTeam2().getPlayers().toArray(),
                    null
            );
            Player asist = (Player) JOptionPane.showInputDialog(
                    this,
                    "Choose player (CANCEL = NO ASSIST):",
                    "Asist team 2",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    match.getTeam2().getPlayers().toArray(),
                    null
            );

            if (goal != null) {
                match.addGoal(match.getTeam2(), goal, asist);
                updateUIData();
            }
        });

        stopButton.addActionListener(e -> {
            running = !running;
            stopButton.setText(running ? "STOP" : "START");
            if (running) {
                timer.start();
            }
        });

        events.addActionListener(e -> {
            new EventsWindow(match);
        });
        addEvent.addActionListener(e -> {
            String text = JOptionPane.showInputDialog("Add event:");
            if (text != null && !text.isEmpty()) {
                match.addEvent(text);
            }
        });

        JPanel buttons = new JPanel();
        buttons.add(goal1Button);
        buttons.add(goal2Button);
        buttons.add(stopButton);
        buttons.add(penalty);
        buttons.add(events);
        buttons.add(addEvent);
        setLayout(new BorderLayout());
        add(scoreLabel, BorderLayout.CENTER);
        add(timeLabel, BorderLayout.NORTH);
        add(buttons, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void updateUIData() {

        scoreLabel.setText(match.getScore1() + " : " + match.getScore2());

        timeLabel.setText(String.format("%02d:%02d",
                match.getMinutes(),
                match.getSeconds()));

    }
}
