package ui;
import javax.swing.*;
import java.awt.*;

public class WelcomeWindow extends JFrame {

    public WelcomeWindow() {
        setTitle("Floorball app");
        setSize(850, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);


        JPanel panel = new JPanel();
        panel.setBackground(new Color(43, 42, 42));
        panel.setLayout(new BorderLayout());


        JLabel title = new JLabel("FLOORBALL APP", JLabel.CENTER);
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Times New Roma", Font.BOLD, 60));
        title.setBorder(BorderFactory.createEmptyBorder(40,0,20,0));
        panel.add(title,BorderLayout.NORTH);

        JPanel panel1 = new JPanel();
        panel1.setBackground(new Color(43, 42, 42));
        panel1.setLayout(new GridBagLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(43, 42, 42));
        buttonPanel.setLayout(new GridLayout(3,1,0,20));


        JButton startButton = new JButton("START");
        startButton.setFocusPainted(false);
        startButton.setForeground(Color.WHITE);
        startButton.setFont(new Font("Segoe UI",Font.BOLD,25));
        startButton.setBackground(new Color(234, 141, 0));
        startButton.setBorder(BorderFactory.createEmptyBorder());
        startButton.setPreferredSize(new Dimension(200,50));


        JButton settingsButton = new JButton("SETTINGS");
        settingsButton.setFocusPainted(false);
        settingsButton.setForeground(Color.WHITE);
        settingsButton.setFont(new Font("Segoe UI",Font.BOLD,25));
        settingsButton.setBackground(new Color(120, 0, 0));
        settingsButton.setBorder(BorderFactory.createEmptyBorder());
        settingsButton.setPreferredSize(new Dimension(200,50));

        JButton endButton = new JButton("END");
        endButton.setFocusPainted(false);
        endButton.setForeground(Color.WHITE);
        endButton.setFont(new Font("Segoe UI",Font.BOLD,25));
        endButton.setBackground(new Color(18, 1, 158));
        endButton.setBorder(BorderFactory.createEmptyBorder());
        endButton.setPreferredSize(new Dimension(200,50));

        buttonPanel.add(startButton);
        buttonPanel.add(settingsButton);
        buttonPanel.add(endButton);
        panel1.add(buttonPanel);
        panel.add(panel1,BorderLayout.CENTER);

        startButton.addActionListener(e->{
            new StartWindow();
            dispose();
        });
        settingsButton.addActionListener(e -> {
            new SettingsWindow();
        });

        endButton.addActionListener(e->{
            System.exit(0);
        });

        add(panel);
        setVisible(true);
    }
}
