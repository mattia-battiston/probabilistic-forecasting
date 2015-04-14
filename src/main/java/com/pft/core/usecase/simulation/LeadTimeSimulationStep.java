package com.pft.core.usecase.simulation;

import com.pft.core.domain.LeadTimeDistribution;

import javax.inject.Named;

import static java.util.stream.IntStream.range;

@Named
public class LeadTimeSimulationStep {

    public double simulate(LeadTimeDistribution leadTimeDistribution, double averageDailyWip, int numberOfStories) {
        int totalLeadTime = range(0, numberOfStories).map(
                storyNumber -> leadTimeDistribution.extractRandomLeadTime()
        ).sum();

        if (lessStoriesThanWIP(averageDailyWip, numberOfStories)) {
            return totalLeadTime;
        }

        double result = totalLeadTime / averageDailyWip;

        return result;
    }

    private boolean lessStoriesThanWIP(double averageDailyWip, int numberOfStories) {
        return numberOfStories < averageDailyWip;
    }
}
