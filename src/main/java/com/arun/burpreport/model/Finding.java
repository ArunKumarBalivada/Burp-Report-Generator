package com.arun.burpreport.model;

import com.arun.burpreport.model.enums.FindingStatus;
import com.arun.burpreport.model.enums.Severity;

public class Finding {

    private String id;
    private String title;
    private Severity severity;
    private Double cvssScore;
    private String cwe;
    private String owasp;
    private String affectedComponents;
    private String description;
    private String impact;
    private String likelihood;
    private String recommendation;
    private String references;
    private String proofOfConcept;
    private FindingStatus status;

    public Finding() {
        this.status = FindingStatus.DRAFT;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public Double getCvssScore() {
        return cvssScore;
    }

    public void setCvssScore(Double cvssScore) {
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

    public String getAffectedComponents() {
        return affectedComponents;
    }

    public void setAffectedComponents(String affectedComponents) {
        this.affectedComponents = affectedComponents;
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

    public String getReferences() {
        return references;
    }

    public void setReferences(String references) {
        this.references = references;
    }

    public String getProofOfConcept() {
        return proofOfConcept;
    }

    public void setProofOfConcept(String proofOfConcept) {
        this.proofOfConcept = proofOfConcept;
    }

    public FindingStatus getStatus() {
        return status;
    }

    public void setStatus(FindingStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return id + " - " + title;
    }
}