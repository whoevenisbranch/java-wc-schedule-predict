package com.wcpredictor.schedules;

import java.util.List;
import java.util.Map;

import com.wcpredictor.enums.NonAnnexAwaySlotEnum;
import com.wcpredictor.enums.HomeSlotEnum;
import com.wcpredictor.match.MatchDetails;

public class R32Schedule extends AbstractSchedule 
{

    private static final Map<HomeSlotEnum, MatchDetails> fixturesByHomeSlot = Map.ofEntries(
        Map.entry(HomeSlotEnum.A2, new MatchDetails(73, "Sun 28 Jun 20:00",
                HomeSlotEnum.A2.getValue(), NonAnnexAwaySlotEnum.B2.getValue())), // vs 2B
        Map.entry(HomeSlotEnum.E1, new MatchDetails(74, "Mon 29 Jun 21:30",
                HomeSlotEnum.E1.getValue())),
        Map.entry(HomeSlotEnum.F1, new MatchDetails(75, "Tue 30 Jun 02:00",
                HomeSlotEnum.F1.getValue(), NonAnnexAwaySlotEnum.C2.getValue())), // vs 2C
        Map.entry(HomeSlotEnum.C1, new MatchDetails(76, "Mon 29 Jun 18:00",
                HomeSlotEnum.C1.getValue(), NonAnnexAwaySlotEnum.F2.getValue())), // vs 2F
        Map.entry(HomeSlotEnum.I1, new MatchDetails(77, "Tue 30 Jun 22:00",
                HomeSlotEnum.I1.getValue())),
        Map.entry(HomeSlotEnum.E2, new MatchDetails(78, "Tue 30 Jun 18:00",
                HomeSlotEnum.E2.getValue(), NonAnnexAwaySlotEnum.I2.getValue())), // vs 2I
        Map.entry(HomeSlotEnum.A1, new MatchDetails(79, "Wed 1 Jul 02:00",
                HomeSlotEnum.A1.getValue())),
        Map.entry(HomeSlotEnum.L1, new MatchDetails(80, "Wed 1 Jul 17:00",
                HomeSlotEnum.L1.getValue())),
        Map.entry(HomeSlotEnum.D1, new MatchDetails(81, "Thu 2 Jul 01:00",
                HomeSlotEnum.D1.getValue())),
        Map.entry(HomeSlotEnum.G1, new MatchDetails(82, "Wed 1 Jul 21:00",
                HomeSlotEnum.G1.getValue())),
        Map.entry(HomeSlotEnum.K2, new MatchDetails(83, "Fri 3 Jul 00:00",
                HomeSlotEnum.K2.getValue(), NonAnnexAwaySlotEnum.L2.getValue())), // vs 2L
        Map.entry(HomeSlotEnum.H1, new MatchDetails(84, "Thu 2 Jul 20:00",
                HomeSlotEnum.H1.getValue(), NonAnnexAwaySlotEnum.J2.getValue())), // vs 2J
        Map.entry(HomeSlotEnum.B1, new MatchDetails(85, "Fri 3 Jul 04:00",
                HomeSlotEnum.B1.getValue())),
        Map.entry(HomeSlotEnum.J1, new MatchDetails(86, "Fri 3 Jul 23:00",
                HomeSlotEnum.J1.getValue(), NonAnnexAwaySlotEnum.H2.getValue())), // vs 2H
        Map.entry(HomeSlotEnum.K1, new MatchDetails(87, "Sat 4 Jul 02:30",
                HomeSlotEnum.K1.getValue())),
        Map.entry(HomeSlotEnum.D2, new MatchDetails(88, "Fri 3 Jul 19:00",
                HomeSlotEnum.D2.getValue(), NonAnnexAwaySlotEnum.G2.getValue())) // vs 2G
    );

    private final Map<String, String> annexCFixtures;

    public R32Schedule(Map<String, String> scheduleKeyToTeamNameMap, Map<String, String> annexCFixtures)
    {
        super(scheduleKeyToTeamNameMap);
        this.annexCFixtures = annexCFixtures;
    }

    @Override
    public List<MatchDetails> getSchedule() 
    {
        for (HomeSlotEnum homeSlot : HomeSlotEnum.values()) 
        {
            MatchDetails match = fixturesByHomeSlot.get(homeSlot);

            //away team comes from annex c fixture set.
            if (match.getAwayScheduleCode() == null)
            {
                String away = annexCFixtures.get(homeSlot.getValue());
                match.setAwayScheduleCode(away);
            }

            match.setHomeName(this.scheduleCodeToTeamNameMap.get(match.getHomeScheduleCode()));
            match.setAwayName(this.scheduleCodeToTeamNameMap.get(match.getAwayScheduleCode()));

            this.schedule.add(match);
        }
        return this.schedule;
    }

}
