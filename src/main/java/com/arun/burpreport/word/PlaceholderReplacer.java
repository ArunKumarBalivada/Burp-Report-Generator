package com.arun.burpreport.word;

import org.apache.poi.xwpf.usermodel.*;

import java.util.Map;

public final class PlaceholderReplacer {

    private PlaceholderReplacer() {
    }

    public static void replace(XWPFDocument document,
                               Map<String, String> placeholders) {

        replaceParagraphs(document, placeholders);

        replaceTables(document, placeholders);

    }

    private static void replaceParagraphs(XWPFDocument document,
                                          Map<String, String> placeholders) {

        for (XWPFParagraph paragraph : document.getParagraphs()) {

            replaceRuns(paragraph, placeholders);

        }

    }

    private static void replaceTables(XWPFDocument document,
                                      Map<String, String> placeholders) {

        for (XWPFTable table : document.getTables()) {

            for (XWPFTableRow row : table.getRows()) {

                for (XWPFTableCell cell : row.getTableCells()) {

                    for (XWPFParagraph paragraph : cell.getParagraphs()) {

                        replaceRuns(paragraph, placeholders);

                    }

                }

            }

        }

    }

    private static void replaceRuns(XWPFParagraph paragraph,
                                    Map<String, String> placeholders) {

        for (XWPFRun run : paragraph.getRuns()) {

            String text = run.getText(0);

            if (text == null) {
                continue;
            }

            for (Map.Entry<String, String> entry : placeholders.entrySet()) {

                text = text.replace(entry.getKey(), entry.getValue());

            }

            run.setText(text, 0);

        }

    }

}