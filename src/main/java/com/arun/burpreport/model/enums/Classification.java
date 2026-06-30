package com.arun.burpreport.model.enums;

public enum Classification {

    CONFIDENTIAL("Confidential");

    private final String displayName;

    Classification(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}