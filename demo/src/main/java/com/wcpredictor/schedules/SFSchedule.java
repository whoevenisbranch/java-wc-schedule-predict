package com.wcpredictor.schedules;

import java.util.Map;

import com.wcpredictor.match.MatchDetails;

public class SFSchedule extends AbstractSchedule 
{

    public SFSchedule(final Map<String, String> m)
    {
        super(m);
        schedule.add(new MatchDetails(101, "Tue 14 July", "W97", "W98"));
        schedule.add(new MatchDetails(102, "Wed 15 Jul", "W99", "W100"));
    }
}
