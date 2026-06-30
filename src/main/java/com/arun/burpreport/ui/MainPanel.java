package com.arun.burpreport.ui;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    public MainPanel() {

        setLayout(new BorderLayout());

        JLabel title = new JLabel("Burp Report Generator", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 24));

        JLabel version = new JLabel("Version 0.1.0", SwingConstants.CENTER);

        add(title, BorderLayout.CENTER);
        add(version, BorderLayout.SOUTH);
    }
}