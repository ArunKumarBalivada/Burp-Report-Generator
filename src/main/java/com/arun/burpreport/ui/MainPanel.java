package com.arun.burpreport.ui;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private final JTabbedPane tabbedPane;

    public MainPanel() {

        setLayout(new BorderLayout());

        tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Project", createPlaceholderPanel("Project Module"));
        tabbedPane.addTab("Findings", createPlaceholderPanel("Findings Module"));
        tabbedPane.addTab("Dashboard", createPlaceholderPanel("Dashboard Module"));
        tabbedPane.addTab("Report", createPlaceholderPanel("Report Generator"));
        tabbedPane.addTab("Settings", createPlaceholderPanel("Settings"));

        disableProjectTabs();

        add(tabbedPane, BorderLayout.CENTER);
    }

    private JPanel createPlaceholderPanel(String title) {

        JPanel panel = new JPanel(new GridBagLayout());

        JLabel label = new JLabel(title);

        label.setFont(new Font("SansSerif", Font.BOLD, 20));

        panel.add(label);

        return panel;
    }

    public void enableProjectTabs() {

        tabbedPane.setEnabledAt(0, true);
        tabbedPane.setEnabledAt(1, true);
        tabbedPane.setEnabledAt(2, true);
        tabbedPane.setEnabledAt(3, true);
        tabbedPane.setEnabledAt(4, true);

    }

    public void disableProjectTabs() {

        tabbedPane.setEnabledAt(0, true);
        tabbedPane.setEnabledAt(1, false);
        tabbedPane.setEnabledAt(2, false);
        tabbedPane.setEnabledAt(3, false);
        tabbedPane.setEnabledAt(4, true);

    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

}