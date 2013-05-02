/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendario.jornadas.liga.portugal.generation.tabusearch;

import mpes.sorteio.calendario.jornadas.liga.portugal.model.LatinSquare;
import org.coinor.opents.SolutionAdapter;

/**
 *
 * @author marcelo
 */
public class RRTSSolution extends SolutionAdapter {
    /**
     * The latin square with the schedule
     */
    LatinSquare square;

    /**
     * Creates a solution based on an existing schedule
     * @param square The latin square with the schedule
     */
    public RRTSSolution(LatinSquare square) {
        this.square = square;
    }

    @Override
    public Object clone() {
        //System.out.println("CLONE");
        RRTSSolution sol = (RRTSSolution) super.clone();
        sol.square= new LatinSquare(square);
        return sol;
    }
}
