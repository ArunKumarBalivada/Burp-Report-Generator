package com.arun.burpreport.model.enums;

public enum Environment {

    DEV("Development"),
    TEST("Test"),
    UAT("UAT"),
    STAGING("Staging"),
    PRODUCTION("Production");

    private final String displayName;

    Environment(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}