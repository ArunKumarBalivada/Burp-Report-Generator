package com.arun.burpreport.settings;

public class SettingsService {

    private Settings settings;

    public SettingsService() {
        this.settings = new Settings();
    }

    /**
     * Returns the current application settings.
     */
    public Settings getSettings() {
        return settings;
    }

    /**
     * Replaces the current settings.
     */
    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    /**
     * Returns true if mandatory settings are configured.
     */
    public boolean isConfigured() {

        return settings.getWorkspacePath() != null
                && !settings.getWorkspacePath().isBlank()
                && settings.getWordTemplatePath() != null
                && !settings.getWordTemplatePath().isBlank()
                && settings.getFindingLibraryPath() != null
                && !settings.getFindingLibraryPath().isBlank();
    }

    /**
     * Resets all settings to default values.
     */
    public void reset() {
        settings = new Settings();
    }

}