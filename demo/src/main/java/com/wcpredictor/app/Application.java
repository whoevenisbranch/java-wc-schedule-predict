package com.wcpredictor.app;

import java.io.IOException;
import java.util.*;

import com.wcpredictor.app.StateMachine.StateEnum;
import com.wcpredictor.exceptions.UserExitedException;
import com.wcpredictor.groupstage.GroupStage;
import com.wcpredictor.groupstage.GroupStandingsLoader;
import com.wcpredictor.lookups.AnnexCFixtureLookup;
import com.wcpredictor.match.MatchDetails;
import com.wcpredictor.schedules.AbstractSchedule;
import com.wcpredictor.schedules.FinalSchedule;
import com.wcpredictor.schedules.QFSchedule;
import com.wcpredictor.schedules.R16Schedule;
import com.wcpredictor.schedules.R32Schedule;
import com.wcpredictor.schedules.SFSchedule;

public class Application 
{

    private final StateMachine stateMachine;

    private List<MatchDetails> r32Matches;
    private List<MatchDetails> r16Matches;
    private List<MatchDetails> qfMatches;
    private List<MatchDetails> sfMatches;

    private final List<String> predictionHistory;

    public Application(final StateMachine stateMachine) 
    {
        this.stateMachine = stateMachine;
        this.predictionHistory = new ArrayList<>();
    }

    public void predict()
    {
        Scanner sc = new Scanner(System.in);
        
        while (stateMachine.getCurrentState() != StateEnum.DONE) 
        {

            AbstractSchedule schedule;

            switch (stateMachine.getCurrentState()) 
            {
                case R32:

                    try
                    {
                        GroupStage standings = GroupStandingsLoader.loadStandings();
                        Map<String, String> r32Teams = standings.getQualifiedTeamsByGroup();
                        Map<String, String> fixtureSet = AnnexCFixtureLookup.getR32FixtureSet(standings.getAnnexCKey());

                        schedule = new R32Schedule(r32Teams, fixtureSet);
                        System.out.println("\nR32 Fixtures:\n");
                        r32Matches = schedule.getSchedule();
                        schedule.printSchedule();

                        this.stateMachine.next();

                    }
                    catch (IOException e)
                    {
                        throw new IllegalStateException("Failed to read tournament data. Check standings.csv or annex.csv exist.");
                    }

                    break;

                case R16:
                    predictionHistory.add("R32 Predictions");

                    schedule = new R16Schedule(this.getWinnersForMatchups(sc, r32Matches));
                    System.out.println("\nR16 Fixtures:\n");
                    r16Matches = schedule.getSchedule();
                    schedule.printSchedule();

                    this.stateMachine.next();
                    break;
                
                case QF:
                    predictionHistory.add("R16 Predictions");

                    schedule = new QFSchedule(this.getWinnersForMatchups(sc, r16Matches));
                    System.out.println("\nQuarter-Final Fixtures:\n");
                    qfMatches = schedule.getSchedule();
                    schedule.printSchedule();

                    this.stateMachine.next();
                    break;

                case SF:
                    predictionHistory.add("QF Predictions");

                    schedule = new SFSchedule(this.getWinnersForMatchups(sc, qfMatches));
                    System.out.println("\nSemi-Final Fixtures:\n");
                    sfMatches = schedule.getSchedule();
                    schedule.printSchedule();

                    this.stateMachine.next();
                    break;

                case F:
                    predictionHistory.add("SF Predictions");

                    schedule = new FinalSchedule(this.getWinnersForMatchups(sc, sfMatches));
                    System.out.println("\nThe Final Fixtures:\n");
                    List<MatchDetails> theFinal = schedule.getSchedule();
                    schedule.printSchedule();

                    predictionHistory.add("Final Prediction");
                    Map<String, String> winnerMap = this.getWinnersForMatchups(sc, theFinal);
                    String winner = winnerMap.entrySet().iterator().next().getValue();
                    System.out.println("The winner of World Cup 2026 will be " + winner.toUpperCase());

                    this.stateMachine.next();
                    break;

                default:
                    throw new IllegalStateException("state machine fell into unrecoverable state: " + stateMachine.getCurrentState());
            }

        }
        sc.close();
        this.displayPredictionHistory();
    }

    private Map<String, String> getWinnersForMatchups(final Scanner sc, final List<MatchDetails> matches)
    {
        Map<String, String> scheduleCodeToTeam = new HashMap<>();
        
        for (MatchDetails matchDetails : matches) 
        {
            String matchKey = "W" + matchDetails.getMatchNumber();

            String winner = "";
            String knockedOut = "";


            boolean hasValidInput = false;
            while (!hasValidInput)
            {
                matchDetails.printMatchDetailsToConsole();

                System.out.print("Who wins this game? (\"H\" or \"A\" or \"exit\"): ");
                String input = sc.nextLine();

                if (input.equalsIgnoreCase("h"))
                {
                    winner = matchDetails.getHomeName();
                    knockedOut = matchDetails.getAwayName();
                    hasValidInput = true;
                }
                else if (input.equalsIgnoreCase("a"))
                {
                    winner = matchDetails.getAwayName();
                    knockedOut = matchDetails.getHomeName();
                    hasValidInput = true;
                }
                else if (input.equalsIgnoreCase("exit"))
                {
                    throw new UserExitedException("received exit...exiting program.");
                }
                else
                {
                    System.out.println("Invalid input. Enter H/A/exit only.");
                }
            }

            scheduleCodeToTeam.put(matchKey, winner);
            predictionHistory.add(winner.toUpperCase() + " win vs " + knockedOut);

        }
        predictionHistory.add("");

        return scheduleCodeToTeam;
    }

    private void displayPredictionHistory()
    {
        System.out.println();
        for(String line: predictionHistory)
        {
            System.out.println(line);
        }
    }

}
