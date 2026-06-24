package com.wcpredictor.schedules;

import java.util.List;
import java.util.Map;

import com.wcpredictor.match.MatchDetails;

public class FinalSchedule extends AbstractSchedule{

    public FinalSchedule(final Map<String, String> m)
    {
        super(m);

        //Add final matches.
        this.schedule.add(new MatchDetails(104, "Sun 19 Jul", "W101", "W102"));
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
