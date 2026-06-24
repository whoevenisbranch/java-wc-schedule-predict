package com.wcpredictor.lookups;

import java.util.HashMap;
import java.util.Map;

import com.wcpredictor.enums.GroupEnum;

public class TeamGroupLookup {

    private static Map<String, GroupEnum> lookup = new HashMap<>();
    static 
    {
        lookup.put("Mexico", GroupEnum.A);
        lookup.put("Korea Republic", GroupEnum.A);
        lookup.put("Czechia", GroupEnum.A);
        lookup.put("South Africa", GroupEnum.A);
        lookup.put("Canada", GroupEnum.B);
        lookup.put("Switzerland", GroupEnum.B);
        lookup.put("Bosnia and Herzegovina", GroupEnum.B);
        lookup.put("Qatar", GroupEnum.B);
        lookup.put("Brazil", GroupEnum.C);
        lookup.put("Morocco", GroupEnum.C);
        lookup.put("Scotland", GroupEnum.C);
        lookup.put("Haiti", GroupEnum.C);
        lookup.put("USA", GroupEnum.D);
        lookup.put("Australia", GroupEnum.D);
        lookup.put("Paraguay", GroupEnum.D);
        lookup.put("Turkey", GroupEnum.D);
        lookup.put("Germany", GroupEnum.E);
        lookup.put("Ivory Coast", GroupEnum.E);
        lookup.put("Ecuador", GroupEnum.E);
        lookup.put("Curacao", GroupEnum.E);
        lookup.put("Netherlands", GroupEnum.F);
        lookup.put("Japan", GroupEnum.F);
        lookup.put("Sweden", GroupEnum.F);
        lookup.put("Tunisia", GroupEnum.F);
        lookup.put("Egypt", GroupEnum.G);
        lookup.put("IR Iran", GroupEnum.G);
        lookup.put("Belgium", GroupEnum.G);
        lookup.put("New Zealand", GroupEnum.G);
        lookup.put("Spain", GroupEnum.H);
        lookup.put("Uruguay", GroupEnum.H);
        lookup.put("Cape Verde", GroupEnum.H);
        lookup.put("Saudi Arabia", GroupEnum.H);
        lookup.put("France", GroupEnum.I);
        lookup.put("Norway", GroupEnum.I);
        lookup.put("Senegal", GroupEnum.I);
        lookup.put("Iraq", GroupEnum.I);
        lookup.put("Argentina", GroupEnum.J);
        lookup.put("Austria", GroupEnum.J);
        lookup.put("Algeria", GroupEnum.J);
        lookup.put("Jordan", GroupEnum.J);
        lookup.put("Colombia", GroupEnum.K);
        lookup.put("DR Congo", GroupEnum.K);
        lookup.put("Portugal", GroupEnum.K);
        lookup.put("Uzbekistan", GroupEnum.K);
        lookup.put("England", GroupEnum.L);
        lookup.put("Ghana", GroupEnum.L);
        lookup.put("Panama", GroupEnum.L);
        lookup.put("Croatia", GroupEnum.L);
    }

    public static GroupEnum getGroupByTeam(String team) {
        return lookup.get(team);
    }

}
