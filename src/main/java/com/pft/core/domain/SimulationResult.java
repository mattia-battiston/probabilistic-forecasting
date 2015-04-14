package com.pft.core.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SimulationResult {

    private Collection<Double> allResults = new ArrayList<>();

    public Collection<Double> getAllResults() {
        return allResults;
    }

    public List<ValueProbability> getResultProbabilities() {
        return null;
    }

    public void record(double result) {
        allResults.add(result);
    }

    public void calculateResultProbabilities() {
//        Map<Double, Long> occurrences = allResults.stream().collect(groupingBy(o -> o, counting()));


    }
}
