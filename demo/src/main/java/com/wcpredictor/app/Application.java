package com.wcpredictor.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.wcpredictor.app.StateMachine.StateEnum;
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
    private List<MatchDetails> theFinal;

    private List<String> predictions;

    public Application(final StateMachine stateMachine) 
    {
        this.stateMachine = stateMachine;
        this.predictions = new ArrayList<>();
    }

    public void predict() throws Exception 
    {
        Scanner sc = new Scanner(System.in);
        
        while (stateMachine.getCurrentState() != StateEnum.DONE) 
            {

            AbstractSchedule schedule = null;

            switch (stateMachine.getCurrentState()) 
            {
                case R32:
                    
                    GroupStage standings = GroupStandingsLoader.loadStandings();                    
                    Map<String, String> r32Teams = standings.getQualifiedTeamsByGroup();
                    Map<String, String> fixtureSet = AnnexCFixtureLookup.getR32FixtureSet(standings.getAnnexCKey());

                    schedule = new R32Schedule(r32Teams, fixtureSet);
                    System.out.println("\nR32 Fixtures:\n");
                    r32Matches = schedule.getSchedule();
                    schedule.printSchedule();

                    this.stateMachine.next();
                    break;

                case R16:
                    predictions.add("R32 Predictions");

                    schedule = new R16Schedule(this.getWinnersForMatchups(sc, r32Matches));
                    System.out.println("\nR16 Fixtures:\n");
                    r16Matches = schedule.getSchedule();
                    schedule.printSchedule();

                    this.stateMachine.next();
                    break;
                
                case QF:
                    predictions.add("R16 Predictions");                  

                    schedule = new QFSchedule(this.getWinnersForMatchups(sc, r16Matches));
                    System.out.println("\nQuarter-Final Fixtures:\n");
                    qfMatches = schedule.getSchedule();
                    schedule.printSchedule();

                    this.stateMachine.next();
                    break;

                case SF:
                    predictions.add("QF Predictions");

                    schedule = new SFSchedule(this.getWinnersForMatchups(sc, qfMatches));
                    System.out.println("\nSemi-Final Fixtures:\n");
                    sfMatches = schedule.getSchedule();
                    schedule.printSchedule();

                    this.stateMachine.next();
                    break;

                case F:
                    predictions.add("SF Predictions");

                    schedule = new FinalSchedule(this.getWinnersForMatchups(sc, sfMatches));
                    System.out.println("\nThe Final Fixtures:\n");
                    theFinal = schedule.getSchedule();
                    schedule.printSchedule();

                    predictions.add("Final Prediction");
                    Map<String, String> winnerMap = this.getWinnersForMatchups(sc, theFinal);
                    String winner = winnerMap.entrySet().iterator().next().getValue();
                    System.out.println("The winner of World Cup 2026 will be " + winner.toUpperCase());

                    this.stateMachine.next();
                    break;

                default:
                    throw new Exception("state machine fell into unrecoverable state: " + stateMachine.getCurrentState());
            }

        }
        sc.close();
        this.displayPredictions();
    }

    private Map<String, String> getWinnersForMatchups(final Scanner sc, final List<MatchDetails> matches) 
    {
        Map<String, String> codeToTeamMap = new HashMap<>();
        
        for (MatchDetails matchDetails : matches) 
        {
            boolean valid = false;
            while (!valid) 
            {

                matchDetails.display();

                System.out.print("Who wins this game? (H or A): ");
                String input = sc.nextLine();

                String matchKey = "W" + matchDetails.getmatchNumber();
                
                String winner = "";
                String knockedOut = "";

                if (input.equalsIgnoreCase("h")) 
                {
                    winner = matchDetails.getHomeName();
                    knockedOut = matchDetails.getAwayName();
                    valid = true;
                } 
                else if (input.equalsIgnoreCase("a")) 
                {
                    winner = matchDetails.getAwayName();
                    knockedOut = matchDetails.getHomeName();
                    valid = true;
                }
                else if (input.equalsIgnoreCase("exit")) 
                {
                    System.out.println("recieved exit...exiting program.");
                    System.exit(1);
                }
                else 
                {
                    System.out.println("Invalid input. Enter H/A/exit only.");
                }

                codeToTeamMap.put(matchKey, winner);
                predictions.add(winner.toUpperCase() + " win vs " + knockedOut);
            }
            
        }
        predictions.add("");

        return codeToTeamMap;
    }

    private void displayPredictions()
    {
        System.out.println();
        for(String line: predictions) 
        {
            System.out.println(line);
        }
    }

}
