package com.arun.burpreport.settings;

public class SettingsValidator {

    public void validate(Settings settings) {

        if (settings == null) {
            throw new IllegalArgumentException("Settings cannot be null.");
        }

        if (isBlank(settings.getWorkspacePath())) {
            throw new IllegalArgumentException("Please select a workspace.");
        }

        if (isBlank(settings.getVendorName())) {
            throw new IllegalArgumentException("Vendor Name is required.");
        }

        if (isBlank(settings.getWordTemplatePath())) {
            throw new IllegalArgumentException("Please select a Word template.");
        }

        if (isBlank(settings.getFindingLibraryPath())) {
            throw new IllegalArgumentException("Please select a Finding Library.");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}