package com.pft.entrypoint.rest.leadtime;

import com.pft.core.domain.GetDailyWip;
import com.pft.core.domain.GetLeadTimeDistribution;
import com.pft.core.domain.LeadTimeDistribution;
import com.pft.core.usecase.simulation.LeadTimeSimulation;
import com.pft.entrypoint.rest.leadtime.dto.RequestDto;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
public class LeadTimeSimulationEndpoint {

    private final LeadTimeSimulation leadTimeSimulation;
    private final GetDailyWip getDailyWip;
    private final GetLeadTimeDistribution getLeadTimeDistribution;

    @Inject
    public LeadTimeSimulationEndpoint(final LeadTimeSimulation leadTimeSimulation, GetDailyWip getDailyWip, GetLeadTimeDistribution getLeadTimeDistribution) {
        this.leadTimeSimulation = leadTimeSimulation;
        this.getDailyWip = getDailyWip;
        this.getLeadTimeDistribution = getLeadTimeDistribution;
    }

    @RequestMapping(value = "/rest/leadtime", method = RequestMethod.POST)
    public String leadtime(@RequestBody RequestDto requestDto) {

        double averageDailyWIP = getDailyWip.getAverageDailyWIP();
        LeadTimeDistribution leadTimeDistribution = getLeadTimeDistribution.getLeadTimeDistribution();

        leadTimeSimulation.forecast(leadTimeDistribution, averageDailyWIP, requestDto.getNoOfStories(), requestDto.getNoOfSamples());
        return "done!";
    }

}

//{
//        "noOfSamples" : 3,
//        "noOfStories" : 3
//        }
//