package com.arun.burpreport.library;

import com.arun.burpreport.common.AppConstants;
import com.arun.burpreport.model.FindingDefinition;
import com.arun.burpreport.model.enums.FindingLibraryColumn;
import com.arun.burpreport.model.enums.Severity;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ExcelFindingLibraryLoader {

    public List<FindingDefinition> load(Path excelFile) throws IOException {

        if (excelFile == null) {
            throw new IllegalArgumentException("Excel file path cannot be null.");
        }

        if (!Files.exists(excelFile)) {
            throw new IOException("Finding Knowledge Base not found: " + excelFile);
        }

        List<FindingDefinition> definitions = new ArrayList<>();

        try (InputStream inputStream = Files.newInputStream(excelFile);
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheet(AppConstants.FINDING_LIBRARY_SHEET);

            if (sheet == null) {
                throw new IOException(
                        "Worksheet '" +
                                AppConstants.FINDING_LIBRARY_SHEET +
                                "' not found.");
            }

            for (int rowIndex = AppConstants.FINDING_LIBRARY_FIRST_DATA_ROW;
                 rowIndex <= sheet.getLastRowNum();
                 rowIndex++) {

                Row row = sheet.getRow(rowIndex);

                if (row == null || isRowEmpty(row)) {
                    continue;
                }

                definitions.add(mapRow(row));
            }

        }

        return definitions;
    }

    private FindingDefinition mapRow(Row row) {

        FindingDefinition definition = new FindingDefinition();

        definition.setFindingName(
                getString(row.getCell(
                        FindingLibraryColumn.FINDING_NAME.getIndex())));

        definition.setDefaultTitle(
                getString(row.getCell(
                        FindingLibraryColumn.DEFAULT_TITLE.getIndex())));

        definition.setCategory(
                getString(row.getCell(
                        FindingLibraryColumn.CATEGORY.getIndex())));

        definition.setVulnerabilityType(
                getString(row.getCell(
                        FindingLibraryColumn.VULNERABILITY_TYPE.getIndex())));

        definition.setSeverity(parseSeverity(
                getString(row.getCell(
                        FindingLibraryColumn.SEVERITY.getIndex()))));

        definition.setCvssScore(
                getNumeric(row.getCell(
                        FindingLibraryColumn.CVSS_SCORE.getIndex())));

        definition.setCwe(
                getString(row.getCell(
                        FindingLibraryColumn.CWE.getIndex())));

        definition.setOwasp(
                getString(row.getCell(
                        FindingLibraryColumn.OWASP.getIndex())));

        definition.setDescription(
                getString(row.getCell(
                        FindingLibraryColumn.DESCRIPTION.getIndex())));

        definition.setImpact(
                getString(row.getCell(
                        FindingLibraryColumn.IMPACT.getIndex())));

        definition.setLikelihood(
                getString(row.getCell(
                        FindingLibraryColumn.LIKELIHOOD.getIndex())));

        definition.setRecommendation(
                getString(row.getCell(
                        FindingLibraryColumn.RECOMMENDATION.getIndex())));

        String refs = getString(row.getCell(
                FindingLibraryColumn.REFERENCES.getIndex()));

        if (!refs.isBlank()) {

            String[] split = refs.split("\\r?\\n");

            for (String reference : split) {
                definition.addReference(reference);
            }
        }

        return definition;
    }

    private String getString(Cell cell) {

        if (cell == null) {
            return "";
        }

        return switch (cell.getCellType()) {

            case STRING ->
                    cell.getStringCellValue().trim();

            case NUMERIC ->
                    String.valueOf(cell.getNumericCellValue());

            case BOOLEAN ->
                    String.valueOf(cell.getBooleanCellValue());

            case FORMULA ->
                    cell.getCellFormula();

            default ->
                    "";
        };
    }

    private double getNumeric(Cell cell) {

        if (cell == null) {
            return 0.0;
        }

        return switch (cell.getCellType()) {

            case NUMERIC ->
                    cell.getNumericCellValue();

            case STRING -> {

                try {
                    yield Double.parseDouble(cell.getStringCellValue());
                } catch (Exception ex) {
                    yield 0.0;
                }

            }

            default ->
                    0.0;
        };
    }

    private Severity parseSeverity(String value) {

        if (value == null || value.isBlank()) {
            return Severity.INFORMATIONAL;
        }

        try {
            return Severity.valueOf(value.trim().toUpperCase());
        } catch (Exception ex) {
            return Severity.INFORMATIONAL;
        }
    }

    private boolean isRowEmpty(Row row) {

        for (Cell cell : row) {

            if (cell != null &&
                    cell.getCellType() != CellType.BLANK &&
                    !getString(cell).isBlank()) {

                return false;
            }

        }

        return true;
    }

}