package com.pft.core.domain;

public class ValueProbability {

    private int value;
    private int probability;

    public ValueProbability(int value, int probability) {
        this.value = value;
        this.probability = probability;
    }

    public int getValue() {
        return value;
    }

    public int getProbability() {
        return probability;
    }

}
