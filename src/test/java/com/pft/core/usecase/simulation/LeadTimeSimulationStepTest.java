package com.pft.core.usecase.simulation;

import com.pft.core.domain.LeadTimeDistribution;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LeadTimeSimulationStepTest {

    private final LeadTimeDistribution distribution = mock(LeadTimeDistribution.class);

    private LeadTimeSimulationStep leadTimeSimulationStep = new LeadTimeSimulationStep();

    @Test
    public void simplestScenario() throws Exception {
        when(distribution.extractRandomLeadTime()).thenReturn(1);

        double result = leadTimeSimulationStep.simulate(distribution, 1, 1);

        assertThat(result).isEqualTo(1);
    }

    @Test
    public void multipleStories() throws Exception {
        when(distribution.extractRandomLeadTime()).thenReturn(10);

        double result = leadTimeSimulationStep.simulate(distribution, 1, 2);

        assertThat(result).isEqualTo(20);
    }

    @Test
    public void multipleProbabilities() throws Exception {
        when(distribution.extractRandomLeadTime())
                .thenReturn(10)
                .thenReturn(5)
                .thenReturn(2);

        double result = leadTimeSimulationStep.simulate(distribution, 1, 3);

        assertThat(result).isEqualTo(17);
    }

    @Test
    public void wipLimitBiggerThanNumberOfStories() throws Exception {
        when(distribution.extractRandomLeadTime()).thenReturn(10);

        double result = leadTimeSimulationStep.simulate(distribution, 2, 1);

        assertThat(result).isEqualTo(10);
    }

    @Test
    public void multipleStoriesStillUnderWipLimit() throws Exception {
        when(distribution.extractRandomLeadTime())
                .thenReturn(10)
                .thenReturn(5);

        double result = leadTimeSimulationStep.simulate(distribution, 3, 2);

        assertThat(result).isEqualTo(15);
    }

    @Test
    public void doesNotRoundResults() throws Exception {
        when(distribution.extractRandomLeadTime())
                .thenReturn(10)
                .thenReturn(5)
                .thenReturn(2);

        double result = leadTimeSimulationStep.simulate(distribution, 2, 3);

        assertThat(result).isEqualTo(8.5);
    }
}