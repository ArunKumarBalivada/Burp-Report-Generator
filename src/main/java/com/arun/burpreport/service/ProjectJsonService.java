package com.arun.burpreport.service;

import com.arun.burpreport.json.JsonProjectReader;
import com.arun.burpreport.json.JsonProjectWriter;
import com.arun.burpreport.model.Project;

import java.io.IOException;
import java.nio.file.Path;

public class ProjectJsonService {

    public void saveProject(Project project, Path file) throws IOException {

        JsonProjectWriter.write(project, file);

    }

    public Project loadProject(Path file) throws IOException {

        return JsonProjectReader.read(file);

    }

}