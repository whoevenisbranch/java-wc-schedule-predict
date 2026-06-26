package com.wcpredictor.schedules;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wcpredictor.match.MatchDetails;

public abstract class AbstractSchedule 
{
    protected final List<MatchDetails> schedule = new ArrayList<>();
    protected final Map<String, String> scheduleCodeToTeamNameMap;

    public AbstractSchedule(final Map<String, String> keyMap)
    {
        this.scheduleCodeToTeamNameMap = keyMap;
    }

    public List<MatchDetails> getSchedule()
    {
        for (MatchDetails matchDetails : schedule) 
        {
            matchDetails.setHomeName(this.scheduleCodeToTeamNameMap.get(matchDetails.getHomeScheduleCode()));
            matchDetails.setAwayName(this.scheduleCodeToTeamNameMap.get(matchDetails.getAwayScheduleCode()));
        }

        return schedule;
    }


    public void printSchedule() 
    {
        for (MatchDetails matchDetails : schedule) 
        {
            matchDetails.setHomeName(this.scheduleCodeToTeamNameMap.get(matchDetails.getHomeScheduleCode()));
            matchDetails.setAwayName(this.scheduleCodeToTeamNameMap.get(matchDetails.getAwayScheduleCode()));

            matchDetails.printMatchDetailsToConsole();
        }

        System.out.println();
    }
}
