package com.pft.datapovider.stub;

import com.pft.core.domain.GetDailyWip;
import com.pft.core.domain.GetLeadTimeDistribution;
import com.pft.core.domain.LeadTimeDistribution;
import com.pft.core.domain.ValueProbability;

import javax.inject.Named;

@Named
public class Stub implements GetLeadTimeDistribution, GetDailyWip {


    @Override
    public LeadTimeDistribution getLeadTimeDistribution() {
        LeadTimeDistribution leadTimeDistribution = new LeadTimeDistribution();
        leadTimeDistribution.record(new ValueProbability(1,  3));
        leadTimeDistribution.record(new ValueProbability(2,  5));
        leadTimeDistribution.record(new ValueProbability(3,  8));
        leadTimeDistribution.record(new ValueProbability(4,  17));
        leadTimeDistribution.record(new ValueProbability(5,  13));
        leadTimeDistribution.record(new ValueProbability(6,  8));
        leadTimeDistribution.record(new ValueProbability(7,  5));
        leadTimeDistribution.record(new ValueProbability(8,  3));
        leadTimeDistribution.record(new ValueProbability(9,  11));
        leadTimeDistribution.record(new ValueProbability(10, 11));
        leadTimeDistribution.record(new ValueProbability(11, 6));
        leadTimeDistribution.record(new ValueProbability(12, 5));
        leadTimeDistribution.record(new ValueProbability(13, 3));
        leadTimeDistribution.record(new ValueProbability(14, 2));
        return leadTimeDistribution;
    }

    @Override
    public double getAverageDailyWIP() {
        return 4.3;
    }
}
