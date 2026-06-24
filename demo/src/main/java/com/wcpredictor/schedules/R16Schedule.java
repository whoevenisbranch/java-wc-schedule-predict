package com.wcpredictor.schedules;

import java.util.Map;

import com.wcpredictor.match.MatchDetails;

public class R16Schedule extends AbstractSchedule {

    public R16Schedule(final Map<String, String> m)
    {
        super(m);
        schedule.add(new MatchDetails(89, "Sat 4 Jul", "W74", "W77"));
        schedule.add(new MatchDetails(90, "Sat 4 Jul", "W73", "W75"));
        schedule.add(new MatchDetails(91, "Sun 5 Jul", "W76", "W78"));
        schedule.add(new MatchDetails(92, "Sun 5 Jul", "W79", "W80"));
        schedule.add(new MatchDetails(93, "Mon 6 Jul", "W83", "W84"));
        schedule.add(new MatchDetails(94, "Mon 6 Jul", "W81", "W82"));
        schedule.add(new MatchDetails(95, "Tue 7 Jul", "W86", "W88"));
        schedule.add(new MatchDetails(96, "Tue 7 Jul", "W85", "W87"));
    }
}
