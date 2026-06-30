package com.arun.burpreport.settings;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class WorkspaceInitializer {

    public void initializeWorkspace(String workspacePath) throws IOException {

        if (workspacePath == null || workspacePath.isBlank()) {
            throw new IllegalArgumentException("Workspace path cannot be empty.");
        }

        Path workspace = Path.of(workspacePath);

        Files.createDirectories(workspace);
        Files.createDirectories(workspace.resolve("config"));
        Files.createDirectories(workspace.resolve("projects"));
        Files.createDirectories(workspace.resolve("reports"));
        Files.createDirectories(workspace.resolve("templates"));
        Files.createDirectories(workspace.resolve("logs"));
        Files.createDirectories(workspace.resolve("backups"));
        
    }
}