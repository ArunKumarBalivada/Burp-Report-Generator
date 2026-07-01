package com.arun.burpreport.word;

import com.arun.burpreport.model.Finding;
import com.arun.burpreport.model.Project;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class WordReportGenerator {

    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private final WordTemplateProcessor templateProcessor =
            new WordTemplateProcessor();

    private final ExecutiveSummaryGenerator executiveSummaryGenerator =
            new ExecutiveSummaryGenerator();

    private final FindingSummaryGenerator findingSummaryGenerator =
            new FindingSummaryGenerator();

    private final DetailedFindingGenerator detailedFindingGenerator =
            new DetailedFindingGenerator();

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

        Map<String, String> placeholders =
                createPlaceholderMap(project);

        XWPFDocument document =
                templateProcessor.process(templateFile, placeholders);

        //--------------------------------------------------------
        // Executive Summary
        //--------------------------------------------------------

        executiveSummaryGenerator.generate(
                document,
                project
        );

        //--------------------------------------------------------
        // Summary Table
        //--------------------------------------------------------

        findingSummaryGenerator.generate(
                document,
                project
        );

        //--------------------------------------------------------
        // Detailed Findings
        //--------------------------------------------------------

        if (project.getFindings() != null) {

            for (Finding finding : project.getFindings()) {

                detailedFindingGenerator.generate(
                        document,
                        finding
                );

            }

        }

        //--------------------------------------------------------
        // Save Report
        //--------------------------------------------------------

        try (FileOutputStream out =
                     new FileOutputStream(outputFile.toFile())) {

            document.write(out);

        }

        document.close();

    }

    private Map<String, String> createPlaceholderMap(Project project) {

        Map<String, String> map = new HashMap<>();

        map.put("${APPLICATION_NAME}",
                safe(project.getApplicationName()));

        map.put("${VENDOR}",
                safe(project.getVendorName()));

        map.put("${CONSULTANT}",
                safe(project.getConsultant()));

        map.put("${CLASSIFICATION}",
                project.getClassification() != null
                        ? project.getClassification().toString()
                        : "");

        map.put("${ASSESSMENT_TYPE}",
                project.getAssessmentType() != null
                        ? project.getAssessmentType().toString()
                        : "");

        map.put("${ENVIRONMENT}",
                project.getEnvironment() != null
                        ? project.getEnvironment().toString()
                        : "");

        map.put("${TESTING_APPROACH}",
                project.getTestingApproach() != null
                        ? project.getTestingApproach().toString()
                        : "");

        map.put("${START_DATE}",
                project.getStartDate() != null
                        ? project.getStartDate().format(DATE_FORMAT)
                        : "");

        map.put("${END_DATE}",
                project.getEndDate() != null
                        ? project.getEndDate().format(DATE_FORMAT)
                        : "");

        map.put("${ASSUMPTIONS}",
                safe(project.getAssumptions()));

        map.put("${TOTAL_FINDINGS}",
                String.valueOf(project.getTotalFindings()));

        return map;

    }

    private String safe(String value) {

        return value == null ? "" : value;

    }

}