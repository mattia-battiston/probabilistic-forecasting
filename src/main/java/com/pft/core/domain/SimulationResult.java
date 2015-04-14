package com.pft.core.domain;

import java.util.ArrayList;
import java.util.Collection;

public class SimulationResult {

    private Collection<Integer> allResults = new ArrayList<>();

    public Collection<Integer> getAllResults() {
        return allResults;
    }

    public void record(int result) {
        allResults.add(result);
    }
}
