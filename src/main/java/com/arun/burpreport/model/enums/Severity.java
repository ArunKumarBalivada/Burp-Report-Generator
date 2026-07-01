package com.arun.burpreport.model.enums;

public enum Severity {

    CRITICAL("Critical"),
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low"),
    INFORMATIONAL("Informational");

    private final String displayName;

    Severity(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}