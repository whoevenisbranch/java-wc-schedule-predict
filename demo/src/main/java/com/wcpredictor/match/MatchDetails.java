package com.wcpredictor.match;

public class MatchDetails 
{
    private int matchNumber;
    private String dateString;
    private String homeKey;
    private String homeName;
    private String awayKey;
    private String awayName;

    public MatchDetails(int number, String dateString, String home) 
    {
        this.matchNumber = number;
        this.dateString = dateString;
        this.homeKey = home;
    }

    public MatchDetails(int number, String dateString, String home, String away) 
    {
        this(number, dateString, home);
        this.awayKey = away;
    }    

    public int getmatchNumber() 
    {
        return this.matchNumber;
    }

    public String getHomeName() 
    {
        return this.homeName;
    }

    public String getAwayName()
    {
        return this.awayName;
    }

    public String getHomeKey() 
    {
        return this.homeKey;
    }

    public String getAwayKey() 
    {
        return this.awayKey;
    }

    public void setAwayKey(String away) {
        this.awayKey = away;
    }

    public void setHomeName(String homeName) 
    {
        this.homeName = homeName;
    }

    public void setAwayName(String awayName) 
    {
        this.awayName = awayName;
    }

    public void display() 
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Match " + this.matchNumber + ": ");
        sb.append(this.homeName);
        sb.append("(" + this.homeKey + ")");
        sb.append(" v ");
        sb.append(this.awayName);
        sb.append("(" + this.awayKey + ")");
        sb.append(", " + this.dateString);

        System.out.println(sb.toString());
    }
}
