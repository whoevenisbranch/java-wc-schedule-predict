package com.wcpredictor.match;

public class MatchDetails 
{
    private final int matchNumber;
    private final String dateString;
    private final String homeScheduleCode;
    private String homeName;
    private String awayScheduleCode;
    private String awayName;

    public MatchDetails(int number, String dateString, String home) 
    {
        this.matchNumber = number;
        this.dateString = dateString;
        this.homeScheduleCode = home;
    }

    public MatchDetails(int number, String dateString, String home, String away) 
    {
        this(number, dateString, home);
        this.awayScheduleCode = away;
    }    

    public int getMatchNumber()
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

    public String getHomeScheduleCode()
    {
        return this.homeScheduleCode;
    }

    public String getAwayScheduleCode()
    {
        return this.awayScheduleCode;
    }

    public void setAwayScheduleCode(String away) {
        this.awayScheduleCode = away;
    }

    public void setHomeName(String homeName) 
    {
        this.homeName = homeName;
    }

    public void setAwayName(String awayName) 
    {
        this.awayName = awayName;
    }

    public void printMatchDetailsToConsole()
    {
        String match = "Match " + this.matchNumber + ": " +
                this.homeName + "(" + this.homeScheduleCode + ")" +
                " v " +
                this.awayName + "(" + this.awayScheduleCode + ")" +
                ", " + this.dateString;

        System.out.println(match);
    }
}
