/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendario.jornadas.liga.portugal.generation.tabusearch;

import mpes.sorteio.calendario.jornadas.liga.portugal.model.LatinSquare;
import org.coinor.opents.Move;

/**
 * Latin square moves
 *
 * @author marcelo
 */
public interface RRTSMove extends Move {

    /**
     * Performs the move on the latin square
     *
     * @param soln The current solution
     */
    void move(RRTSSolution soln);

    /**
     * Undoes the move on the latin square
     *
     * @param soln The current solution
     */
    void undo(RRTSSolution soln);
}
