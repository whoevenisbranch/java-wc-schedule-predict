package com.wcpredictor.groupstage;

import java.util.Map;

public class GroupStage 
{
    
    private String annexCKey;
    private Map<String, String> qualifiedTeamsByGroup;

    public GroupStage(String annexCKey, Map<String, String> qualifiedTeamsByGroup) 
    {
        this.annexCKey = annexCKey;
        this.qualifiedTeamsByGroup = qualifiedTeamsByGroup;
    }

    public String getAnnexCKey() 
    {
        return annexCKey;
    }

    public Map<String, String> getQualifiedTeamsByGroup() 
    {
        return qualifiedTeamsByGroup;
    }

    public void setAnnexCKey(String annexCKey) 
    {
        this.annexCKey = annexCKey;
    }

    public void setQualifiedTeamsByGroup(Map<String, String> qualifiedTeamsByGroup) 
    {
        this.qualifiedTeamsByGroup = qualifiedTeamsByGroup;
    }
}
