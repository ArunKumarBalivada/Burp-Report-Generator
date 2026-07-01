package com.arun.burpreport.model;

import com.arun.burpreport.model.enums.FindingStatus;
import com.arun.burpreport.model.enums.Severity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Finding {

    private final String id;

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

    private List<String> references;

    private String proofOfConcept;

    private FindingStatus status;

    public Finding() {

        this.id = UUID.randomUUID().toString();

        this.status = FindingStatus.DRAFT;

        this.references = new ArrayList<>();

    }

    public String getId() {
        return id;
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

    public List<String> getReferences() {
        return references;
    }

    public void setReferences(List<String> references) {

        this.references.clear();

        if (references != null) {
            this.references.addAll(references);
        }

    }

    public void addReference(String reference) {

        if (reference != null && !reference.isBlank()) {
            references.add(reference.trim());
        }

    }

    public void clearReferences() {
        references.clear();
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

        return title != null ? title : id;

    }

}