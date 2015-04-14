package com.pft.core.usecase.simulation;

import com.pft.core.domain.InvalidDistributionException;
import com.pft.core.domain.LeadTimeDistribution;
import com.pft.core.domain.SimulationResult;
import com.pft.core.domain.ValueProbability;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.stubbing.OngoingStubbing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LeadTimeSimulationTest {

    private static final int NUMBER_OF_SAMPLES = 1;
    private static final int NUMBER_OF_STORIES = 1;
    private static final double AVG_DAILY_WIP = 1.0;
    private final LeadTimeDistribution LEAD_TIME_DISTRIBUTION = new LeadTimeDistribution();

    private LeadTimeSimulationStep leadTimeSimulationStep = mock(LeadTimeSimulationStep.class);
    private LeadTimeSimulation simulation = new LeadTimeSimulation(leadTimeSimulationStep);

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() throws Exception {
        setupValidLeadTimeDistribution();
    }

    @Test
    public void distributionMustBeValid() throws Exception {
        LeadTimeDistribution leadTimeDistribution = givenALeadTimeDistributionWhoseProbabilityIsNot100Percent();

        expectedException.expect(InvalidDistributionException.class);

        simulation.forecast(leadTimeDistribution, AVG_DAILY_WIP, NUMBER_OF_STORIES, NUMBER_OF_SAMPLES);
    }

    @Test
    public void executesTheSimulationStepsAsManyTimesAsDefinedInInput() throws Exception {
        int numberOfSamples = 5;

        simulation.forecast(LEAD_TIME_DISTRIBUTION, AVG_DAILY_WIP, NUMBER_OF_STORIES, numberOfSamples);

        verify(leadTimeSimulationStep, times(numberOfSamples)).simulate(LEAD_TIME_DISTRIBUTION, AVG_DAILY_WIP, NUMBER_OF_STORIES);
    }

    @Test
    public void recordsAllResultsAndReturnsThem() throws Exception {
        int numberOfSamples = 5;
        givenTheSimulationStepsWillGenerateResults(1, 3, 5, 7, 9);

        SimulationResult simulationResult = simulation.forecast(LEAD_TIME_DISTRIBUTION, AVG_DAILY_WIP, NUMBER_OF_STORIES, numberOfSamples);

        assertThat(simulationResult.getAllResults()).containsExactly(1, 3, 5, 7, 9);
    }

    private void givenTheSimulationStepsWillGenerateResults(int... results) {
        OngoingStubbing<Integer> ongoingStubbing = when(leadTimeSimulationStep.simulate(LEAD_TIME_DISTRIBUTION, AVG_DAILY_WIP, NUMBER_OF_STORIES));

        for (int result : results) {
            ongoingStubbing = ongoingStubbing.thenReturn(result);
        }
    }

    private LeadTimeDistribution givenALeadTimeDistributionWhoseProbabilityIsNot100Percent() {
        LeadTimeDistribution distribution = new LeadTimeDistribution();
        distribution.record(new ValueProbability(1, 1));
        return distribution;
    }

    private void setupValidLeadTimeDistribution() {
        LEAD_TIME_DISTRIBUTION.record(new ValueProbability(1, 100));
    }
}