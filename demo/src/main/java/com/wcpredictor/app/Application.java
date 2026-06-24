package com.wcpredictor.app;

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

public class Application {

    private final StateMachine stateMachine;

    private List<MatchDetails> r32Matches;
    private List<MatchDetails> r16Matches;
    private List<MatchDetails> qfMatches;
    private List<MatchDetails> sfMatches;
    // private List<MatchDetails> theFinal;

    public Application(final StateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }

    public void predict() {
        Scanner sc = new Scanner(System.in);
        
        while (stateMachine.getCurrentState() != StateEnum.DONE) {

            AbstractSchedule schedule = null;

            switch (stateMachine.getCurrentState()) {
                case R32:
                    System.out.println("Round of 32 Fixtures");

                    GroupStage standings = GroupStandingsLoader.loadStandings();
                    Map<String, String> r32Teams = standings.getQualifiedTeamsByGroup();
                    Map<String, String> fixtureSet = AnnexCFixtureLookup.getR32FixtureSet(standings.getAnnexCKey());

                    schedule = new R32Schedule(r32Teams, fixtureSet);
                    r32Matches = schedule.getSchedule();
                    schedule.printSchedule();

                    this.stateMachine.next();
                    break;

                case R16:
                    schedule = new R16Schedule(this.getWinnersForMatchups(sc, r32Matches));
                    System.out.println("\nRound of 16 Fixtures:\n");
                    r16Matches = schedule.getSchedule();
                    schedule.printSchedule();

                    this.stateMachine.next();
                    break;
                
                case QF:
                    schedule = new QFSchedule(this.getWinnersForMatchups(sc, r16Matches));
                    System.out.println("\nQuarter-Final Fixtures:\n");
                    qfMatches = schedule.getSchedule();
                    schedule.printSchedule();

                    this.stateMachine.next();
                    break;

                case SF:
                    schedule = new SFSchedule(this.getWinnersForMatchups(sc, qfMatches));
                    System.out.println("\nSemi-Final Fixtures:\n");
                    sfMatches = schedule.getSchedule();
                    schedule.printSchedule();

                    this.stateMachine.next();
                    break;

                case F:
                    schedule = new FinalSchedule(this.getWinnersForMatchups(sc, sfMatches));
                    System.out.println("\nThe Final Fixtures:\n");
                    // theFinal = schedule.getSchedule();
                    schedule.printSchedule();

                    this.stateMachine.next();
                    break;

                default:
                    break;
            }

        }
        sc.close();
    }

    private Map<String, String> getWinnersForMatchups(final Scanner sc, final List<MatchDetails> matches) {
        Map<String, String> codeToTeamMap = new HashMap<>();
        for (MatchDetails matchDetails : matches) {
            matchDetails.display();

            System.out.print("Who wins this game? (H or A): ");
            String input = sc.nextLine();

            String matchKey = "W" + matchDetails.getNumber();

            if (input.equalsIgnoreCase("h")) {
                codeToTeamMap.put(matchKey, matchDetails.getHomeName());
            } else if (input.equalsIgnoreCase("a")) {
                codeToTeamMap.put(matchKey, matchDetails.getAwayName());
            }
        }

        return codeToTeamMap;
    }

}
