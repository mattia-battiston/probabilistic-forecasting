package com.pft.core.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class LeadTimeDistribution {

    private List<ValueProbability> valueProbabilities = new ArrayList<>();
    private int weightedSamples[] = new int[100];

    public Stream<ValueProbability> getValueProbabilities() {
        return valueProbabilities.stream();
    }

    public void record(ValueProbability valueProbability) {
        valueProbabilities.add(valueProbability);
        recreateSamples();
    }

    public int extractRandomLeadTime() {
        int randomIndex = createRandomIndex();
        return weightedSamples[randomIndex];
    }

    private void recreateSamples() {
        weightedSamples = new int[100];
        int index = 0;
        for (ValueProbability valueProbability : valueProbabilities) {
            int value = valueProbability.getDays();
            int probabilityPercentage = valueProbability.getProbabilityPercentage();
            for (int i = 0; i < probabilityPercentage; i++) {
                weightedSamples[index] = value;
                index++;
            }
        }
    }

    private int createRandomIndex() {
        return new Random().nextInt(100);
    }
}
