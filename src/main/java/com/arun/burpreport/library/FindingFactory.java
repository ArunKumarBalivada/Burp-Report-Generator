package com.arun.burpreport.library;

import com.arun.burpreport.model.Finding;
import com.arun.burpreport.model.FindingDefinition;

import java.util.ArrayList;

public final class FindingFactory {

    private FindingFactory() {
        // Prevent instantiation
    }

    /**
     * Creates a new project Finding from a FindingDefinition.
     *
     * @param definition Finding definition from the Knowledge Base
     * @return Finding instance
     */
    public static Finding createFinding(FindingDefinition definition) {

        if (definition == null) {
            throw new IllegalArgumentException("FindingDefinition cannot be null.");
        }

        Finding finding = new Finding();

        // General Information
        finding.setTitle(definition.getDefaultTitle());

        // Risk Information
        finding.setSeverity(definition.getSeverity());
        finding.setCvssScore(definition.getCvssScore());

        // Standards
        finding.setCwe(definition.getCwe());
        finding.setOwasp(definition.getOwasp());

        // Report Content
        finding.setDescription(definition.getDescription());
        finding.setImpact(definition.getImpact());
        finding.setLikelihood(definition.getLikelihood());
        finding.setRecommendation(definition.getRecommendation());

        // References
        finding.setReferences(new ArrayList<>(definition.getReferences()));

        // Project-specific fields (filled by tester later)
        finding.setAffectedComponents("");
        finding.setProofOfConcept("");

        return finding;
    }
}