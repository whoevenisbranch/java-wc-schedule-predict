package com.wcpredictor.schedules;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.wcpredictor.match.MatchDetails;

public abstract class AbstractSchedule 
{
    protected List<MatchDetails> schedule = new ArrayList<>();
    protected final Map<String, String> keyToTeamMap; 

    public AbstractSchedule(final Map<String, String> m)
    {
        this.keyToTeamMap = m;
    }

    public List<MatchDetails> getSchedule()
    {
        for (MatchDetails matchDetails : schedule) 
        {
            matchDetails.setHomeName(this.keyToTeamMap.get(matchDetails.getHomeKey()));
            matchDetails.setAwayName(this.keyToTeamMap.get(matchDetails.getAwayKey()));
        }

        return schedule;
    }


    public void printSchedule() 
    {
        for (MatchDetails matchDetails : schedule) 
        {
            matchDetails.setHomeName(this.keyToTeamMap.get(matchDetails.getHomeKey()));
            matchDetails.setAwayName(this.keyToTeamMap.get(matchDetails.getAwayKey()));

            matchDetails.display();
        }

        System.out.println();
    }
}
