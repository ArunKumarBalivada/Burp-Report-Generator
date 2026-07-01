package com.arun.burpreport.word;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class WordTemplateProcessor {

    public XWPFDocument process(Path template,
                                Map<String, String> placeholders)
            throws IOException {

        XWPFDocument document =
                TemplateLoader.load(template);

        PlaceholderReplacer.replace(document, placeholders);

        return document;

    }

}