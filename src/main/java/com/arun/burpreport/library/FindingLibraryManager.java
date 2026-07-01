package com.arun.burpreport.library;

import com.arun.burpreport.model.FindingDefinition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FindingLibraryManager {

    private static final FindingLibraryManager INSTANCE =
            new FindingLibraryManager();

    private final List<FindingDefinition> definitions =
            new ArrayList<>();

    private FindingLibraryManager() {
    }

    public static FindingLibraryManager getInstance() {
        return INSTANCE;
    }

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
                                || f.getDefaultTitle().toLowerCase().contains(search)
                                || f.getCategory().toLowerCase().contains(search)
                                || f.getVulnerabilityType().toLowerCase().contains(search))
                .collect(Collectors.toList());

    }

    public Optional<FindingDefinition> findByName(String name) {

        return definitions.stream()
                .filter(f -> f.getFindingName().equalsIgnoreCase(name))
                .findFirst();

    }

    public boolean isEmpty() {
        return definitions.isEmpty();
    }

    public int size() {
        return definitions.size();
    }

    public void clear() {
        definitions.clear();
    }

}