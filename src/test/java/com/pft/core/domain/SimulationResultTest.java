package com.pft.core.domain;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SimulationResultTest {

    @Test
    public void simpleScenario() throws Exception {
        SimulationResult simulationResult = new SimulationResult();
        simulationResult.record(1.0);
        simulationResult.record(1.0);
        simulationResult.record(1.0);
        simulationResult.record(1.0);
        simulationResult.record(1.0);
        simulationResult.record(1.0);
        simulationResult.record(6.0);
        simulationResult.record(6.0);
        simulationResult.record(6.0);
        simulationResult.record(11.0);

        simulationResult.calculateResultProbabilities();

        List<ValueProbability> resultProbabilities = simulationResult.getResultProbabilities();
        assertThat(resultProbabilities).extracting(o -> o.getProbabilityPercentage()).containsExactly(60, 30, 10);
        assertThat(resultProbabilities).extracting(o -> o.getDays()).containsExactly(5, 10, 15);
    }

    @Test
    public void groupsByFiveInclusive() throws Exception {
        SimulationResult simulationResult = new SimulationResult();
        simulationResult.record(1.0);
        simulationResult.record(1.0);
        simulationResult.record(1.0);
        simulationResult.record(3.0);
        simulationResult.record(3.0);
        simulationResult.record(5.0);
        simulationResult.record(6.0);
        simulationResult.record(7.0);
        simulationResult.record(9.0);
        simulationResult.record(11.0);

        simulationResult.calculateResultProbabilities();

        List<ValueProbability> resultProbabilities = simulationResult.getResultProbabilities();
        assertThat(resultProbabilities).extracting(o -> o.getProbabilityPercentage()).containsExactly(60, 30, 10);
        assertThat(resultProbabilities).extracting(o -> o.getDays()).containsExactly(5, 10, 15);
    }

}