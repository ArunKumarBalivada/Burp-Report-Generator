package com.arun.burpreport.word;

import com.arun.burpreport.model.Finding;
import org.apache.poi.xwpf.usermodel.*;

public class DetailedFindingGenerator {

    public void generate(XWPFDocument document,
                         Finding finding) {

        document.createParagraph();

        XWPFParagraph title = document.createParagraph();

        title.setStyle("Heading1");

        XWPFRun run = title.createRun();

        run.setBold(true);

        run.setFontSize(15);

        run.setText(finding.getTitle());

        addSection(document, "Severity", String.valueOf(finding.getSeverity()));

        addSection(document, "CVSS", String.valueOf(finding.getCvssScore()));

        addSection(document, "CWE", finding.getCwe());

        addSection(document, "OWASP", finding.getOwasp());

        addSection(document, "Affected Components",
                finding.getAffectedComponents());

        addSection(document, "Description",
                finding.getDescription());

        addSection(document, "Impact",
                finding.getImpact());

        addSection(document, "Likelihood",
                finding.getLikelihood());

        addSection(document, "Recommendation",
                finding.getRecommendation());

        addSection(document, "Proof of Concept",
                finding.getProofOfConcept());

    }

    private void addSection(XWPFDocument document,
                            String heading,
                            String value) {

        XWPFParagraph h = document.createParagraph();

        XWPFRun hr = h.createRun();

        hr.setBold(true);

        hr.setText(heading);

        XWPFParagraph p = document.createParagraph();

        XWPFRun pr = p.createRun();

        pr.setText(value == null ? "" : value);

    }

}