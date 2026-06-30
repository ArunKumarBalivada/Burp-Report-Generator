package com.arun.burpreport.service;

import com.arun.burpreport.model.Project;

public class ProjectManager {

    private static final ProjectManager INSTANCE = new ProjectManager();

    private Project currentProject;

    private ProjectManager() {
    }

    public static ProjectManager getInstance() {
        return INSTANCE;
    }

    public Project getCurrentProject() {
        return currentProject;
    }

    public void setCurrentProject(Project project) {
        this.currentProject = project;
    }

    public boolean hasOpenProject() {
        return currentProject != null;
    }

    public void closeProject() {
        currentProject = null;
    }

    public void createNewProject() {
        currentProject = new Project();
    }

}