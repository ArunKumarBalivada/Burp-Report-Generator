package com.arun.burpreport.model.enums;

public enum ProjectStatus {

    NEW("New"),
    IN_PROGRESS("In Progress"),
    REVIEW("Review"),
    COMPLETED("Completed"),
    ARCHIVED("Archived");

    private final String displayName;

    ProjectStatus(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}