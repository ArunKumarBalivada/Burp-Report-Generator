package com.arun.burpreport.word;

import com.arun.burpreport.model.Finding;
import com.arun.burpreport.model.Project;
import com.arun.burpreport.model.enums.Severity;
import org.apache.poi.xwpf.usermodel.*;

public class ExecutiveSummaryGenerator {

    public void generate(XWPFDocument document, Project project) {

        document.createParagraph();

        XWPFParagraph heading = document.createParagraph();
        heading.setStyle("Heading1");

        XWPFRun headingRun = heading.createRun();
        headingRun.setBold(true);
        headingRun.setFontSize(16);
        headingRun.setText("Executive Summary");

        int critical = 0;
        int high = 0;
        int medium = 0;
        int low = 0;
        int info = 0;

        for (Finding finding : project.getFindings()) {

            if (finding.getSeverity() == null)
                continue;

            switch (finding.getSeverity()) {

                case CRITICAL -> critical++;
                case HIGH -> high++;
                case MEDIUM -> medium++;
                case LOW -> low++;
                case INFORMATIONAL -> info++;

            }

        }

        XWPFParagraph p = document.createParagraph();

        XWPFRun run = p.createRun();

        run.setFontSize(11);

        run.setText(
                "The assessment identified "
                        + project.getTotalFindings()
                        + " findings."
        );

        run.addBreak();

        run.setText("Critical : " + critical);

        run.addBreak();

        run.setText("High : " + high);

        run.addBreak();

        run.setText("Medium : " + medium);

        run.addBreak();

        run.setText("Low : " + low);

        run.addBreak();

        run.setText("Informational : " + info);

    }

}