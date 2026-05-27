package ui;

import model.Match;
import model.Player;
import model.Team;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private JLabel scoreLabel;
    private JLabel timeLabel;
    private JLabel team1Label;
    private JLabel team2Label;
    private JLabel periodLabel;

    private DefaultListModel<String> team1PenaltiesModel;
    private DefaultListModel<String> team2PenaltiesModel;
    private JList<String> team1PenaltiesList;
    private JList<String> team2PenaltiesList;

    private Timer timer;
    private boolean running = false;
    private Match match;

    private int tickCounter = 0;

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

        team1PenaltiesModel = new DefaultListModel<>();
        team2PenaltiesModel = new DefaultListModel<>();
        team1PenaltiesList = new JList<>(team1PenaltiesModel);
        team2PenaltiesList = new JList<>(team2PenaltiesModel);

        Font penaltyFont = new Font("Monospaced", Font.BOLD, 22);
        team1PenaltiesList.setFont(penaltyFont);
        team2PenaltiesList.setFont(penaltyFont);
        team1PenaltiesList.setForeground(Color.RED);
        team2PenaltiesList.setForeground(Color.RED);
        team1PenaltiesList.setBackground(getBackground());
        team2PenaltiesList.setBackground(getBackground());

        JPanel leftPenaltyPanel = new JPanel(new GridBagLayout());
        JPanel rightPenaltyPanel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 5, 0);

        JLabel p1Title = new JLabel("Penalties", SwingConstants.CENTER);
        p1Title.setFont(new Font("Arial", Font.BOLD, 14));
        leftPenaltyPanel.add(p1Title, gbc);
        gbc.gridy = 1;
        leftPenaltyPanel.add(team1PenaltiesList, gbc);

        gbc.gridy = 0;
        JLabel p2Title = new JLabel("Penalties", SwingConstants.CENTER);
        p2Title.setFont(new Font("Arial", Font.BOLD, 14));
        rightPenaltyPanel.add(p2Title, gbc);
        gbc.gridy = 1;
        rightPenaltyPanel.add(team2PenaltiesList, gbc);

        JPanel penaltyDisplayPanel = new JPanel(new GridLayout(1, 2, 50, 0));
        penaltyDisplayPanel.add(leftPenaltyPanel);
        penaltyDisplayPanel.add(rightPenaltyPanel);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(scoreLabel, BorderLayout.CENTER);

        JPanel bottomCenterPanel = new JPanel(new BorderLayout());
        bottomCenterPanel.add(teamPanel, BorderLayout.NORTH);

        JPanel spacingPanel = new JPanel(new BorderLayout());
        spacingPanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 0, 0));
        spacingPanel.add(penaltyDisplayPanel, BorderLayout.CENTER);
        bottomCenterPanel.add(spacingPanel, BorderLayout.SOUTH);
        centerPanel.add(bottomCenterPanel, BorderLayout.SOUTH);

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

        penalty.addActionListener(e -> {
            Team selectedTeam = (Team) JOptionPane.showInputDialog(
                    this, "Select team for penalty:", "Penalty Team",
                    JOptionPane.PLAIN_MESSAGE, null, new Team[]{match.getTeam1(), match.getTeam2()}, null
            );
            if (selectedTeam == null){
                return;
            }

            Player selectedPlayer = (Player) JOptionPane.showInputDialog(
                    this, "Select player:", "Penalty Player",
                    JOptionPane.PLAIN_MESSAGE, null, selectedTeam.getPlayers().toArray(), null
            );
            if (selectedPlayer == null){
                return;
            }

            String[] durationOptions = {"2 minutes", "2+2 minutes (4 min)"};
            String selectedDuration = (String) JOptionPane.showInputDialog(
                    this, "Select penalty duration:", "Penalty Type",
                    JOptionPane.PLAIN_MESSAGE, null, durationOptions, durationOptions[0]
            );
            if (selectedDuration == null){
                return;
            }

            String[] foulOptions = {
                    "Slashing",
                    "High Stick",
                    "Holding",
                    "Tripping",
                    "Incorrect Pushing / Bodily contact",
                    "Incorrect Distance (3m)",
                    "Playing on the floor / Hand play",
                    "Incorrect Substitution",
                    "Unsportsmanlike conduct",
                    "Other foul..."
            };

            String selectedReason = (String) JOptionPane.showInputDialog(
                    this, "Select floorball foul:", "Reason for Penalty",
                    JOptionPane.PLAIN_MESSAGE, null, foulOptions, foulOptions[0]
            );
            if (selectedReason == null){
                return;
            }
            if (selectedReason.equals("Other foul...")) {
                selectedReason = JOptionPane.showInputDialog(this, "Type custom reason:");
                if (selectedReason == null || selectedReason.trim().isEmpty()) {
                    selectedReason = "Unspecified foul";
                }
            }

            int seconds = selectedDuration.equals(durationOptions[0]) ? 120 : 240;
            selectedPlayer.addPenalty(seconds);
            String eventText = String.format("%s - #%d %s (%s) - Reason: %s",
                    String.format("%02d:%02d", match.getMinutes(), match.getSeconds()),
                    selectedPlayer.getNumber(),
                    selectedPlayer.getName(),
                    selectedDuration,
                    selectedReason
            );

            match.addEvent(eventText);
            updateUIData();
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

        timer = new Timer(1000, e -> {
            if (running) {
                boolean periodEnd = match.tick();
                for (int i = 0; i < match.getTeam1().getPlayers().size(); i++) {
                    match.getTeam1().getPlayers().get(i).tickPenalty();
                }
                for (int i = 0; i < match.getTeam2().getPlayers().size(); i++) {
                    match.getTeam2().getPlayers().get(i).tickPenalty();
                }

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

        team1PenaltiesModel.clear();
        team2PenaltiesModel.clear();

        for (Player p : match.getTeam1().getPlayers()) {
            if (p.getPenaltySeconds() > 0) {
                team1PenaltiesModel.addElement(p.getFormattedPenalty());
            }
        }
        for (Player p : match.getTeam2().getPlayers()) {
            if (p.getPenaltySeconds() > 0) {
                team2PenaltiesModel.addElement(p.getFormattedPenalty());
            }
        }
    }

    private void stopMatch() {
        running = false;
        timer.stop();
        tickCounter = 0;

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