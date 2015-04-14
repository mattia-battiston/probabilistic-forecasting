package com.pft.entrypoint.rest.leadtime;

import com.pft.core.domain.GetDailyWip;
import com.pft.core.domain.GetLeadTimeDistribution;
import com.pft.core.domain.LeadTimeDistribution;
import com.pft.core.usecase.simulation.LeadTimeSimulation;
import com.pft.entrypoint.rest.leadtime.dto.RequestDto;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LeadTimeSimulationEndpointTest {

    private static final int NO_OF_STORIES = 2;
    private static final int NO_OF_SAMPLES = 3;
    private static final double WIP = 9D;
    private LeadTimeSimulation leadTimeSimulation = mock(LeadTimeSimulation.class);
    private GetDailyWip getDailyWip = mock(GetDailyWip.class);
    private GetLeadTimeDistribution getLeadTimeDistribution = mock(GetLeadTimeDistribution.class);
    private LeadTimeSimulationEndpoint leadTimeSimulationEndpoint = new LeadTimeSimulationEndpoint(leadTimeSimulation, getDailyWip, getLeadTimeDistribution);
    private final RequestDto requestDto = new RequestDto(NO_OF_STORIES, NO_OF_SAMPLES);
    private final LeadTimeDistribution expectLeadTimeDistribution = new LeadTimeDistribution();

    @Test
    public void canGetWip() throws Exception {
        leadTimeSimulationEndpoint.leadtime(requestDto);
        verify(getDailyWip).getAverageDailyWIP();
    }

    @Test
    public void canGetLeadTimeDistribution() throws Exception {
        leadTimeSimulationEndpoint.leadtime(requestDto);
        verify(getLeadTimeDistribution).getLeadTimeDistribution();
    }

    @Test
    public void callsUseCaseWithCorrectValuesFromRequestAndDataProviders() throws Exception {
        givenLeadTime();
        givenDailyWip();

        leadTimeSimulationEndpoint.leadtime(requestDto);
        verify(leadTimeSimulation).forecast(expectLeadTimeDistribution, WIP, NO_OF_STORIES, NO_OF_SAMPLES);
    }

    private void givenLeadTime() {
        when(getLeadTimeDistribution.getLeadTimeDistribution()).thenReturn(expectLeadTimeDistribution);
    }

    private void givenDailyWip() {
        when(getDailyWip.getAverageDailyWIP()).thenReturn(WIP);
    }

}
