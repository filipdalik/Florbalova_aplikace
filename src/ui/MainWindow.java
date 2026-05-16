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
    private JLabel periodLabel;

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
        setLayout(new BorderLayout());

        JPanel northPanel = new JPanel(new GridLayout(2, 1));
        timeLabel = new JLabel("00:00", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial", Font.BOLD, 80));
        timeLabel.setForeground(new Color(108, 0, 0));
        periodLabel = new JLabel("1. period", SwingConstants.CENTER);
        periodLabel.setFont(new Font("Arial", Font.ITALIC, 25));
        northPanel.add(timeLabel);
        northPanel.add(periodLabel);

        scoreLabel = new JLabel("0 : 0", SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 120));

        team1Label = new JLabel(match.getTeam1().getName(), SwingConstants.RIGHT);
        team2Label = new JLabel(match.getTeam2().getName(), SwingConstants.LEFT);
        team1Label.setFont(new Font("Arial", Font.BOLD, 30));
        team2Label.setFont(new Font("Arial", Font.BOLD, 30));
        team1Label.setForeground(new Color(23, 50, 106));
        team2Label.setForeground(new Color(23, 50, 106));

        JPanel teamPanel = new JPanel(new GridLayout(1, 2, 50, 0));
        teamPanel.add(team1Label);
        teamPanel.add(team2Label);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(scoreLabel, BorderLayout.CENTER);
        centerPanel.add(teamPanel, BorderLayout.SOUTH);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton goal1Button = new JButton("GOAL TEAM 1");
        JButton goal2Button = new JButton("GOAL TEAM 2");
        JButton stopButton = new JButton("START");
        stopButton.setBackground(new Color(108, 0, 0));
        stopButton.setForeground(new Color(255, 255, 255));
        JButton penalty = new JButton("PENALTY");
        JButton events = new JButton("EVENTS");
        JButton addEvent = new JButton("ADD EVENT");


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
                    "Assist (cancel = no assist):",
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

        buttonPanel.add(goal1Button);
        buttonPanel.add(goal2Button);
        buttonPanel.add(penalty);
        buttonPanel.add(stopButton);
        buttonPanel.add(events);
        buttonPanel.add(addEvent);


        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);

        timer = new Timer(50, e -> {
            if (running) {
                boolean periodEnd = match.tick();
                updateUIData();
                if (periodEnd || match.getMinutes() >= 20) {
                    stopMatch();
                    stopButton.setText("START");
                }
            }
        });
    }

    private void updateUIData() {
        timeLabel.setText(String.format("%02d:%02d", match.getMinutes(), match.getSeconds()));
        scoreLabel.setText(match.getScore1() + " : " + match.getScore2());
        periodLabel.setText(match.getPeriod() + ". period");
    }

    private void stopMatch() {
        running = false;
        timer.stop();

        if (match.getPeriod() < 3){
            JOptionPane.showMessageDialog(this, "End of " + match.getPeriod() + ". period!");
            match.nextPeriod();
            updateUIData();
        } else {
            handleFinalEnd();
        }
    }
    private void handleFinalEnd() {
        match.saveToHistory();
        String text = "The match has ended! \n Score: " + match.getScore1() + " : " + match.getScore2() + "\n You can see this data in history.";
        Object[] options = {"Back to menu", "End"};
        int n = JOptionPane.showOptionDialog(this, text, "End of match", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (n == JOptionPane.YES_OPTION) {
            new WelcomeWindow();
            dispose();
        } else {
            System.exit(0);
        }
    }
}