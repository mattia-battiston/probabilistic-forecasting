package com.pft.core.domain;

import org.junit.Test;

import java.util.function.Predicate;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class LeadTimeDistributionTest {

    private LeadTimeDistribution distribution;

    @Test
    public void canRecordProbabilityOfLeadTimes() throws Exception {
        distribution = new LeadTimeDistribution();

        distribution.record(new ValueProbability(1, 7));

        thenTheValueHasBeenRecorded(1, 7);
    }

    private void thenTheValueHasBeenRecorded(int value, int probability) {
        Stream<ValueProbability> valueProbabilities = distribution.getValueProbabilities();
        assertThat(valueProbabilities.anyMatch(containsProbability(value, probability))).isTrue();
    }

    private Predicate<ValueProbability> containsProbability(int value, int probability) {
        return (valueProbability) -> valueProbability.getProbabilityPercentage() == probability && valueProbability.getDays() == value;
    }
}
