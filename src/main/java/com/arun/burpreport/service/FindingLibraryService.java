package com.arun.burpreport.service;

import com.arun.burpreport.library.FindingLibrary;
import com.arun.burpreport.model.FindingDefinition;

import java.util.List;
import java.util.Optional;

public class FindingLibraryService {

    private final FindingLibrary findingLibrary;

    public FindingLibraryService(FindingLibrary findingLibrary) {
        this.findingLibrary = findingLibrary;
    }

    public List<FindingDefinition> getAllFindings() {
        return findingLibrary.getAll();
    }

    public List<FindingDefinition> search(String keyword) {
        return findingLibrary.search(keyword);
    }

    public Optional<FindingDefinition> findByName(String name) {
        return findingLibrary.findByName(name);
    }

    public boolean isEmpty() {
        return findingLibrary.isEmpty();
    }

    public int size() {
        return findingLibrary.size();
    }
}