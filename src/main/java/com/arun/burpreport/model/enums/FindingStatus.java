package com.arun.burpreport.model.enums;

public enum FindingStatus {

    DRAFT("Draft"),
    READY("Ready"),
    VERIFIED("Verified");

    private final String displayName;

    FindingStatus(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}