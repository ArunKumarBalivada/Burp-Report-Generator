package com.arun.burpreport.word;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public final class TemplateLoader {

    private TemplateLoader() {
        // Prevent instantiation
    }

    public static XWPFDocument load(Path templatePath) throws IOException {

        if (templatePath == null) {
            throw new IllegalArgumentException("Template path cannot be null.");
        }

        if (!Files.exists(templatePath)) {
            throw new IOException("Template not found: " + templatePath);
        }

        try (InputStream inputStream = Files.newInputStream(templatePath)) {
            return new XWPFDocument(inputStream);
        }

    }

}