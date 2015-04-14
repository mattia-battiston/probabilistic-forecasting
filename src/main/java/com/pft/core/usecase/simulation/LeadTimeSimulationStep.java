package com.pft.core.usecase.simulation;

import com.pft.core.domain.LeadTimeDistribution;

import javax.inject.Named;

@Named
public class LeadTimeSimulationStep {
    public int simulate(LeadTimeDistribution leadTimeDistribution, double averageDailyWip, int numberOfStories) {
        return 0;
    }
}
