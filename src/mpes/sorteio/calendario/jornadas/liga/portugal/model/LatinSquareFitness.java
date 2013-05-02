/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendario.jornadas.liga.portugal.model;

import mpes.sorteio.calendario.jornadas.liga.portugal.generation.GameRestrictions;

/**
 * Class used to calculate the fitness of a schedule from its representation as
 * a latin square
 *
 * @author marcelo
 */
public class LatinSquareFitness {

    private static final int OPTIMAL_SOLUTION = 0;
    private static final int MIRROR_IMAGE_PENALTY_VALUE = 300;
    private static final int MORE_THAN_ONE_GAME_AGAINST_THE_SAME_TEAM_PENALTY_VALUE = 200;
    private static final int DOESNT_RECEIVE_ALL_TEAMS_PENALTY_VALUE = 200;
    private static final int DOESNT_VISIT_ALL_TEAMS_PENALTY_VALUE = 200;
    private static final int GAMES_MISMATCH_PENALTY_VALUE = 300;
    private static final int MORE_THAN_ONE_BIG_GAME_IN_THE_SAME_STADIUM_PENALTY_VALUE = 50;
    private static final int MORE_THAN_ONE_REGIONAL_TEAM_AWAY_PENALTY_VALUE = 30;
    private static final int MORE_THAN_ONE_REGIONAL_TEAM_HOME_PENALTY_VALUE = 30;
    private static final int TWO_OR_MORE_CONSECUTIVE_BIG_GAMES_PENALTY_VALUE = 50;
    private static final int FORBIDDEN_GAME_PENALTY_VALUE = 50;
    /**
     * Penalty for when teams play away or home twice in a row
     */
    private static final int CONSECUTIVE_HOME_OR_AWAY_GAME_PENALTY = 15;
    /**
     * Penalty for teams that don't play the first and last game in the same
     * place
     */
    private static final int FIRST_AND_LAST_GAMES_DIFFER_PENALTY = 30;

    private enum Where {

        HOME,
        AWAY,
        DIDNT_PLAY
    }

    /**
     * Populates an array of {@link Where} for a given schedule and team
     *
     * @param where The array to be populated
     * @param square The latin square with the schedule
     * @param team The team
     */
    private static void buildWhereArray(Where[] where, LatinSquare square, int team) {
        // Populates the 'where' array
        for (int foe = 1; foe <= square.numTeams(); foe++) {
            // Gets the slot of the game
            final int slot = square.getSlot(team, foe);
            // If the slot is valid
            if (slot < where.length) {
                if (team == foe) {
                    where[slot] = Where.DIDNT_PLAY;
                } else if (team < foe) {
                    if (square.isSmallerHome(team, foe)) {
                        where[slot] = Where.HOME;
                    } else {
                        where[slot] = Where.AWAY;
                    }
                } else {
                    if (square.isSmallerHome(team, foe)) {
                        where[slot] = Where.AWAY;
                    } else {
                        where[slot] = Where.HOME;
                    }
                }
            }
        }
    }

    /**
     * Calculates the fitness of a latin square
     *
     * @param square the latin square
     * @return the fitness of the latin square
     */
    public static int fitness(LatinSquare square) {
        int fitness = 0;
        fitness += consecutiveHomeOrAwayGamesPenalty(square);
        fitness += unmatchedMirrorPenalty(square);
        return fitness;
    }

    /**
     * Calculates the penalty for teams that play home or away twice in
     * successive slots
     *
     * @param square The latin square with the schedule
     * @return the penalty
     */
    public static int consecutiveHomeOrAwayGamesPenalty(LatinSquare square) {
        // The penalty
        int penalty = 0;
        // An array with where the teams play
        final Where[] where = new Where[square.numSlots()];

        // For each team
        for (int team = 1; team < square.numTeams(); team++) {
            // Populates the 'where' array
            buildWhereArray(where, square, team);
            // Walks over the array and tests if there are repeated games
            for (int i = 1; i < where.length; i++) {
                if (where[i] == where[i - 1]) {
                    penalty += CONSECUTIVE_HOME_OR_AWAY_GAME_PENALTY;
                }
            }
        }
        return penalty;
    }

    /**
     * Calculates the penalty for schedules considering the mirroring
     *
     * @param square The latin square with the schedule
     * @return the penalty
     */
    public static int unmatchedMirrorPenalty(LatinSquare square) {
        // The penalty
        int penalty = 0;
        // The arrays that say where the teams play the first and last games
        final Where[] first = new Where[square.numTeams()];
        final Where[] last = new Where[square.numTeams()];
        // Walks over the schedule and populates the arrays
        for (int i = 1; i <= square.numTeams(); i++) {
            for (int j = i; j <= square.numTeams(); j++) {
                final int slot = square.getSlot(i, j);
                if (slot == 1) {
                    if (i == j) {
                        first[i - 1] = Where.DIDNT_PLAY;
                    } else if (square.isSmallerHome(i, j)) {
                        first[Math.min(i, j) - 1] = Where.HOME;
                        first[Math.max(i, j) - 1] = Where.AWAY;
                    }
                } else if (slot == square.numSlots()) {
                    if (i == j) {
                        last[i - 1] = Where.DIDNT_PLAY;
                    } else if (square.isSmallerHome(i, j)) {
                        last[Math.min(i, j) - 1] = Where.HOME;
                        last[Math.max(i, j) - 1] = Where.AWAY;
                    }
                }
            }
        }

        // Analyses the arrays
        for (int i = 0; i < first.length; i++) {
            if (first[i] != Where.DIDNT_PLAY
                    && last[i] != Where.DIDNT_PLAY
                    && first[i] != last[i]) {
                penalty += FIRST_AND_LAST_GAMES_DIFFER_PENALTY;
            }
        }
        return penalty;
    }
}
