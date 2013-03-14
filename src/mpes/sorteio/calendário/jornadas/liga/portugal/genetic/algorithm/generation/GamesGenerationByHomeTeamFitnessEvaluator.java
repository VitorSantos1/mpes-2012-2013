/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendário.jornadas.liga.portugal.genetic.algorithm.generation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import mpes.sorteio.calendário.jornadas.liga.portugal.model.Team;
import org.uncommons.maths.binary.BitString;
import org.uncommons.watchmaker.framework.FitnessEvaluator;

/**
 *
 * @author vitorsantos
 */
public class GamesGenerationByHomeTeamFitnessEvaluator implements FitnessEvaluator<BitString> {

    public ArrayList<Team> teams;
    public int nBitsPerTeam;
    public HashMap<BitString, Team> teamsByGene;
    public String awayGame;
    
    public static final int OPTIMAL_SOLUTION = 500;
    public static final int MIRROR_IMAGE_PENALTY_VALUE = 300;
    public static final int BOTH_GAMES_IN_HOME_OR_AWAY_PENALTY_VALUE = 30;
    public static final int MORE_THAN_ONE_BIG_GAME_IN_THE_SAME_STADIUM_PENALTY_VALUE = 50;
    public static final int MORE_THAN_ONE_REGIONAL_TEAM_AWAY_PENALTY_VALUE = 30;
    public static final int MORE_THAN_ONE_REGIONAL_TEAM_HOME_PENALTY_VALUE = 30;
    public static final int MORE_THAN_TWO_CONSECUTIVE_BIG_GAMES_PENALTY_VALUE = 50;
    public static final int FORBIDDEN_GAME_PENALTY_VALUE = 50;
    public static final int MORE_THAN_ONE_CONSECUTIVE_HOME_OR_AWAY_GAME_PENALTY_VALUE = 15;
    public static final int MORE_THAN_ONE_GAME_AGAINST_THE_SAME_TEAM_PENALTY_VALUE = 15;

    public GamesGenerationByHomeTeamFitnessEvaluator(ArrayList<Team> t, int nBits) {
        teams = t;
        nBitsPerTeam = nBits;
        teamsByGene = new HashMap<BitString, Team>();

        while (!(teams.size() <= 0)) {
            this.attributeGeneToATeam();
        }
        
        awayGame = new String();

        while (awayGame.length() < nBitsPerTeam) {
            awayGame += "0";
        }
    }

    //Fitness is calculated here.
    @Override
    public double getFitness(BitString t, List<? extends BitString> list) {
        BitString[] keyArray = (BitString[]) teamsByGene.keySet().toArray();
        HashMap<BitString, String> candidateCalendar = atributeCandidateCalendarToEachTeam(t, keyArray);

        int fitness = OPTIMAL_SOLUTION;

        for (BitString key : keyArray) {
            fitness += this.detectForbiddenGames(candidateCalendar, keyArray);
            fitness += this.detectConsecutiveBigGames(candidateCalendar, keyArray);
            fitness += this.detectHomeGamesFor2GeographicallyCloseTeams(candidateCalendar, keyArray);
            fitness += this.detectAwayGamesFor2GeographicallyCloseTeams(candidateCalendar, keyArray);
            fitness += this.detectTwoOrMoreBigGamesOnTheSameStadium(candidateCalendar, keyArray);
            fitness += this.detectIfFirstAndLastGameAreBothAwayOrHome(candidateCalendar, key);
            fitness += this.detectMirrorImageOfHomeTeam(candidateCalendar, key);
            fitness += this.detectTwoOrMoreGamesWithTheSameOpponent(candidateCalendar, key, keyArray);
        }

        return fitness;
    }

    //Here, it evaluates if the fittest individual has the highest score possible or not.
    @Override
    public boolean isNatural() {
        return true;
    }

    private HashMap<BitString, String> atributeCandidateCalendarToEachTeam(BitString t, BitString[] keyArray) {
        String stringT = t.toString();

        HashMap<BitString, String> candidateCalendar = new HashMap<BitString, String>();

        for (BitString key : keyArray) {
            candidateCalendar.put(key, stringT.substring(0, nBitsPerTeam * keyArray.length));
            stringT = stringT.substring(nBitsPerTeam * keyArray.length);
        }

        return candidateCalendar;
    }

