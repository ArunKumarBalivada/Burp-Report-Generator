package com.arun.burpreport.model;

import com.arun.burpreport.common.AppConstants;
import com.arun.burpreport.model.enums.Classification;
import com.arun.burpreport.model.enums.Environment;
import com.arun.burpreport.model.enums.FindingType;
import com.arun.burpreport.model.enums.ProjectStatus;
import com.arun.burpreport.model.enums.TestingApproach;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Project {

    private final String projectId;

    private String applicationName;
    private String vendorName;
    private String consultant;
    private Classification classification;
    private LocalDate startDate;
    private LocalDate endDate;
    private Environment environment;
    private TestingApproach testingApproach;
    private FindingType findingType;
    private ProjectStatus status;

    private List<String> scopeItems;
    private String assumptions;

    private List<Finding> findings;

    public Project() {

        this.projectId = UUID.randomUUID().toString();

        this.vendorName = AppConstants.DEFAULT_VENDOR;
        this.classification = Classification.CONFIDENTIAL;
        this.status = ProjectStatus.NEW;

        this.startDate = LocalDate.now();
        this.endDate = LocalDate.now();

        this.scopeItems = new ArrayList<>();
        this.findings = new ArrayList<>();
    }

    public String getProjectId() {
        return projectId;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public String getConsultant() {
        return consultant;
    }

    public void setConsultant(String consultant) {
        this.consultant = consultant;
    }

    public Classification getClassification() {
        return classification;
    }

    public void setClassification(Classification classification) {
        this.classification = classification;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public TestingApproach getTestingApproach() {
        return testingApproach;
    }

    public void setTestingApproach(TestingApproach testingApproach) {
        this.testingApproach = testingApproach;
    }

    public FindingType getFindingType() {
        return findingType;
    }

    public void setFindingType(FindingType findingType) {
        this.findingType = findingType;
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    public List<String> getScopeItems() {
        return scopeItems;
    }

    public void setScopeItems(List<String> scopeItems) {
        this.scopeItems = scopeItems;
    }

    public String getAssumptions() {
        return assumptions;
    }

    public void setAssumptions(String assumptions) {
        this.assumptions = assumptions;
    }

    public List<Finding> getFindings() {
        return findings;
    }

    public void setFindings(List<Finding> findings) {
        this.findings = findings;
    }

    public void addScopeItem(String scopeItem) {
        scopeItems.add(scopeItem);
    }

    public void removeScopeItem(String scopeItem) {
        scopeItems.remove(scopeItem);
    }

    public void addFinding(Finding finding) {
        findings.add(finding);
    }

    public void removeFinding(Finding finding) {
        findings.remove(finding);
    }

    public int getTotalFindings() {
        return findings.size();
    }

    @Override
    public String toString() {
        return applicationName != null && !applicationName.isBlank()
                ? applicationName
                : "Untitled Project";
    }
}