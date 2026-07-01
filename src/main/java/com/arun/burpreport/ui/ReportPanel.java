package com.arun.burpreport.ui;

import com.arun.burpreport.model.Project;
import com.arun.burpreport.service.ProjectService;
import com.arun.burpreport.service.ReportService;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.nio.file.Path;

public class ReportPanel extends JPanel {

    private JButton btnGenerateReport;

    private final ReportService reportService;

    public ReportPanel() {

        reportService = new ReportService();

        initializeComponents();
        buildLayout();
        registerEvents();

    }

    private void initializeComponents() {

        btnGenerateReport = new JButton("Generate Word Report");

        btnGenerateReport.setPreferredSize(new Dimension(220, 40));

    }

    private void buildLayout() {

        setLayout(new GridBagLayout());

        add(btnGenerateReport);

    }

    private void registerEvents() {

        btnGenerateReport.addActionListener(e -> generateReport());

    }

    private void generateReport() {

        Project project = ProjectService
                .getInstance()
                .getCurrentProject();

        if (project == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "No project is currently loaded.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );

            return;

        }

        //---------------------------------------
        // Select Template
        //---------------------------------------

        JFileChooser templateChooser = new JFileChooser();

        templateChooser.setDialogTitle("Select Word Template (.docx)");

        if (templateChooser.showOpenDialog(this)
                != JFileChooser.APPROVE_OPTION) {

            return;

        }

        File templateFile = templateChooser.getSelectedFile();

        //---------------------------------------
        // Save Report
        //---------------------------------------

        JFileChooser outputChooser = new JFileChooser();

        outputChooser.setDialogTitle("Save Generated Report");

        outputChooser.setSelectedFile(
                new File(project.getApplicationName() + "_Report.docx")
        );

        if (outputChooser.showSaveDialog(this)
                != JFileChooser.APPROVE_OPTION) {

            return;

        }

        File outputFile = outputChooser.getSelectedFile();

        try {

            Path outputPath = outputFile.toPath();

            if (!outputPath.toString()
                    .toLowerCase()
                    .endsWith(".docx")) {

                outputPath = outputPath.resolveSibling(
                        outputPath.getFileName() + ".docx"
                );

            }

            reportService.generateReport(
                    project,
                    templateFile.toPath(),
                    outputPath
            );

            JOptionPane.showMessageDialog(
                    this,
                    "Report generated successfully.\n\n"
                            + outputPath,
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Report Generation Failed",
                    JOptionPane.ERROR_MESSAGE
            );

        }

    }

}