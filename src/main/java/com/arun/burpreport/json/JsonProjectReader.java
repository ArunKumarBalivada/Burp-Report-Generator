package com.arun.burpreport.json;

import com.arun.burpreport.model.Project;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class JsonProjectReader {

    private static final Gson GSON = new Gson();

    private JsonProjectReader() {
        // Prevent instantiation
    }

    public static Project read(Path file) throws IOException {

        if (file == null) {
            throw new IllegalArgumentException("File cannot be null.");
        }

        try (FileReader reader = new FileReader(file.toFile())) {
            return GSON.fromJson(reader, Project.class);
        }

    }

}