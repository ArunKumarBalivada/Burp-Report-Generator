package com.arun.burpreport.model.enums;

public enum FindingLibraryColumn {

    FINDING_NAME(0),
    DEFAULT_TITLE(1),
    CATEGORY(2),
    VULNERABILITY_TYPE(3),
    SEVERITY(4),
    CVSS_SCORE(5),
    CWE(6),
    OWASP(7),
    DESCRIPTION(8),
    IMPACT(9),
    LIKELIHOOD(10),
    RECOMMENDATION(11),
    REFERENCES(12);

    private final int index;

    FindingLibraryColumn(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}