package com.pft.core.domain;

import java.util.ArrayList;
import java.util.Collection;

public class SimulationResult {

    private Collection<Double> allResults = new ArrayList<>();

    public Collection<Double> getAllResults() {
        return allResults;
    }

    public void record(double result) {
        allResults.add(result);
    }
}
