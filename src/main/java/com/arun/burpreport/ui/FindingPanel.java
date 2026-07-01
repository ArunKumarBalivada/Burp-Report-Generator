package com.arun.burpreport.ui;

import com.arun.burpreport.library.FindingFactory;
import com.arun.burpreport.model.Finding;
import com.arun.burpreport.model.FindingDefinition;
import com.arun.burpreport.service.ProjectService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class FindingPanel extends JPanel {

    private JTable tblFindings;
    private DefaultTableModel tableModel;

    private JButton btnAddFinding;
    private JButton btnEditFinding;
    private JButton btnRemoveFinding;

    public FindingPanel() {

        initializeComponents();
        buildLayout();
        registerEvents();

        refreshTable();

    }

    private void initializeComponents() {

        tableModel = new DefaultTableModel(
                new Object[]{
                        "Severity",
                        "Title",
                        "CVSS",
                        "Status"
                },
                0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tblFindings = new JTable(tableModel);

        tblFindings.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblFindings.setRowHeight(25);
        tblFindings.setAutoCreateRowSorter(true);

        btnAddFinding = new JButton("Add");
        btnEditFinding = new JButton("Edit");
        btnRemoveFinding = new JButton("Remove");

    }

    private void buildLayout() {

        setLayout(new BorderLayout(10, 10));

        JScrollPane scrollPane = new JScrollPane(tblFindings);

        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        buttonPanel.add(btnAddFinding);
        buttonPanel.add(btnEditFinding);
        buttonPanel.add(btnRemoveFinding);

        add(buttonPanel, BorderLayout.SOUTH);

    }

    private void registerEvents() {

        btnAddFinding.addActionListener(e -> addFinding());

        btnEditFinding.addActionListener(e -> editFinding());

        btnRemoveFinding.addActionListener(e -> removeFinding());

        tblFindings.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2) {
                    editFinding();
                }

            }

        });

    }

    private void addFinding() {

        FindingSearchDialog dialog = new FindingSearchDialog(null);

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);

        FindingDefinition definition = dialog.getSelectedFinding();

        if (definition == null) {
            return;
        }

        Finding finding = FindingFactory.createFinding(definition);

        ProjectService.getInstance().addFinding(finding);

        refreshTable();

        JOptionPane.showMessageDialog(
                this,
                "Finding added successfully.",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );

    }

    private void editFinding() {

        int row = tblFindings.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a finding.",
                    "Edit Finding",
                    JOptionPane.WARNING_MESSAGE
            );

            return;

        }

        JOptionPane.showMessageDialog(
                this,
                "Edit Finding will be implemented in Version 1.1.",
                "Information",
                JOptionPane.INFORMATION_MESSAGE
        );

    }

    private void removeFinding() {

        int selectedRow = tblFindings.getSelectedRow();

        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a finding to remove.",
                    "Remove Finding",
                    JOptionPane.WARNING_MESSAGE
            );

            return;

        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to remove the selected finding?",
                "Confirm",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        int modelRow = tblFindings.convertRowIndexToModel(selectedRow);

        Finding finding = ProjectService
                .getInstance()
                .getFindings()
                .get(modelRow);

        ProjectService.getInstance().removeFinding(finding);

        refreshTable();

    }

    public void refreshTable() {

        loadFindings(ProjectService
                .getInstance()
                .getFindings());

    }

    public void loadFindings(List<Finding> findings) {

        tableModel.setRowCount(0);

        if (findings == null || findings.isEmpty()) {
            return;
        }

        for (Finding finding : findings) {

            tableModel.addRow(new Object[]{
                    finding.getSeverity(),
                    finding.getTitle(),
                    finding.getCvssScore(),
                    finding.getStatus()
            });

        }

    }

    public JTable getTable() {
        return tblFindings;
    }

    public JButton getAddButton() {
        return btnAddFinding;
    }

    public JButton getEditButton() {
        return btnEditFinding;
    }

    public JButton getRemoveButton() {
        return btnRemoveFinding;
    }

}