package com.arun.burpreport.settings;

import com.arun.burpreport.common.AppConstants;

import java.io.Serial;
import java.io.Serializable;

public class Settings implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String workspacePath;
    private String vendorName;
    private String wordTemplatePath;
    private String findingLibraryPath;
    private String reportsFolder;
    private String reportNamingPattern;

    public Settings() {

        this.vendorName = AppConstants.DEFAULT_VENDOR;
        this.reportsFolder = AppConstants.DEFAULT_REPORTS_FOLDER;
        this.reportNamingPattern = AppConstants.DEFAULT_REPORT_NAMING;

    }

    public String getWorkspacePath() {
        return workspacePath;
    }

    public void setWorkspacePath(String workspacePath) {
        this.workspacePath = workspacePath;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getWordTemplatePath() {
        return wordTemplatePath;
    }

    public void setWordTemplatePath(String wordTemplatePath) {
        this.wordTemplatePath = wordTemplatePath;
    }

    public String getFindingLibraryPath() {
        return findingLibraryPath;
    }

    public void setFindingLibraryPath(String findingLibraryPath) {
        this.findingLibraryPath = findingLibraryPath;
    }

    public String getReportsFolder() {
        return reportsFolder;
    }

    public void setReportsFolder(String reportsFolder) {
        this.reportsFolder = reportsFolder;
    }

    public String getReportNamingPattern() {
        return reportNamingPattern;
    }

    public void setReportNamingPattern(String reportNamingPattern) {
        this.reportNamingPattern = reportNamingPattern;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "workspacePath='" + workspacePath + '\'' +
                ", vendorName='" + vendorName + '\'' +
                ", wordTemplatePath='" + wordTemplatePath + '\'' +
                ", findingLibraryPath='" + findingLibraryPath + '\'' +
                ", reportsFolder='" + reportsFolder + '\'' +
                ", reportNamingPattern='" + reportNamingPattern + '\'' +
                '}';
    }
}