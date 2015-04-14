package com.pft.core.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LeadTimeDistribution {

    private List<ValueProbability> valueProbabilities = new ArrayList<>();

    public void record(ValueProbability valueProbability) {
        valueProbabilities.add(valueProbability);
    }

    public Stream<ValueProbability> getValueProbabilities() {
        return valueProbabilities.stream();
    }

    // TODO
    public int extractRandomLeadTime() {
        return 0;
    }
}
