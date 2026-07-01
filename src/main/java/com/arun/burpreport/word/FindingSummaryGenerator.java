package com.arun.burpreport.word;

import com.arun.burpreport.model.Finding;
import com.arun.burpreport.model.Project;
import org.apache.poi.xwpf.usermodel.*;

public class FindingSummaryGenerator {

    public void generate(XWPFDocument document,
                         Project project) {

        document.createParagraph();

        XWPFParagraph heading = document.createParagraph();

        heading.setStyle("Heading1");

        heading.createRun().setText("Summary of Vulnerabilities");

        XWPFTable table = document.createTable();

        table.setWidth("100%");

        XWPFTableRow header = table.getRow(0);

        header.getCell(0).setText("Severity");

        header.addNewTableCell().setText("Title");

        header.addNewTableCell().setText("CVSS");

        header.addNewTableCell().setText("Status");

        for (Finding finding : project.getFindings()) {

            XWPFTableRow row = table.createRow();

            row.getCell(0).setText(String.valueOf(finding.getSeverity()));

            row.getCell(1).setText(finding.getTitle());

            row.getCell(2).setText(
                    String.valueOf(finding.getCvssScore()));

            row.getCell(3).setText(
                    String.valueOf(finding.getStatus()));

        }

    }

}