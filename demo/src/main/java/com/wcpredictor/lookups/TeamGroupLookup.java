package com.wcpredictor.lookups;

import java.util.Map;

import com.wcpredictor.enums.GroupEnum;

public class TeamGroupLookup {

    private static final Map<String, GroupEnum> lookup = Map.ofEntries(
        // Group A
        Map.entry("Mexico", GroupEnum.A),
        Map.entry("Korea Republic", GroupEnum.A),
        Map.entry("Czechia", GroupEnum.A),
        Map.entry("South Africa", GroupEnum.A),
        // Group B
        Map.entry("Canada", GroupEnum.B),
        Map.entry("Switzerland", GroupEnum.B),
        Map.entry("Bosnia and Herzegovina", GroupEnum.B),
        Map.entry("Qatar", GroupEnum.B),
        // Group C
        Map.entry("Brazil", GroupEnum.C),
        Map.entry("Morocco", GroupEnum.C),
        Map.entry("Scotland", GroupEnum.C),
        Map.entry("Haiti", GroupEnum.C),
        // Group D
        Map.entry("USA", GroupEnum.D),
        Map.entry("Australia", GroupEnum.D),
        Map.entry("Paraguay", GroupEnum.D),
        Map.entry("Turkey", GroupEnum.D),
        // Group E
        Map.entry("Germany", GroupEnum.E),
        Map.entry("Ivory Coast", GroupEnum.E),
        Map.entry("Ecuador", GroupEnum.E),
        Map.entry("Curacao", GroupEnum.E),
        // Group F
        Map.entry("Netherlands", GroupEnum.F),
        Map.entry("Japan", GroupEnum.F),
        Map.entry("Sweden", GroupEnum.F),
        Map.entry("Tunisia", GroupEnum.F),
        // Group G
        Map.entry("Egypt", GroupEnum.G),
        Map.entry("IR Iran", GroupEnum.G),
        Map.entry("Belgium", GroupEnum.G),
        Map.entry("New Zealand", GroupEnum.G),
        // Group H
        Map.entry("Spain", GroupEnum.H),
        Map.entry("Uruguay", GroupEnum.H),
        Map.entry("Cape Verde", GroupEnum.H),
        Map.entry("Saudi Arabia", GroupEnum.H),
        // Group I
        Map.entry("France", GroupEnum.I),
        Map.entry("Norway", GroupEnum.I),
        Map.entry("Senegal", GroupEnum.I),
        Map.entry("Iraq", GroupEnum.I),
        // Group J
        Map.entry("Argentina", GroupEnum.J),
        Map.entry("Austria", GroupEnum.J),
        Map.entry("Algeria", GroupEnum.J),
        Map.entry("Jordan", GroupEnum.J),
        // Group K
        Map.entry("Colombia", GroupEnum.K),
        Map.entry("DR Congo", GroupEnum.K),
        Map.entry("Portugal", GroupEnum.K),
        Map.entry("Uzbekistan", GroupEnum.K),
        // Group L
        Map.entry("England", GroupEnum.L),
        Map.entry("Ghana", GroupEnum.L),
        Map.entry("Panama", GroupEnum.L),
        Map.entry("Croatia", GroupEnum.L)
    );

    public static GroupEnum getGroupByTeam(String team) 
    {
        return lookup.get(team);
    }

    public static String validTeamList() 
    {
        StringBuilder sb = new StringBuilder();
        for (String entry : lookup.keySet()) 
        {
            sb.append(entry + ",");
        }
        sb.delete(sb.length() - 1, sb.length());

        return sb.toString();
    }

}
