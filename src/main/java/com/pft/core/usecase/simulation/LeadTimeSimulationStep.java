package com.pft.core.usecase.simulation;

import com.pft.core.domain.LeadTimeDistribution;

import javax.inject.Named;

import static java.util.stream.IntStream.range;

@Named
public class LeadTimeSimulationStep {

    public double simulate(LeadTimeDistribution leadTimeDistribution, double averageDailyWip, int numberOfStories) {
        System.out.println("Before Random Lead Time");

        int totalLeadTime = range(0, numberOfStories).map(
                storyNumber -> leadTimeDistribution.extractRandomLeadTime()
        ).peek(System.out::println).sum();

        if (lessStoriesThanWIP(averageDailyWip, numberOfStories)) {
            System.out.println("Simulate Step Result = " + totalLeadTime);
            return totalLeadTime;
        }

        double result = totalLeadTime / averageDailyWip;

        System.out.println("Simulate Step Result = " + result);

        return result;
    }

    private boolean lessStoriesThanWIP(double averageDailyWip, int numberOfStories) {
        return numberOfStories < averageDailyWip;
    }
}
