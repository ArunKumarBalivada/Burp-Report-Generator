package com.arun.burpreport.ui;

import com.arun.burpreport.model.enums.AssessmentType;
import com.arun.burpreport.model.enums.Classification;
import com.arun.burpreport.model.enums.Environment;
import com.arun.burpreport.model.enums.FindingType;
import com.arun.burpreport.model.enums.TestingApproach;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ProjectPanel extends JPanel {

    // Text Fields
    private JTextField txtApplicationName;
    private JTextField txtVendor;
    private JTextField txtConsultant;
    private JTextField txtStartDate;
    private JTextField txtEndDate;

    // Combo Boxes
    private JComboBox<AssessmentType> cmbAssessmentType;
    private JComboBox<Classification> cmbClassification;
    private JComboBox<Environment> cmbEnvironment;
    private JComboBox<TestingApproach> cmbTestingApproach;
    private JComboBox<FindingType> cmbFindingType;

    // Scope
    private DefaultListModel<String> scopeModel;
    private JList<String> lstScope;

    // Assumptions
    private JTextArea txtAssumptions;

    // Buttons
    private JButton btnAddScope;
    private JButton btnRemoveScope;

    private JButton btnNewProject;
    private JButton btnOpenProject;
    private JButton btnSaveProject;

    public ProjectPanel() {

        initializeComponents();
        buildLayout();
        registerEvents();
        loadDefaults();

    }

    private void initializeComponents() {

        txtApplicationName = new JTextField(30);
        txtVendor = new JTextField(30);
        txtConsultant = new JTextField(30);

        txtStartDate = new JTextField(15);
        txtEndDate = new JTextField(15);

        cmbAssessmentType = new JComboBox<>(AssessmentType.values());
        cmbClassification = new JComboBox<>(Classification.values());
        cmbEnvironment = new JComboBox<>(Environment.values());
        cmbTestingApproach = new JComboBox<>(TestingApproach.values());
        cmbFindingType = new JComboBox<>(FindingType.values());

        scopeModel = new DefaultListModel<>();
        lstScope = new JList<>(scopeModel);

        txtAssumptions = new JTextArea(5, 30);
        txtAssumptions.setLineWrap(true);
        txtAssumptions.setWrapStyleWord(true);

        btnAddScope = new JButton("Add Scope");
        btnRemoveScope = new JButton("Remove Scope");

        btnNewProject = new JButton("New Project");
        btnOpenProject = new JButton("Open Project");
        btnSaveProject = new JButton("Save Project");

    }

    private void buildLayout() {

        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new EmptyBorder(15, 15, 15, 15));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        addField(formPanel, gbc, row++, "Application Name", txtApplicationName);
        addField(formPanel, gbc, row++, "Vendor", txtVendor);
        addField(formPanel, gbc, row++, "IPT Consultant", txtConsultant);
        addField(formPanel, gbc, row++, "Assessment Type", cmbAssessmentType);
        addField(formPanel, gbc, row++, "Classification", cmbClassification);
        addField(formPanel, gbc, row++, "Environment", cmbEnvironment);
        addField(formPanel, gbc, row++, "Testing Approach", cmbTestingApproach);
        addField(formPanel, gbc, row++, "Finding Type", cmbFindingType);
        addField(formPanel, gbc, row++, "Start Date", txtStartDate);
        addField(formPanel, gbc, row++, "End Date", txtEndDate);

        //-----------------------------------------
        // Scope
        //-----------------------------------------

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;

        formPanel.add(new JLabel("Scope"), gbc);

        row++;

        gbc.gridy = row;

        JScrollPane scopePane = new JScrollPane(lstScope);
        scopePane.setPreferredSize(new Dimension(400, 120));

        formPanel.add(scopePane, gbc);

        row++;

        JPanel scopeButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));

        scopeButtons.add(btnAddScope);
        scopeButtons.add(btnRemoveScope);

        gbc.gridy = row;

        formPanel.add(scopeButtons, gbc);

        //-----------------------------------------
        // Assumptions
        //-----------------------------------------

        row++;

        gbc.gridy = row;

        formPanel.add(new JLabel("Assumptions"), gbc);

        row++;

        gbc.gridy = row;

        JScrollPane assumptionsPane = new JScrollPane(txtAssumptions);
        assumptionsPane.setPreferredSize(new Dimension(400,120));

        formPanel.add(assumptionsPane, gbc);

        //-----------------------------------------
        // Bottom Buttons
        //-----------------------------------------

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        buttonPanel.add(btnNewProject);
        buttonPanel.add(btnOpenProject);
        buttonPanel.add(btnSaveProject);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

    }

    private void registerEvents() {

        btnAddScope.addActionListener(e -> {

            // TODO

        });

        btnRemoveScope.addActionListener(e -> {

            // TODO

        });

        btnNewProject.addActionListener(e -> {

            // TODO

        });

        btnOpenProject.addActionListener(e -> {

            // TODO

        });

        btnSaveProject.addActionListener(e -> {

            // TODO

        });

    }

    private void loadDefaults() {

        txtVendor.setText("CGI");

    }

    private void addField(JPanel panel,
                          GridBagConstraints gbc,
                          int row,
                          String label,
                          JComponent component) {

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.weightx = 0;

        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;

        panel.add(component, gbc);

    }

}