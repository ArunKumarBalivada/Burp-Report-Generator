package com.arun.burpreport.model;

import com.arun.burpreport.model.enums.Severity;

import java.util.ArrayList;
import java.util.List;

public class FindingDefinition {

    private String findingName;
    private String defaultTitle;
    private String category;
    private String vulnerabilityType;

    private Severity severity;
    private double cvssScore;

    private String cwe;
    private String owasp;

    private String description;
    private String impact;
    private String likelihood;

    private String recommendation;

    private List<String> references;

    public FindingDefinition() {
        this.references = new ArrayList<>();
    }

    public String getFindingName() {
        return findingName;
    }

    public void setFindingName(String findingName) {
        this.findingName = findingName;
    }

    public String getDefaultTitle() {
        return defaultTitle;
    }

    public void setDefaultTitle(String defaultTitle) {
        this.defaultTitle = defaultTitle;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getVulnerabilityType() {
        return vulnerabilityType;
    }

    public void setVulnerabilityType(String vulnerabilityType) {
        this.vulnerabilityType = vulnerabilityType;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public double getCvssScore() {
        return cvssScore;
    }

    public void setCvssScore(double cvssScore) {
        this.cvssScore = cvssScore;
    }

    public String getCwe() {
        return cwe;
    }

    public void setCwe(String cwe) {
        this.cwe = cwe;
    }

    public String getOwasp() {
        return owasp;
    }

    public void setOwasp(String owasp) {
        this.owasp = owasp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    public String getLikelihood() {
        return likelihood;
    }

    public void setLikelihood(String likelihood) {
        this.likelihood = likelihood;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public List<String> getReferences() {
        return references;
    }

    public void setReferences(List<String> references) {
        this.references = (references != null)
                ? new ArrayList<>(references)
                : new ArrayList<>();
    }

    public void addReference(String reference) {
        if (reference != null && !reference.isBlank()) {
            references.add(reference.trim());
        }
    }

    public void clearReferences() {
        references.clear();
    }

    @Override
    public String toString() {

        if (defaultTitle != null && !defaultTitle.isBlank()) {
            return defaultTitle;
        }

        return findingName;
    }
}