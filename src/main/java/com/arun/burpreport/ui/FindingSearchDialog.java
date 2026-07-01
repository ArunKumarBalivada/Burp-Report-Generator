package com.arun.burpreport.ui;

import com.arun.burpreport.library.FindingLibraryManager;
import com.arun.burpreport.model.FindingDefinition;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class FindingSearchDialog extends JDialog {

    private JTextField txtSearch;

    private JTable tblFindings;

    private DefaultTableModel tableModel;

    private JButton btnAdd;

    private JButton btnCancel;

    private FindingDefinition selectedFinding;

    public FindingSearchDialog(Frame owner) {

        super(owner, "Finding Library", true);

        initializeComponents();

        buildLayout();

        registerEvents();

        loadFindings();

        setSize(900, 500);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

    private void initializeComponents() {

        txtSearch = new JTextField(30);

        tableModel = new DefaultTableModel(
                new Object[]{
                        "Severity",
                        "Finding",
                        "CVSS",
                        "Category"
                },
                0) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        tblFindings = new JTable(tableModel);

        tblFindings.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblFindings.setRowHeight(25);
        tblFindings.setAutoCreateRowSorter(true);

        btnAdd = new JButton("Add Finding");

        btnCancel = new JButton("Cancel");

    }

    private void buildLayout() {

        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        topPanel.add(new JLabel("Search"));

        topPanel.add(txtSearch);

        add(topPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(tblFindings);

        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        bottomPanel.add(btnAdd);

        bottomPanel.add(btnCancel);

        add(bottomPanel, BorderLayout.SOUTH);

    }

    private void registerEvents() {

        btnCancel.addActionListener(e -> dispose());

        btnAdd.addActionListener(e -> selectFinding());

        txtSearch.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search();
            }

        });

        tblFindings.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2) {
                    selectFinding();
                }

            }

        });

    }

    private void loadFindings() {

        tableModel.setRowCount(0);

        List<FindingDefinition> findings =
                FindingLibraryManager.getInstance().getAll();

        populateTable(findings);

    }

    private void search() {

        String keyword = txtSearch.getText().trim();

        List<FindingDefinition> findings =
                FindingLibraryManager.getInstance().search(keyword);

        populateTable(findings);

    }

    private void populateTable(List<FindingDefinition> findings) {

        tableModel.setRowCount(0);

        for (FindingDefinition finding : findings) {

            tableModel.addRow(new Object[]{

                    finding.getSeverity(),

                    finding.getFindingName(),

                    finding.getCvssScore(),

                    finding.getCategory()

            });

        }

    }

    private void selectFinding() {

        int row = tblFindings.getSelectedRow();

        if (row < 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a finding.",
                    "Finding Library",
                    JOptionPane.INFORMATION_MESSAGE);

            return;

        }

        int modelRow = tblFindings.convertRowIndexToModel(row);

        String findingName = tableModel.getValueAt(modelRow, 1).toString();

        selectedFinding = FindingLibraryManager
                .getInstance()
                .findByName(findingName)
                .orElse(null);

        dispose();

    }

    public FindingDefinition getSelectedFinding() {

        return selectedFinding;

    }

}