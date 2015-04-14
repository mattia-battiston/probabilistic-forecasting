package com.pft.core.domain;

import org.junit.Test;

import java.util.function.Predicate;
import java.util.stream.Stream;

import static java.util.stream.IntStream.range;
import static org.assertj.core.api.Assertions.assertThat;

public class LeadTimeDistributionTest {

    private LeadTimeDistribution distribution = new LeadTimeDistribution();;

    @Test
    public void canRecordProbabilityOfLeadTimes() throws Exception {
        distribution.record(new ValueProbability(1, 7));

        thenTheValueHasBeenRecorded(1, 7);
    }

    @Test
    public void simplestExtraction() throws Exception {
        distribution.record(new ValueProbability(123, 100));

        int leadTime = distribution.extractRandomLeadTime();

        assertThat(leadTime).isEqualTo(123);
    }

    @Test
    public void extractALeadTimeFollowingTheDistribution() throws Exception {
        distribution.record(new ValueProbability(1, 20));
        distribution.record(new ValueProbability(2, 20));
        distribution.record(new ValueProbability(3, 20));
        distribution.record(new ValueProbability(4, 20));
        distribution.record(new ValueProbability(5, 20));

        range(0, 100).forEach(i -> {
            int leadTime = distribution.extractRandomLeadTime();
            assertThat(leadTime).isIn(1, 2, 3, 4, 5);
        });
    }

    private void thenTheValueHasBeenRecorded(int value, int probability) {
        Stream<ValueProbability> valueProbabilities = distribution.getValueProbabilities();
        assertThat(valueProbabilities.anyMatch(containsProbability(value, probability))).isTrue();
    }

    private Predicate<ValueProbability> containsProbability(int value, int probability) {
        return (valueProbability) -> valueProbability.getProbabilityPercentage() == probability && valueProbability.getDays() == value;
    }
}
