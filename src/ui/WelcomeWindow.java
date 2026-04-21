package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class WelcomeWindow extends JFrame {

    public WelcomeWindow() {
        setTitle("Floorball app");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        JLabel label = new JLabel("Welcome to Floorball App", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 20));

        JButton start = new JButton("Start");

        setLayout(new BorderLayout());
        add(label, BorderLayout.CENTER);
        add(start, BorderLayout.SOUTH);

        setVisible(true);
    }
}
