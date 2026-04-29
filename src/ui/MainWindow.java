package ui;

import model.Match;
import model.Player;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private JLabel scoreLabel;
    private JLabel timeLabel;
    private JLabel team1Label;
    private JLabel team2Label;

    private Timer timer;
    private boolean running = false;
    private Match match;

    public MainWindow(Match match) {

        this.match = match;

        setTitle("Match");
        setSize(950, 750);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        scoreLabel = new JLabel("0 : 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 120));

        timeLabel = new JLabel("00:00", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 80));
        timeLabel.setForeground(new Color(108, 0, 0));


        team1Label = new JLabel(match.getTeam1().getName(), SwingConstants.RIGHT);
        team2Label = new JLabel(match.getTeam2().getName(), SwingConstants.LEFT);
        team1Label.setFont(new Font("Arial", Font.BOLD, 30));
        team2Label.setFont(new Font("Arial", Font.BOLD, 30));
        team1Label.setForeground(new Color(23, 50, 106));
        team2Label.setForeground(new Color(23, 50, 106));

        JButton goal1Button = new JButton("GOAL TEAM 1");
        JButton goal2Button = new JButton("GOAL TEAM 2");
        JButton stopButton = new JButton("START");
        stopButton.setBackground(new Color(108, 0, 0));
        stopButton.setForeground(new Color(255, 255, 255));
        JButton penalty = new JButton("PENALTY");
        JButton events = new JButton("EVENTS");
        JButton addEvent = new JButton("ADD EVENT");

        JPanel buttons = new JPanel();
        buttons.add(goal1Button);
        buttons.add(goal2Button);
        buttons.add(stopButton);
        buttons.add(penalty);
        buttons.add(events);
        buttons.add(addEvent);

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

            Player assist = (Player) JOptionPane.showInputDialog(
                    this,
                    "Assist (optional):",
                    "Assist team 1",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    match.getTeam1().getPlayers().toArray(),
                    null
            );

            if (goal != null) {
                match.addGoal(match.getTeam1(), goal, assist);
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

            Player assist = (Player) JOptionPane.showInputDialog(
                    this,
                    "Assist (optional):",
                    "Assist team 2",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    match.getTeam2().getPlayers().toArray(),
                    null
            );

            if (goal != null) {
                match.addGoal(match.getTeam2(), goal, assist);
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

        events.addActionListener(e -> new EventsWindow(match));

        addEvent.addActionListener(e -> {
            String text = JOptionPane.showInputDialog("Add event:");
            if (text != null && !text.isEmpty()) {
                match.addEvent(text);
            }
        });

        setLayout(new BorderLayout());
        add(timeLabel, BorderLayout.NORTH);
        JPanel center = new JPanel(new BorderLayout());
        center.add(scoreLabel, BorderLayout.CENTER);
        JPanel teamPanel = new JPanel(new BorderLayout());
        teamPanel.add(team2Label, BorderLayout.EAST);
        teamPanel.add(team1Label, BorderLayout.WEST);

        center.add(teamPanel, BorderLayout.SOUTH);
        teamPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 350, 100));
        add(center, BorderLayout.CENTER);
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