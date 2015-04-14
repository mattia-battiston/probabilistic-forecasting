package com.pft.core.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
        convertToProbabilities(groupedResults);
    }

    private Function<Double, Integer> numberOfDaysInclusive(int numberOfDaysToGroup) {
        return days -> {
            if (days % 5 == 0) {
                return days.intValue();
            }

            return (int) (days - (days % numberOfDaysToGroup)) + numberOfDaysToGroup;
        };
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
