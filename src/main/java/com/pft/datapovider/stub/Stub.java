package com.pft.datapovider.stub;

import com.pft.core.domain.GetDailyWip;
import com.pft.core.domain.GetLeadTimeDistribution;
import com.pft.core.domain.LeadTimeDistribution;

import javax.inject.Named;

@Named
public class Stub implements GetLeadTimeDistribution, GetDailyWip {

    private LeadTimeDistribution leadTimeDistribution;
    private Double averageDailyWIP;

    @Override
    public LeadTimeDistribution getLeadTimeDistribution() {
        return leadTimeDistribution;
    }

    @Override
    public double getAverageDailyWIP() {
        return averageDailyWIP;
    }
}
