package com.wcpredictor.groupstage;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wcpredictor.enums.GroupEnum;
import com.wcpredictor.lookups.TeamGroupLookup;

public class GroupStandingsLoader 
{

    public static GroupStage loadStandings() throws IOException
    {
        Map<String, String> teamsByGroupStanding = new HashMap<>();
        List<String> bestThirdTeamGroups = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(Files.newBufferedReader(Path.of(".standings.csv"))))
        {  
            for (int i = 1; i <= 3; i++) 
            {

                String line = reader.readLine();
                String[] split = line.split(",");
                for (String team : split) 
                {
                    GroupEnum group = TeamGroupLookup.getGroupByTeam(team);

                    if (group == null)
                    {
                        String validTeams = TeamGroupLookup.validTeamList();
                        throw new IOException("Invalid team in configuration: " + team + "\nValid Teams: " + validTeams);
                    }

                    String key = i + group.getValue();
                    teamsByGroupStanding.put(key, team);

                    if (i == 3) 
                    {
                        bestThirdTeamGroups.add(group.getValue());
                    }
                }
                
            }

            Collections.sort(bestThirdTeamGroups);
            StringBuilder key = new StringBuilder();
            for (String group :bestThirdTeamGroups) 
            {
                key.append(group);
            }

            return new GroupStage(key.toString(), teamsByGroupStanding);
            
        }
    }

}
