package com.arun.burpreport.settings;

import com.arun.burpreport.common.AppConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SettingsPersistence {

    private final ObjectMapper objectMapper;

    public SettingsPersistence() {

        objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

    }

    /**
     * Saves the application settings.
     */
    public void save(Settings settings) throws IOException {

        Path settingsFile = getSettingsFile(settings);

        Files.createDirectories(settingsFile.getParent());

        objectMapper.writeValue(settingsFile.toFile(), settings);

    }

    /**
     * Loads application settings.
     */
    public Settings load(String workspacePath) throws IOException {

        Path settingsFile = getSettingsFile(workspacePath);

        if (!Files.exists(settingsFile)) {
            return new Settings();
        }

        return objectMapper.readValue(
                settingsFile.toFile(),
                Settings.class
        );

    }

    /**
     * Returns true if settings.json exists.
     */
    public boolean exists(String workspacePath) {

        return Files.exists(getSettingsFile(workspacePath));

    }

    /**
     * Deletes settings.json.
     */
    public void delete(String workspacePath) throws IOException {

        Files.deleteIfExists(getSettingsFile(workspacePath));

    }

    /**
     * Returns the full path to settings.json.
     */
    private Path getSettingsFile(Settings settings) {

        return Path.of(settings.getWorkspacePath())
                .resolve(AppConstants.CONFIG_FOLDER)
                .resolve(AppConstants.SETTINGS_FILE);

    }

    /**
     * Returns the full path to settings.json.
     */
    private Path getSettingsFile(String workspacePath) {

        return Path.of(workspacePath)
                .resolve(AppConstants.CONFIG_FOLDER)
                .resolve(AppConstants.SETTINGS_FILE);

    }

}