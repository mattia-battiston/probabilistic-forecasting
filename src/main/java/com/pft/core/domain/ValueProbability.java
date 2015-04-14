package com.pft.core.domain;

public class ValueProbability {

    private int days;
    private int probabilityPercentage;

    public ValueProbability(int days, int probabilityPercentage) {
        this.days = days;
        this.probabilityPercentage = probabilityPercentage;
    }

    public int getDays() {
        return days;
    }

    public int getProbabilityPercentage() {
        return probabilityPercentage;
    }

    @Override
    public String toString() {
        return "ValueProbability{" +
                "days=" + days +
                ", probabilityPercentage=" + probabilityPercentage +
                '}';
    }
}
