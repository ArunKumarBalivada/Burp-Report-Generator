package com.arun.burpreport.settings;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SettingsPanel extends JPanel {

    private JTextField txtCompanyName;
    private JTextField txtDefaultVendor;
    private JTextField txtConsultant;
    private JTextField txtTemplatePath;

    private JButton btnBrowseTemplate;
    private JButton btnSave;

    public SettingsPanel() {

        initializeComponents();
        buildLayout();
        registerEvents();

    }

    private void initializeComponents() {

        txtCompanyName = new JTextField(30);
        txtDefaultVendor = new JTextField(30);
        txtConsultant = new JTextField(30);
        txtTemplatePath = new JTextField(30);

        btnBrowseTemplate = new JButton("Browse");
        btnSave = new JButton("Save Settings");

        //---------------------------------
        // Default Values
        //---------------------------------

        txtCompanyName.setText("CGI");
        txtDefaultVendor.setText("CGI");

    }

    private void buildLayout() {

        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(new EmptyBorder(20,20,20,20));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8,8,8,8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        int row = 0;

        addField(panel, gbc, row++, "Company Name", txtCompanyName);

        addField(panel, gbc, row++, "Default Vendor", txtDefaultVendor);

        addField(panel, gbc, row++, "Consultant", txtConsultant);

        //---------------------------------
        // Template
        //---------------------------------

        gbc.gridx = 0;
        gbc.gridy = row;

        panel.add(new JLabel("Word Template"), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;

        panel.add(txtTemplatePath, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;

        panel.add(btnBrowseTemplate, gbc);

        //---------------------------------
        // Save Button
        //---------------------------------

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        bottom.add(btnSave);

        add(panel, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

    }

    private void registerEvents() {

        btnBrowseTemplate.addActionListener(e -> {

            JFileChooser chooser = new JFileChooser();

            chooser.setDialogTitle("Select Word Template");

            if (chooser.showOpenDialog(this)
                    == JFileChooser.APPROVE_OPTION) {

                txtTemplatePath.setText(
                        chooser.getSelectedFile().getAbsolutePath());

            }

        });

        btnSave.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Settings feature will be connected to settings.json in Version 1.1.",
                    "Information",
                    JOptionPane.INFORMATION_MESSAGE);

        });

    }

    private void addField(JPanel panel,
                          GridBagConstraints gbc,
                          int row,
                          String label,
                          JComponent component) {

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;

        panel.add(new JLabel(label), gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;

        panel.add(component, gbc);

    }

}