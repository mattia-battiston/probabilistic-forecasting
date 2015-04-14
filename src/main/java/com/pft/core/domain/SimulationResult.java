package com.pft.core.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class SimulationResult {

    public static final int NUMBER_OF_DAYS_TO_GROUP = 5;
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
        Map<Integer, Long> groupedResults = allResults.stream().collect(Collectors.groupingBy(numberOfDaysInclusive(NUMBER_OF_DAYS_TO_GROUP), Collectors.counting()));

        Long totalNumberOfSamples = getTotalNumberOfSamples(groupedResults);

        resultProbabilities = convertToProbabilities(groupedResults, totalNumberOfSamples);
    }

    private List<ValueProbability> convertToProbabilities(Map<Integer, Long> groupedResults, Long totalNumberOfSamples) {
        return groupedResults.entrySet().stream().map(entry -> {
            Integer result = entry.getKey();
            double occurrencies = entry.getValue();
            int probability = (int) Math.round(occurrencies / totalNumberOfSamples * 100);
            return new ValueProbability(result, probability);
        }).collect(toList());
    }

    private Function<Double, Integer> numberOfDaysInclusive(int numberOfDaysToGroup) {
        return days -> {
            if (days % 5 == 0) {
                return days.intValue();
            }

            return (int) (days - (days % numberOfDaysToGroup)) + numberOfDaysToGroup;
        };
    }

    private Long getTotalNumberOfSamples(Map<Integer, Long> groupedResults) {
        return groupedResults.values().stream().mapToLong(o -> o).sum();
    }

}
