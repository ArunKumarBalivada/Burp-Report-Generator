package com.arun.burpreport.service;

import com.arun.burpreport.model.Finding;
import com.arun.burpreport.model.Project;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;

public final class ProjectService {

    private static final ProjectService INSTANCE = new ProjectService();

    private final ProjectJsonService projectJsonService;

    private Project currentProject;

    private ProjectService() {

        this.projectJsonService = new ProjectJsonService();
        this.currentProject = new Project();

    }

    public static ProjectService getInstance() {
        return INSTANCE;
    }

    /*
     * ==========================================================
     * Project Management
     * ==========================================================
     */

    public Project newProject() {

        currentProject = new Project();
        return currentProject;

    }

    public Project getCurrentProject() {
        return currentProject;
    }

    public void setCurrentProject(Project project) {

        if (project == null) {
            throw new IllegalArgumentException("Project cannot be null.");
        }

        currentProject = project;

    }

    public void clearProject() {

        currentProject = new Project();

    }

    public boolean hasProject() {

        return currentProject != null;

    }

    /*
     * ==========================================================
     * Finding Management
     * ==========================================================
     */

    public void addFinding(Finding finding) {

        if (finding == null) {
            return;
        }

        currentProject.addFinding(finding);

    }

    public void removeFinding(Finding finding) {

        if (finding == null) {
            return;
        }

        currentProject.removeFinding(finding);

    }

    public List<Finding> getFindings() {

        if (currentProject == null) {
            return Collections.emptyList();
        }

        return currentProject.getFindings();

    }

    public int getFindingCount() {

        if (currentProject == null) {
            return 0;
        }

        return currentProject.getTotalFindings();

    }

    /*
     * ==========================================================
     * Persistence
     * ==========================================================
     */

    public void saveProject(Path file) throws IOException {

        if (file == null) {
            throw new IllegalArgumentException("File cannot be null.");
        }

        projectJsonService.saveProject(currentProject, file);

    }

    public Project openProject(Path file) throws IOException {

        if (file == null) {
            throw new IllegalArgumentException("File cannot be null.");
        }

        currentProject = projectJsonService.loadProject(file);

        return currentProject;

    }

}