    //Attribute a gene for each team
    private void attributeGeneToATeam() {
        /**
         * Steps: 1. See the number of elements already present in the hash map.
         * 2. Determine the next bit string sequence. 2.1 If the sequence has a
         * lower number of bits than nBitsPerTeam, add the remainder to the
         * left. 3. Select a random team for the sequence.
         */
        int nElemsInHash = teamsByGene.size();
        String newGene = Integer.toBinaryString(nElemsInHash);

        while (newGene.length() < nBitsPerTeam) {
            newGene = "0" + newGene;
        }

        int randomTeamIndex = new Random().nextInt(teams.size());

        teamsByGene.put(new BitString(newGene), teams.get(randomTeamIndex));

        teams.remove(randomTeamIndex);
    }

    //Fitness computing functions
    public double detectForbiddenGames(HashMap<BitString, String> candidateCalendar, BitString[] keyArray) {
        double penalty = 0.0;

        return penalty;
    }

    public double detectConsecutiveBigGames(HashMap<BitString, String> candidateCalendar, BitString[] keyArray) {
        double penalty = 0.0;

        return penalty;
    }

    public double detectAwayGamesFor2GeographicallyCloseTeams(HashMap<BitString, String> candidateCalendar, BitString[] keyArray) {
        double penalty = 0.0;

        return penalty;
    }

    public double detectHomeGamesFor2GeographicallyCloseTeams(HashMap<BitString, String> candidateCalendar, BitString[] keyArray) {
        double penalty = 0.0;

        return penalty;
    }

    public double detectTwoOrMoreBigGamesOnTheSameStadium(HashMap<BitString, String> candidateCalendar, BitString[] keyArray) {
        double penalty = 0.0;

        return penalty;
    }

    public double detectIfFirstAndLastGameAreBothAwayOrHome(HashMap<BitString, String> candidateCalendar, BitString key) {
        double penalty = 0.0;

        String aCalendar = candidateCalendar.get(key);

        String firstGame = aCalendar.substring(0, nBitsPerTeam);
        String lastGame = aCalendar.substring(aCalendar.length() - nBitsPerTeam, aCalendar.length());

        if ((firstGame.equalsIgnoreCase(awayGame) && lastGame.equalsIgnoreCase(awayGame))
                || (!firstGame.equalsIgnoreCase(awayGame) && !lastGame.equalsIgnoreCase(awayGame))) {
            penalty -= BOTH_GAMES_IN_HOME_OR_AWAY_PENALTY_VALUE;
        }

        return penalty;
    }

    //Detects if a team has a gene that corresponds to himself.
    public double detectMirrorImageOfHomeTeam(HashMap<BitString, String> candidateCalendar, BitString key) {
        double penalty = 0.0;

        String aCalendar = candidateCalendar.get(key);

        while (!(aCalendar.length() <= 0)) {
            if (aCalendar.substring(0, nBitsPerTeam).equalsIgnoreCase(key.toString())) {
                penalty -= MIRROR_IMAGE_PENALTY_VALUE;
            }

            aCalendar = aCalendar.substring(nBitsPerTeam);
        }

        return penalty;
    }

    //Detects if a team has two or more games against the same opponent, on the same round.
    public double detectTwoOrMoreGamesWithTheSameOpponent(HashMap<BitString, String> candidateCalendar, BitString key, BitString[] otherKeys) {
        double penalty = 0.0;

        String aCalendar = candidateCalendar.get(key);
        
        HashMap<BitString, Integer> nTimesInCalendar = new HashMap<BitString, Integer>();
        
        for(BitString k : otherKeys){
            if(!k.toString().equalsIgnoreCase(key.toString())){
                nTimesInCalendar.put(k, 0);
            }
        }
        
        while (!(aCalendar.length() <= 0)) {
             String sequence = aCalendar.substring(0, nBitsPerTeam);
             
             if(!sequence.equalsIgnoreCase(this.awayGame)){
                 int nTimes = nTimesInCalendar.get(new BitString(sequence));
                 
                 if(!(nTimes <= 1)){
                     penalty -= MORE_THAN_ONE_GAME_AGAINST_THE_SAME_TEAM_PENALTY_VALUE;
                 }
                 
                 nTimes++;
             }
        }
        
        return penalty;
    }
}
