package com.pft.core.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SimulationResult {

    private Collection<Double> allResults = new ArrayList<>();
    private List<ValueProbability> resultProbabilities = new ArrayList<>();

    public Collection<Double> getAllResults() {
        return allResults;
    }

    public List<ValueProbability> getResultProbabilities() {
        return resultProbabilities;
    }

    public void record(double result) {
        allResults.add(result);
    }

    public void calculateResultProbabilities() {
        Map<Double, Long> individualResults = allResults.stream().collect(Collectors.groupingBy(o -> o, Collectors.counting()));

        Map<Integer, Long> groupedResults = new HashMap<>();

        for (Map.Entry<Double, Long> individualResult : individualResults.entrySet()) {
            Double days = individualResult.getKey();
            Long numberOfOccurrences = individualResult.getValue();

            Integer bucket = (int)(days - (days%5 )) + 5;
            if(groupedResults.containsKey(bucket)) {
                Long existingOccurences = groupedResults.get(bucket);
                Long newOccurrences = existingOccurences + numberOfOccurrences;
                groupedResults.put(bucket, newOccurrences);
            } else {
                groupedResults.put(bucket, numberOfOccurrences);
            }
        }

        convertToProbabilities(groupedResults);
    }

    private void convertToProbabilities(Map<Integer, Long> resultOccurrences) {
        double numberOfSamples = getTotalNumberOfSamples(resultOccurrences);
        for (Map.Entry<Integer, Long> resultOccurency : resultOccurrences.entrySet()) {
            Integer result = resultOccurency.getKey();
            double occurrencies = resultOccurency.getValue();
            int probability = (int) Math.round(occurrencies / numberOfSamples * 100);
            resultProbabilities.add(new ValueProbability(result, probability));
        }
    }

    private Long getTotalNumberOfSamples(Map<Integer, Long> groupedResults) {
        return groupedResults.values().stream().mapToLong(o -> o).sum();
    }

}
