/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendario.jornadas.liga.portugal.genetic.algorithm.generation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import mpes.sorteio.calendario.jornadas.liga.portugal.model.Game;
import mpes.sorteio.calendario.jornadas.liga.portugal.model.Matchday;
import mpes.sorteio.calendario.jornadas.liga.portugal.model.Team;
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
    public static final int OPTIMAL_SOLUTION = 0;
    public static final int MIRROR_IMAGE_PENALTY_VALUE = 300;
    public static final int MORE_THAN_ONE_GAME_AGAINST_THE_SAME_TEAM_PENALTY_VALUE = 200;
    public static final int DOESNT_RECEIVE_ALL_TEAMS_PENALTY_VALUE = 200;
    public static final int DOESNT_VISIT_ALL_TEAMS_PENALTY_VALUE = 200;
    public static final int GAMES_MISMATCH_PENALTY_VALUE = 300;
    public static final int BOTH_GAMES_IN_HOME_OR_AWAY_PENALTY_VALUE = 30;
    public static final int MORE_THAN_ONE_BIG_GAME_IN_THE_SAME_STADIUM_PENALTY_VALUE = 50;
    public static final int MORE_THAN_ONE_REGIONAL_TEAM_AWAY_PENALTY_VALUE = 30;
    public static final int MORE_THAN_ONE_REGIONAL_TEAM_HOME_PENALTY_VALUE = 30;
    public static final int TWO_OR_MORE_CONSECUTIVE_BIG_GAMES_PENALTY_VALUE = 50;
    public static final int FORBIDDEN_GAME_PENALTY_VALUE = 50;
    public static final int MORE_THAN_ONE_CONSECUTIVE_HOME_OR_AWAY_GAME_PENALTY_VALUE = 15;

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

    public HashMap<BitString, Team> getTeamsByGene() {
        return teamsByGene;
    }

    //Fitness is calculated here.
    @Override
    public double getFitness(BitString t, List<? extends BitString> list) {
        BitString[] keyArray = new BitString[teamsByGene.keySet().size()];
        teamsByGene.keySet().toArray(keyArray);

        HashMap<BitString, String> candidateCalendar = atributeCandidateCalendarToEachTeam(t, keyArray);

        int fitness = OPTIMAL_SOLUTION;

        for (BitString key : keyArray) {
            fitness += this.detectForbiddenGames(candidateCalendar, keyArray);
            fitness += this.detectConsecutiveBigGames(candidateCalendar, key, keyArray);
            fitness += this.detectGamesFor2GeographicallyCloseTeams(candidateCalendar, key, keyArray);
            fitness += this.detectTwoOrMoreBigGamesOnTheSameStadium(candidateCalendar, key, keyArray);
            fitness += this.detectTwoConsecutiveHomeOrAwayGames(candidateCalendar, key);
            fitness += this.detectIfFirstAndLastGameAreBothAwayOrHome(candidateCalendar, key);
            fitness += this.detectMirrorImageOfHomeTeam(candidateCalendar, key);
            fitness += this.detectTwoOrMoreGamesWithTheSameOpponent(candidateCalendar, key, keyArray);
            fitness += this.detectNumberOfVisitedTeams(candidateCalendar, key);
            fitness += this.detectConsistencyBetweenGames(candidateCalendar, key, keyArray);
        }

        return fitness;
    }

    //Here, it evaluates if the fittest individual has the highest score possible or not.
    @Override
    public boolean isNatural() {
        return false;
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

    public HashMap<Integer, Matchday> genesToObjectsTranslation(BitString result) {
        BitString[] keyArray = new BitString[teamsByGene.keySet().size()];
        teamsByGene.keySet().toArray(keyArray);
        HashMap<Integer, Matchday> translatedCalendar = new HashMap<Integer, Matchday>();

        for (int i = 0; i < keyArray.length; i++) {
            String theCalendar = result.toString().substring(i * keyArray.length,
                    i * keyArray.length + nBitsPerTeam * keyArray.length);

            int gameIndex = 1;

            while (!(theCalendar.length() <= 0)) {
                String aGame = theCalendar.substring(0, nBitsPerTeam);

                if (!aGame.equalsIgnoreCase(awayGame)) {
                    Matchday aMatchday = null;

                    if (translatedCalendar.get(gameIndex) == null) {
                        aMatchday = new Matchday();
                    } else {
                        aMatchday = translatedCalendar.get(gameIndex);
                    }

                    aMatchday.getGameList().add(new Game(teamsByGene.get(keyArray[i]), teamsByGene.get(new BitString(aGame))));

                    translatedCalendar.put(gameIndex, aMatchday);
                }

                theCalendar = theCalendar.substring(nBitsPerTeam);
                gameIndex++;
            }
        }

        return translatedCalendar;
    }

    //Fitness computing functions
    //Restrictions Appliers Fitness Evaluators
    public double detectForbiddenGames(HashMap<BitString, String> candidateCalendar, BitString[] keyArray) {
        double penalty = 0.0;
        Game[] forbiddenGames = new Game[GameRestrictions.getForbiddenGames().keySet().size()];
        GameRestrictions.getForbiddenGames().keySet().toArray(forbiddenGames);

        //It starts the search by the forbidden games
        for (Game g : forbiddenGames) {
            Team homeTeam = g.getVisitedTeam();
            Team awayTeam = g.getVisitorTeam();

            //It will search for the correspondent calendar of home team.
            for (BitString k : keyArray) {
                if (teamsByGene.get(k).getTeamName().equalsIgnoreCase(homeTeam.getTeamName())) {
                    String aCalendar = candidateCalendar.get(k);

                    //It will verifies if it has any forbidden teams, according to the integer array indicating the forbidden matchdays.
                    for (int gameIndex : GameRestrictions.getForbiddenGames().get(g)) {
                        String sequence = aCalendar.substring(nBitsPerTeam * (gameIndex - 1), nBitsPerTeam * (gameIndex - 1) + nBitsPerTeam);

                        if (!sequence.equals(awayGame) && teamsByGene.get(new BitString(sequence)) != null && teamsByGene.get(new BitString(sequence)).getTeamName().equalsIgnoreCase(awayTeam.getTeamName())) {
                            penalty += FORBIDDEN_GAME_PENALTY_VALUE;
                        }
                    }
                }
            }
        }

        return penalty;
    }

    public double detectConsecutiveBigGames(HashMap<BitString, String> candidateCalendar, BitString key, BitString[] keyArray) {
        double penalty = 0.0;

        Team aTeam = teamsByGene.get(key);
        int gameIndex = 0;

        //Enters in this situation if the team is not null and if it's a big one.
        if (aTeam != null && (aTeam.getTeamType().equalsIgnoreCase("Equipa Grande")
                || aTeam.getTeamType().equalsIgnoreCase("Equipa \"B\" Grande"))) {
            String aCalendar = candidateCalendar.get(key);

            while (!(aCalendar.length() <= nBitsPerTeam)) {
                String sequence = aCalendar.substring(0, nBitsPerTeam);

                Team anotherTeam = teamsByGene.get(new BitString(sequence));

                //Enters if the algorithm detects an important home match.
                if (anotherTeam != null && (anotherTeam.getTeamType().equalsIgnoreCase("Equipa Grande")
                        || anotherTeam.getTeamType().equalsIgnoreCase("Equipa \"B\" Grande"))) {
                    for (BitString k : keyArray) {
                        Team thisTeam = teamsByGene.get(k);

                        //If this team is a big one, it will search for its game that occurs the next matchday of the previous team
                        if (thisTeam != null && (thisTeam.getTeamType().equalsIgnoreCase("Equipa Grande")
                                || thisTeam.getTeamType().equalsIgnoreCase("Equipa \"B\" Grande"))) {
                            String thisSequence = candidateCalendar.get(k);
                            String otherTeamGame = thisSequence.substring((gameIndex + 1) * nBitsPerTeam, (gameIndex + 1) * nBitsPerTeam + nBitsPerTeam);

                            Team opponentOfThisTeam = teamsByGene.get(new BitString(otherTeamGame));

                            //If this game is a big one, a penalty is applied
                            if (opponentOfThisTeam != null && (opponentOfThisTeam.getTeamType().equalsIgnoreCase("Equipa Grande")
                                    || opponentOfThisTeam.getTeamType().equalsIgnoreCase("Equipa \"B\" Grande"))) {
                                penalty += TWO_OR_MORE_CONSECUTIVE_BIG_GAMES_PENALTY_VALUE;
                            }
                        }
                    }
                }

                gameIndex++;
                aCalendar = aCalendar.substring(nBitsPerTeam);
            }
        }

        return penalty;
    }

    public double detectGamesFor2GeographicallyCloseTeams(HashMap<BitString, String> candidateCalendar, BitString key, BitString[] keyArray) {
        double penalty = 0.0;

        Team aTeam = teamsByGene.get(key);
        ArrayList<String> neighbourTeams = aTeam.getNeighbourTeams();

        //If a team has others in its proximity
        if (!neighbourTeams.isEmpty()) {
            String aCalendar = candidateCalendar.get(key);
            int gameIndex = 0;

            while (!(aCalendar.length() <= 0)) {
                String aGame = aCalendar.substring(0, nBitsPerTeam);

                //It it plays away
                if (aGame.equals(awayGame)) {
                    for (String teamName : neighbourTeams) {
                        for (BitString k : keyArray) {
                            if (teamsByGene.get(k).getTeamName().equalsIgnoreCase(teamName)) {
                                String otherCalendar = candidateCalendar.get(k);
                                String otherGame = otherCalendar.substring(nBitsPerTeam * gameIndex, nBitsPerTeam * gameIndex + nBitsPerTeam);

                                if (otherGame.equals(awayGame)) {
                                    penalty += MORE_THAN_ONE_REGIONAL_TEAM_AWAY_PENALTY_VALUE;
                                }
                            }
                        }
                    }
                } //If it plays home
                else if (!aGame.equals(awayGame)) {
                    for (String teamName : neighbourTeams) {
                        for (BitString k : keyArray) {
                            if (teamsByGene.get(k).getTeamName().equalsIgnoreCase(teamName)) {
                                String otherCalendar = candidateCalendar.get(k);
                                String otherGame = otherCalendar.substring(nBitsPerTeam * gameIndex, nBitsPerTeam * gameIndex + nBitsPerTeam);

                                if (!otherGame.equals(awayGame)) {
                                    penalty += MORE_THAN_ONE_REGIONAL_TEAM_HOME_PENALTY_VALUE;
                                }
                            }
                        }
                    }
                }

                gameIndex++;
                aCalendar = aCalendar.substring(nBitsPerTeam);
            }
        }

        return penalty;
    }

    public double detectTwoOrMoreBigGamesOnTheSameStadium(HashMap<BitString, String> candidateCalendar, BitString key, BitString[] keyArray) {
        double penalty = 0.0;

        Team aTeam = teamsByGene.get(key);

        //If team with key as a parameter is a big one
        if (aTeam != null && (aTeam.getTeamType().equalsIgnoreCase("Equipa Grande")
                || aTeam.getTeamType().equalsIgnoreCase("Equipa \"B\" Grande"))) {
            String aCalendar = candidateCalendar.get(key);
            int gamesCounter = 0;

            //Search for how many big teams will receive
            while (!(aCalendar.length() <= 0)) {
                String aGame = aCalendar.substring(0, nBitsPerTeam);
                Team otherTeam = teamsByGene.get(new BitString(aGame));

                if (otherTeam != null && (otherTeam.getTeamType().equalsIgnoreCase("Equipa Grande")
                        || otherTeam.getTeamType().equalsIgnoreCase("Equipa \"B\" Grande"))) {
                    gamesCounter++;
                }

                aCalendar = aCalendar.substring(nBitsPerTeam);
            }

            if (!(gamesCounter <= 1)) {
                penalty += MORE_THAN_ONE_BIG_GAME_IN_THE_SAME_STADIUM_PENALTY_VALUE;
            }
        }

        return penalty;
    }

    public double detectTwoConsecutiveHomeOrAwayGames(HashMap<BitString, String> candidateCalendar, BitString key) {
        double penalty = 0.0;

        String aCalendar = candidateCalendar.get(key);

        while (!(aCalendar.length() <= nBitsPerTeam)) {
            String aGame = aCalendar.substring(0, nBitsPerTeam);
            String theNextGame = aCalendar.substring(nBitsPerTeam, nBitsPerTeam * 2);

            if ((aGame.equalsIgnoreCase(awayGame) && theNextGame.equalsIgnoreCase(awayGame))
                    || (!aGame.equalsIgnoreCase(awayGame) && !theNextGame.equalsIgnoreCase(awayGame))) {
                penalty += MORE_THAN_ONE_CONSECUTIVE_HOME_OR_AWAY_GAME_PENALTY_VALUE;
            }

            aCalendar = aCalendar.substring(nBitsPerTeam);
        }

        return penalty;
    }

    public double detectIfFirstAndLastGameAreBothAwayOrHome(HashMap<BitString, String> candidateCalendar, BitString key) {
        double penalty = 0.0;

        String aCalendar = candidateCalendar.get(key);

        String firstGame = aCalendar.substring(0, nBitsPerTeam);
        String lastGame = aCalendar.substring(aCalendar.length() - nBitsPerTeam, aCalendar.length());

        if ((firstGame.equalsIgnoreCase(awayGame) && lastGame.equalsIgnoreCase(awayGame))
                || (!firstGame.equalsIgnoreCase(awayGame) && !lastGame.equalsIgnoreCase(awayGame))) {
            penalty += BOTH_GAMES_IN_HOME_OR_AWAY_PENALTY_VALUE;
        }

        return penalty;
    }

    //Solution Consistency Fitness Evaluators
    //Detects if a team has a gene that corresponds to himself.
    public double detectMirrorImageOfHomeTeam(HashMap<BitString, String> candidateCalendar, BitString key) {
        double penalty = 0.0;

        String aCalendar = candidateCalendar.get(key);

        while (!(aCalendar.length() <= 0)) {
            if (aCalendar.substring(0, nBitsPerTeam).equalsIgnoreCase(key.toString())) {
                penalty += MIRROR_IMAGE_PENALTY_VALUE;
            }

            aCalendar = aCalendar.substring(nBitsPerTeam);
        }

        return penalty;
    }

    //Detects if a team has two or more games against the same opponent, on the same round.
    public double detectTwoOrMoreGamesWithTheSameOpponent(HashMap<BitString, String> candidateCalendar, BitString key, BitString[] keyArray) {
        double penalty = 0.0;

        String aCalendar = candidateCalendar.get(key);

        HashMap<BitString, Integer> nTimesInCalendar = new HashMap<BitString, Integer>();

        for (BitString k : keyArray) {
            if (!k.toString().equalsIgnoreCase(key.toString())) {
                nTimesInCalendar.put(k, 0);
            }
        }

        while (!(aCalendar.length() <= 0)) {
            String sequence = aCalendar.substring(0, nBitsPerTeam);

            if (!sequence.equalsIgnoreCase(this.awayGame)) {
                Integer nTimes = nTimesInCalendar.get(new BitString(sequence));

                if (nTimes != null) {
                    if (!(nTimes <= 1)) {
                        penalty += MORE_THAN_ONE_GAME_AGAINST_THE_SAME_TEAM_PENALTY_VALUE;
                    }

                    nTimesInCalendar.put(new BitString(sequence), nTimes++);
                }
            }

            aCalendar = aCalendar.substring(nBitsPerTeam);
        }

        return penalty;
    }

    public double detectNumberOfVisitedTeams(HashMap<BitString, String> candidateCalendar, BitString key) {
        double penalty = 0.0;

        String aCalendar = candidateCalendar.get(key);
        double gamesCounter = 0.0;
        double visitedTeamsCounter = 0.0;

        while (!(aCalendar.length() <= 0)) {
            String sequence = aCalendar.substring(0, nBitsPerTeam);

            if (!sequence.equalsIgnoreCase(this.awayGame)) {
                visitedTeamsCounter++;
            }

            gamesCounter++;

            aCalendar = aCalendar.substring(nBitsPerTeam);
        }

        //Test if the number of games is even or odd.
        if (gamesCounter % 2 == 0) {
            if (visitedTeamsCounter != (gamesCounter / 2)) {
                penalty += DOESNT_RECEIVE_ALL_TEAMS_PENALTY_VALUE;
            }
        } else {
            if ((visitedTeamsCounter != ((int) (gamesCounter / 2)))
                    || (visitedTeamsCounter != (((int) (gamesCounter / 2)) + 1))) {
                penalty += DOESNT_RECEIVE_ALL_TEAMS_PENALTY_VALUE;
            }
        }

        return penalty;
    }

    public double detectNumberOfVisitsByATeam(HashMap<BitString, String> candidateCalendar, BitString key) {
        double penalty = 0.0;

        String aCalendar = candidateCalendar.get(key);
        double gamesCounter = 0.0;
        double visitsCounter = 0.0;

        while (!(aCalendar.length() <= 0)) {
            String sequence = aCalendar.substring(0, nBitsPerTeam);

            if (sequence.equalsIgnoreCase(this.awayGame)) {
                visitsCounter++;
            }

            gamesCounter++;

            aCalendar = aCalendar.substring(nBitsPerTeam);
        }

        //Test if the number of games is even or odd.
        if (gamesCounter % 2 == 0) {
            if (visitsCounter != (gamesCounter / 2)) {
                penalty += DOESNT_VISIT_ALL_TEAMS_PENALTY_VALUE;
            }
        } else {
            if ((visitsCounter != ((int) (gamesCounter / 2)))
                    || (visitsCounter != (((int) (gamesCounter / 2)) + 1))) {
                penalty += DOESNT_VISIT_ALL_TEAMS_PENALTY_VALUE;
            }
        }

        return penalty;
    }

    //Detects consistency between viewpoints of a game by each team.
    public double detectConsistencyBetweenGames(HashMap<BitString, String> candidateCalendar, BitString key, BitString[] keyArray) {
        double penalty = 0.0;

        String aCalendar = candidateCalendar.get(key);
        int gameIndex = 0;

        while (!(aCalendar.length() <= 0)) {
            String sequence = aCalendar.substring(0, nBitsPerTeam);

            if (!sequence.equalsIgnoreCase(this.awayGame)) {
                String otherTeamSequence = candidateCalendar.get(new BitString(sequence));

                if (otherTeamSequence != null) {
                    String otherTeamGame = otherTeamSequence.substring(gameIndex * nBitsPerTeam, gameIndex * nBitsPerTeam + nBitsPerTeam);

                    if (!otherTeamGame.equals(this.awayGame)) {
                        penalty += GAMES_MISMATCH_PENALTY_VALUE;
                    }
                }
            } else {
                boolean teamFound = false;

                for (BitString k : keyArray) {
                    String otherTeamSequence = candidateCalendar.get(k);
                    String otherTeamGame = otherTeamSequence.substring(gameIndex * nBitsPerTeam, gameIndex * nBitsPerTeam + nBitsPerTeam);

                    if (otherTeamGame.equals(key.toString())) {
                        teamFound = true;
                        break;
                    }
                }

                if (!teamFound) {
                    penalty += GAMES_MISMATCH_PENALTY_VALUE;
                }
            }

            gameIndex++;

            aCalendar = aCalendar.substring(nBitsPerTeam);
        }

        return penalty;
    }
}
