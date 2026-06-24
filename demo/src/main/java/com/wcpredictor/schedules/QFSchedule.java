package com.wcpredictor.schedules;

import java.util.Map;

import com.wcpredictor.match.MatchDetails;

public class QFSchedule extends AbstractSchedule {

    public QFSchedule(final Map<String, String> m)
    {
        super(m);
        
        //Add 1/4 final matches
        schedule.add(new MatchDetails(97, "Thu 9 July", "W89", "W90"));
        schedule.add(new MatchDetails(98, "Fri 10 Jul", "W93", "W94"));
        schedule.add(new MatchDetails(99, "Sat 11 Jul", "W91", "W92"));
        schedule.add(new MatchDetails(100, "Sat 11 Jul", "W95", "W96"));
    }

    
}
