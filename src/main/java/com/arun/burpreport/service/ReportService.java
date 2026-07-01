package com.arun.burpreport.service;

import com.arun.burpreport.model.Project;
import com.arun.burpreport.word.WordReportGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReportService {

    private final WordReportGenerator generator;

    public ReportService() {

        this.generator = new WordReportGenerator();

    }

    public void generateReport(Project project,
                               Path templateFile,
                               Path outputFile) throws IOException {

        if (project == null) {
            throw new IllegalArgumentException("Project cannot be null.");
        }

        if (templateFile == null) {
            throw new IllegalArgumentException("Template file cannot be null.");
        }

        if (outputFile == null) {
            throw new IllegalArgumentException("Output file cannot be null.");
        }

        if (!Files.exists(templateFile)) {
            throw new IOException("Template file not found:\n" + templateFile);
        }

        generator.generateReport(
                project,
                templateFile,
                outputFile
        );

    }

}