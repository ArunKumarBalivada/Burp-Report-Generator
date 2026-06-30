package com.arun.burpreport.model.enums;

public enum TestingApproach {

    BLACK_BOX("Black Box"),
    GREY_BOX("Grey Box"),
    WHITE_BOX("White Box");

    private final String displayName;

    TestingApproach(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}