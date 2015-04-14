package com.pft.core.domain;

public class ValueProbability {

    private Integer value;
    private Integer probability;

    public ValueProbability(Integer value, Integer probability) {
        this.value = value;
        this.probability = probability;
    }

    public Integer getValue() {
        return value;
    }

    public Integer getProbability() {
        return probability;
    }

}
