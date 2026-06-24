package com.wcpredictor.schedules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wcpredictor.enums.AwaySlotEnum;
import com.wcpredictor.enums.HomeSlotEnum;
import com.wcpredictor.match.MatchDetails;

public class R32Schedule extends AbstractSchedule {

    private static final Map<String, String> nonAnnexFixtures;
    static 
    {
        nonAnnexFixtures = new HashMap<>();
        nonAnnexFixtures.put(HomeSlotEnum.A2.getValue(), "2B");
        nonAnnexFixtures.put(HomeSlotEnum.F1.getValue(), "2C");
        nonAnnexFixtures.put(HomeSlotEnum.C1.getValue(), "2F");
        nonAnnexFixtures.put(HomeSlotEnum.E2.getValue(), "2I");
        nonAnnexFixtures.put(HomeSlotEnum.K2.getValue(), "2L");
        nonAnnexFixtures.put(HomeSlotEnum.H1.getValue(), "2J");
        nonAnnexFixtures.put(HomeSlotEnum.J1.getValue(), "2H");
        nonAnnexFixtures.put(HomeSlotEnum.D2.getValue(), "2G");
    }

    static Map<HomeSlotEnum, MatchDetails> fixturesByHomeSlot;
    static 
    {
        fixturesByHomeSlot = new HashMap<>();
        fixturesByHomeSlot.put(HomeSlotEnum.A2, new MatchDetails(73, "Sun 28 Jun 20:00", HomeSlotEnum.A2.getValue(), AwaySlotEnum.B2.getValue())); // vs 2B
        fixturesByHomeSlot.put(HomeSlotEnum.E1, new MatchDetails(74, "Mon 29 Jun 21:30", HomeSlotEnum.E1.getValue()));
        fixturesByHomeSlot.put(HomeSlotEnum.F1, new MatchDetails(75, "Tue 30 Jun 02:00", HomeSlotEnum.F1.getValue(), AwaySlotEnum.C2.getValue())); // vs 2C
        fixturesByHomeSlot.put(HomeSlotEnum.C1, new MatchDetails(76, "Mon 29 Jun 18:00", HomeSlotEnum.C1.getValue(), AwaySlotEnum.F2.getValue())); // vs 2F
        fixturesByHomeSlot.put(HomeSlotEnum.I1, new MatchDetails(77, "Tue 30 Jun 22:00", HomeSlotEnum.I1.getValue()));
        fixturesByHomeSlot.put(HomeSlotEnum.E2, new MatchDetails(78, "Tue 30 Jun 18:00", HomeSlotEnum.E2.getValue(), AwaySlotEnum.I2.getValue())); // vs 2I
        fixturesByHomeSlot.put(HomeSlotEnum.A1, new MatchDetails(79, "Wed 1 Jul 02:00", HomeSlotEnum.A1.getValue()));
        fixturesByHomeSlot.put(HomeSlotEnum.L1, new MatchDetails(80, "Wed 1 Jul 17:00", HomeSlotEnum.L1.getValue()));
        fixturesByHomeSlot.put(HomeSlotEnum.D1, new MatchDetails(81, "Thu 2 Jul 01:00", HomeSlotEnum.D1.getValue()));
        fixturesByHomeSlot.put(HomeSlotEnum.G1, new MatchDetails(82, "Wed 1 Jul 21:00", HomeSlotEnum.G1.getValue()));
        fixturesByHomeSlot.put(HomeSlotEnum.K2, new MatchDetails(83, "Fri 3 Jul 00:00", HomeSlotEnum.K2.getValue(), AwaySlotEnum.L2.getValue())); // vs 2L
        fixturesByHomeSlot.put(HomeSlotEnum.H1, new MatchDetails(84, "Thu 2 Jul 20:00", HomeSlotEnum.H1.getValue(), AwaySlotEnum.J2.getValue())); // vs 2J
        fixturesByHomeSlot.put(HomeSlotEnum.B1, new MatchDetails(85, "Fri 3 Jul 04:00", HomeSlotEnum.B1.getValue()));
        fixturesByHomeSlot.put(HomeSlotEnum.J1, new MatchDetails(86, "Fri 3 Jul 23:00", HomeSlotEnum.J1.getValue(), AwaySlotEnum.H2.getValue())); // vs 2H
        fixturesByHomeSlot.put(HomeSlotEnum.K1, new MatchDetails(87, "Sat 4 Jul 02:30", HomeSlotEnum.K1.getValue()));
        fixturesByHomeSlot.put(HomeSlotEnum.D2, new MatchDetails(88, "Fri 3 Jul 19:00", HomeSlotEnum.D2.getValue(), AwaySlotEnum.G2.getValue())); // vs 2G
    }

    private final Map<String, String> annexCFixtures;

    public R32Schedule(Map<String, String> keyMap, Map<String, String> annexCFixtures) 
    {
        super(keyMap);
        this.annexCFixtures = annexCFixtures;
    }

    @Override
    public List<MatchDetails> getSchedule() 
    {
        for (HomeSlotEnum homeSlot : HomeSlotEnum.values()) 
        {
            MatchDetails match = fixturesByHomeSlot.get(homeSlot);

            if (match.getAwayKey() == null) {
                String away = annexCFixtures.get(homeSlot.getValue());
                match.setAwayKey(away);
            }

            match.setHomeName(this.keyToTeamMap.get(match.getHomeKey()));
            match.setAwayName(this.keyToTeamMap.get(match.getAwayKey()));

            this.schedule.add(match);
        }
        return this.schedule;
    }

}
