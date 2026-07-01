package com.arun.burpreport.ui;

import javax.swing.*;
import java.awt.*;
import com.arun.burpreport.settings.SettingsPanel;

public class MainPanel extends JPanel {

    private final JTabbedPane tabbedPane;

    private final ProjectPanel projectPanel;
    private final FindingPanel findingPanel;
    private final DashboardPanel dashboardPanel;
    private final ReportPanel reportPanel;
    private final SettingsPanel settingsPanel;

    public MainPanel() {

        setLayout(new BorderLayout());

        tabbedPane = new JTabbedPane();

        // Create Panels
        projectPanel = new ProjectPanel();
        findingPanel = new FindingPanel();
        dashboardPanel = new DashboardPanel();
        reportPanel = new ReportPanel();
        settingsPanel = new SettingsPanel();

        // Add Tabs
        tabbedPane.addTab("Project", projectPanel);
        tabbedPane.addTab("Findings", findingPanel);
        tabbedPane.addTab("Dashboard", dashboardPanel);
        tabbedPane.addTab("Report", reportPanel);
        tabbedPane.addTab("Settings", settingsPanel);

        disableProjectTabs();

        add(tabbedPane, BorderLayout.CENTER);

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

    public ProjectPanel getProjectPanel() {
        return projectPanel;
    }

    public FindingPanel getFindingPanel() {
        return findingPanel;
    }

    public DashboardPanel getDashboardPanel() {
        return dashboardPanel;
    }

    public ReportPanel getReportPanel() {
        return reportPanel;
    }

    public SettingsPanel getSettingsPanel() {
        return settingsPanel;
    }

}