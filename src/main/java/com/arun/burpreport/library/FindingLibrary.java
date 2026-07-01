package com.arun.burpreport.library;

import com.arun.burpreport.model.FindingDefinition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FindingLibrary {

    private final List<FindingDefinition> definitions = new ArrayList<>();

    public void setDefinitions(List<FindingDefinition> definitions) {

        this.definitions.clear();

        if (definitions != null) {
            this.definitions.addAll(definitions);
        }

    }

    public List<FindingDefinition> getAll() {

        return Collections.unmodifiableList(definitions);

    }

    public List<FindingDefinition> search(String keyword) {

        if (keyword == null || keyword.isBlank()) {
            return getAll();
        }

        String search = keyword.toLowerCase();

        return definitions.stream()
                .filter(f ->
                        f.getFindingName().toLowerCase().contains(search)
                                || f.getCategory().toLowerCase().contains(search)
                                || f.getVulnerabilityType().toLowerCase().contains(search))
                .collect(Collectors.toList());

    }

    public Optional<FindingDefinition> findByName(String name) {

        return definitions.stream()
                .filter(f -> f.getFindingName().equalsIgnoreCase(name))
                .findFirst();

    }

    public int size() {

        return definitions.size();

    }

    public boolean isEmpty() {

        return definitions.isEmpty();

    }

    public void clear() {

        definitions.clear();

    }

}