package com.arun.burpreport.model.enums;

public enum FindingType {

    WEB_APPLICATION("Web Application"),
    API("API"),
    MOBILE_ANDROID("Mobile - Android"),
    MOBILE_IOS("Mobile - iOS"),
    THICK_CLIENT("Thick Client"),
    CLOUD("Cloud"),
    AI_LLM("AI / LLM"),
    INFRASTRUCTURE("Infrastructure"),
    NETWORK("Network");

    private final String displayName;

    FindingType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}