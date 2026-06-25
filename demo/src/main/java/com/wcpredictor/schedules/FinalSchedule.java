package com.wcpredictor.schedules;

import java.util.Map;

import com.wcpredictor.match.MatchDetails;

public class FinalSchedule extends AbstractSchedule
{
    public FinalSchedule(final Map<String, String> keyMap)
    {
        super(keyMap);

        //Add final matches.
        this.schedule.add(new MatchDetails(104, "Sun 19 Jul", "W101", "W102"));
    }
}
