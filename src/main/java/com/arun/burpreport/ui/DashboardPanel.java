package com.arun.burpreport.ui;

import com.arun.burpreport.model.Finding;
import com.arun.burpreport.model.enums.Severity;
import com.arun.burpreport.service.ProjectService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.List;

public class DashboardPanel extends JPanel {

    private JLabel lblTotalFindings;
    private JLabel lblCritical;
    private JLabel lblHigh;
    private JLabel lblMedium;
    private JLabel lblLow;
    private JLabel lblInformational;

    public DashboardPanel() {

        initializeComponents();
        buildLayout();

        refreshDashboard();

    }

    private void initializeComponents() {

        lblTotalFindings = new JLabel();
        lblCritical = new JLabel();
        lblHigh = new JLabel();
        lblMedium = new JLabel();
        lblLow = new JLabel();
        lblInformational = new JLabel();

        Font font = new Font("SansSerif", Font.BOLD, 15);

        lblTotalFindings.setFont(font);
        lblCritical.setFont(font);
        lblHigh.setFont(font);
        lblMedium.setFont(font);
        lblLow.setFont(font);
        lblInformational.setFont(font);

    }

    private void buildLayout() {

        setLayout(new BorderLayout());

        JPanel statsPanel = new JPanel();
        statsPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        statsPanel.setLayout(new GridLayout(6, 1, 10, 10));

        statsPanel.add(lblTotalFindings);
        statsPanel.add(lblCritical);
        statsPanel.add(lblHigh);
        statsPanel.add(lblMedium);
        statsPanel.add(lblLow);
        statsPanel.add(lblInformational);

        add(statsPanel, BorderLayout.NORTH);

    }

    public void refreshDashboard() {

        List<Finding> findings =
                ProjectService.getInstance().getFindings();

        int critical = 0;
        int high = 0;
        int medium = 0;
        int low = 0;
        int informational = 0;

        for (Finding finding : findings) {

            if (finding.getSeverity() == null) {
                continue;
            }

            switch (finding.getSeverity()) {

                case CRITICAL:
                    critical++;
                    break;

                case HIGH:
                    high++;
                    break;

                case MEDIUM:
                    medium++;
                    break;

                case LOW:
                    low++;
                    break;

                case INFORMATIONAL:
                    informational++;
                    break;

            }

        }

        lblTotalFindings.setText(
                "Total Findings : " + findings.size());

        lblCritical.setText(
                "Critical : " + critical);

        lblHigh.setText(
                "High : " + high);

        lblMedium.setText(
                "Medium : " + medium);

        lblLow.setText(
                "Low : " + low);

        lblInformational.setText(
                "Informational : " + informational);

    }

}