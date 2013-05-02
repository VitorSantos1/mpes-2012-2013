/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendario.jornadas.liga.portugal.generation.tabusearch;

import mpes.sorteio.calendario.jornadas.liga.portugal.model.LatinSquare;
import org.coinor.opents.Move;
import org.coinor.opents.MoveManager;
import org.coinor.opents.Solution;

/**
 * Move manager for Single Round Robin tournments
 *
 * @author marcelo
 */
public class RRTSMoveManager implements MoveManager {

    /**
     * The array of moves
     */
    private final Move[] moves;

    public RRTSMoveManager(int numTeams) {
        // The number of slots
        final int numSlots = LatinSquare.numSlots(numTeams);
        // The number of games
        final int numGames = numTeams * (numTeams - 1) / 2;
        // The index in the array
        int index = 0;
        // The array of moves
        moves = new Move[numGames + numGames + numSlots * (numSlots - 1) / 2];

        // Switch visitor moves and switch team moves
        for (int team1 = 1; team1 <= numTeams; team1++) {
            for (int team2 = team1 + 1; team2 <= numTeams; team2++) {
                moves[index] = new SwitchVisitor(team1, team2);
                index++;
                moves[index] = new SwitchTeams(team1, team2);
                index++;
            }
        }
        // Switch slots moves
        for (int slot1 = 1; slot1 <= numSlots; slot1++) {
            for (int slot2 = slot1 + 1; slot2 <= numSlots; slot2++) {
                moves[index] = new SwitchSlots(slot1, slot2);
                index++;
            }
        }
    }

    @Override
    public Move[] getAllMoves(Solution solution) {
        return moves;
    }
}
