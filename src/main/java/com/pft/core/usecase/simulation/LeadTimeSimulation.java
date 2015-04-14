package com.pft.core.usecase.simulation;

import com.pft.core.domain.InvalidDistributionException;
import com.pft.core.domain.LeadTimeDistribution;
import com.pft.core.domain.SimulationResult;

import static java.util.stream.IntStream.range;

public class LeadTimeSimulation {

    private final LeadTimeSimulationStep leadTimeSimulationStep;

    public LeadTimeSimulation(LeadTimeSimulationStep leadTimeSimulationStep) {
        this.leadTimeSimulationStep = leadTimeSimulationStep;
    }

    public SimulationResult forecast(final LeadTimeDistribution leadTimeDistribution, final double averageDailyWIP, final int numberOfStories, final int numberOfSamples) {
        int totalProbability = leadTimeDistribution.getValueProbabilities().mapToInt(vp -> vp.getProbability()).sum();

        if (totalProbability != 100) {
            throw new InvalidDistributionException("Require probability to add up to 100% but instead got: " + totalProbability);
        }

        SimulationResult simulationResult = new SimulationResult();

        range(0, numberOfSamples).map(
            sampleNumber -> leadTimeSimulationStep.simulate(leadTimeDistribution, averageDailyWIP, numberOfStories)
        ).forEach(
            simulationResult::record
        );

        return simulationResult;
    }

}
