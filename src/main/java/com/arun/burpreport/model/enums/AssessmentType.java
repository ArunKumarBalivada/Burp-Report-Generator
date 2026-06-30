package com.arun.burpreport.model.enums;

public enum AssessmentType {

    INITIAL_ASSESSMENT("Initial Assessment"),
    RETEST("Retest"),
    VALIDATION("Validation");

    private final String displayName;

    AssessmentType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}