/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendario.jornadas.liga.portugal.generation.tabusearch;

import mpes.sorteio.calendario.jornadas.liga.portugal.model.LatinSquare;
import mpes.sorteio.calendario.jornadas.liga.portugal.model.LatinSquareFitness;
import org.coinor.opents.Move;
import org.coinor.opents.ObjectiveFunction;
import org.coinor.opents.Solution;

/**
 * The objective function
 *
 * @author marcelo
 */
public class RRTSObjectiveFunction implements ObjectiveFunction {

    @Override
    public double[] evaluate(Solution soln, Move move) {
        final LatinSquare square = ((RRTSSolution) soln).square;
        final RRTSMove tsmove = (RRTSMove) move;
        int fitness;
        
        if (move == null) {
            fitness = LatinSquareFitness.fitness(square); 
        } else {
            tsmove.move((RRTSSolution) soln);
            fitness = LatinSquareFitness.fitness(square); 
            tsmove.undo((RRTSSolution) soln);
        }
        //System.out.println("Fitness: " + fitness);
        return new double[]{fitness};
    }
}
