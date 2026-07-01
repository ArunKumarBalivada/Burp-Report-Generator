package com.arun.burpreport.json;

import com.arun.burpreport.model.Project;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public class JsonProjectWriter {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    private JsonProjectWriter() {
        // Prevent instantiation
    }

    public static void write(Project project, Path file) throws IOException {

        if (project == null) {
            throw new IllegalArgumentException("Project cannot be null.");
        }

        if (file == null) {
            throw new IllegalArgumentException("File cannot be null.");
        }

        try (FileWriter writer = new FileWriter(file.toFile())) {
            GSON.toJson(project, writer);
        }

    }

}