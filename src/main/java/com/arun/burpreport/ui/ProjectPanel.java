package com.arun.burpreport.ui;

import com.arun.burpreport.common.AppConstants;
import com.arun.burpreport.model.Project;
import com.arun.burpreport.model.enums.AssessmentType;
import com.arun.burpreport.model.enums.Classification;
import com.arun.burpreport.model.enums.Environment;
import com.arun.burpreport.model.enums.FindingType;
import com.arun.burpreport.model.enums.TestingApproach;
import com.arun.burpreport.service.ProjectService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

        // Scope
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

        // Assumptions

        row++;

        gbc.gridy = row;
        formPanel.add(new JLabel("Assumptions"), gbc);

        row++;

        gbc.gridy = row;

        JScrollPane assumptionPane = new JScrollPane(txtAssumptions);
        assumptionPane.setPreferredSize(new Dimension(400, 120));

        formPanel.add(assumptionPane, gbc);

        // Bottom Buttons

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        buttonPanel.add(btnNewProject);
        buttonPanel.add(btnOpenProject);
        buttonPanel.add(btnSaveProject);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

    }


    private void registerEvents() {

        btnAddScope.addActionListener(e -> {

            String scope = JOptionPane.showInputDialog(
                    this,
                    "Enter Scope Item",
                    "Add Scope",
                    JOptionPane.PLAIN_MESSAGE);

            if (scope != null && !scope.isBlank()) {
                scopeModel.addElement(scope.trim());
            }

        });

        btnRemoveScope.addActionListener(e -> {

            int index = lstScope.getSelectedIndex();

            if (index >= 0) {
                scopeModel.remove(index);
            }

        });

        btnNewProject.addActionListener(e -> {

            ProjectService.getInstance().newProject();

            clearForm();

            JOptionPane.showMessageDialog(
                    this,
                    "New project created successfully.");

        });

        btnSaveProject.addActionListener(e -> {

            if (txtApplicationName.getText().isBlank()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Application Name is mandatory.",
                        "Validation",
                        JOptionPane.WARNING_MESSAGE);

                txtApplicationName.requestFocus();
                return;
            }

            try {

                Project project = getProject();

                ProjectService.getInstance().setCurrentProject(project);

                JFileChooser chooser = new JFileChooser();
                chooser.setDialogTitle("Save Project");
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

                if (chooser.showSaveDialog(this)
                        == JFileChooser.APPROVE_OPTION) {

                    java.nio.file.Path file = chooser.getSelectedFile().toPath();

                    if (!file.toString().toLowerCase().endsWith(".json")) {
                        file = file.resolveSibling(file.getFileName() + ".json");
                    }

                    ProjectService.getInstance().saveProject(file);

                    JOptionPane.showMessageDialog(
                            this,
                            "Project saved successfully.");

                }

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage() == null ? ex.toString() : ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);

            }

        });

        btnOpenProject.addActionListener(e -> {

            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Open Project");
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            if (chooser.showOpenDialog(this)
                    != JFileChooser.APPROVE_OPTION) {
                return;
            }

            try {

                Project project =
                        ProjectService.getInstance()
                                .openProject(chooser.getSelectedFile().toPath());

                loadProject(project);

                JOptionPane.showMessageDialog(
                        this,
                        "Project loaded successfully.");

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage() == null ? ex.toString() : ex.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE);

            }

        });

    }

    public Project getProject() {

        Project project = ProjectService.getInstance().getCurrentProject();

        if (project == null) {
            project = ProjectService.getInstance().newProject();
        }

        project.setApplicationName(txtApplicationName.getText().trim());
        project.setVendorName(txtVendor.getText().trim());
        project.setConsultant(txtConsultant.getText().trim());

        project.setAssessmentType(
                (AssessmentType) cmbAssessmentType.getSelectedItem());

        project.setClassification(
                (Classification) cmbClassification.getSelectedItem());

        project.setEnvironment(
                (Environment) cmbEnvironment.getSelectedItem());

        project.setTestingApproach(
                (TestingApproach) cmbTestingApproach.getSelectedItem());

        project.setFindingType(
                (FindingType) cmbFindingType.getSelectedItem());

        try {

            if (!txtStartDate.getText().isBlank()) {
                project.setStartDate(LocalDate.parse(txtStartDate.getText().trim()));
            }

            if (!txtEndDate.getText().isBlank()) {
                project.setEndDate(LocalDate.parse(txtEndDate.getText().trim()));
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid date format.\nPlease use YYYY-MM-DD.",
                    "Validation",
                    JOptionPane.WARNING_MESSAGE);

            return ProjectService.getInstance().getCurrentProject();

        }

        project.setAssumptions(txtAssumptions.getText());

        List<String> scopeItems = new ArrayList<>();

        for (int i = 0; i < scopeModel.size(); i++) {
            scopeItems.add(scopeModel.getElementAt(i));
        }

        project.setScopeItems(scopeItems);

        return project;

    }

    public void loadProject(Project project) {

        if (project == null) {
            return;
        }

        txtApplicationName.setText(project.getApplicationName());
        txtVendor.setText(project.getVendorName());
        txtConsultant.setText(project.getConsultant());

        cmbAssessmentType.setSelectedItem(project.getAssessmentType());
        cmbClassification.setSelectedItem(project.getClassification());
        cmbEnvironment.setSelectedItem(project.getEnvironment());
        cmbTestingApproach.setSelectedItem(project.getTestingApproach());
        cmbFindingType.setSelectedItem(project.getFindingType());

        txtStartDate.setText(
                project.getStartDate() == null ? "" : project.getStartDate().toString());

        txtEndDate.setText(
                project.getEndDate() == null ? "" : project.getEndDate().toString());

        txtAssumptions.setText(
                project.getAssumptions() == null
                        ? ""
                        : project.getAssumptions());

        scopeModel.clear();

        if (project.getScopeItems() != null) {

            for (String scope : project.getScopeItems()) {
                scopeModel.addElement(scope);
            }

        }

    }

    private void clearForm() {

        txtApplicationName.setText("");
        txtVendor.setText(AppConstants.DEFAULT_VENDOR);
        txtConsultant.setText("");

        txtStartDate.setText(LocalDate.now().toString());
        txtEndDate.setText(LocalDate.now().toString());

        cmbAssessmentType.setSelectedIndex(0);
        cmbClassification.setSelectedIndex(0);
        cmbEnvironment.setSelectedIndex(0);
        cmbTestingApproach.setSelectedIndex(0);
        cmbFindingType.setSelectedIndex(0);

        txtAssumptions.setText("");

        scopeModel.clear();

    }

    private void loadDefaults() {

        txtVendor.setText(AppConstants.DEFAULT_VENDOR);

        txtStartDate.setText(LocalDate.now().toString());
        txtEndDate.setText(LocalDate.now().toString());

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