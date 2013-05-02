/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendario.jornadas.liga.portugal.generation.tabusearch;

import mpes.sorteio.calendario.jornadas.liga.portugal.model.LatinSquare;
import mpes.sorteio.calendario.jornadas.liga.portugal.model.LatinSquareFitness;
import org.coinor.opents.BestEverAspirationCriteria;
import org.coinor.opents.Move;
import org.coinor.opents.MoveManager;
import org.coinor.opents.ObjectiveFunction;
import org.coinor.opents.SimpleTabuList;
import org.coinor.opents.SingleThreadedTabuSearch;
import org.coinor.opents.Solution;
import org.coinor.opents.TabuList;
import org.coinor.opents.TabuSearch;

/**
 * Tabu search for single round robin tournments
 *
 * @author marcelo
 */
public class RoundRobinTabuSearch {

    /**
     * The number of iterations
     */
    private final int numIterations;
    /**
     * The number of teams
     */
    private final int numTeams;
    /**
     * The size of the tabu list
     */
    private final int tabuSize;

    /**
     * Create a Tabu search object to solve round robin tournments scheduling
     *
     * @param numIterations The number of iterations to be used
     * @param numTeams The number of teams
     * @param tabuSize The size of the tabu list
     */
    public RoundRobinTabuSearch(int numIterations, int numTeams, int tabuSize) {
        this.numIterations = numIterations;
        this.numTeams = numTeams;
        this.tabuSize = tabuSize;
    }

    public LatinSquare solve() {
        // Initial solution
        final RRTSSolution initial = new RRTSSolution(new LatinSquare(numTeams));
        // Show solution
        System.out.println(initial.square);
        System.out.println("Fitness: " + LatinSquareFitness.fitness(initial.square));

        // Objective function
        final ObjectiveFunction objFunc = new RRTSObjectiveFunction();
        // The move manager
        final MoveManager movMan = new RRTSMoveManager(numTeams);
        // The tabu list
        TabuList tabuList = new SimpleTabuList(tabuSize);

        // Create Tabu Search object
        TabuSearch tabuSearch = new SingleThreadedTabuSearch(initial,
                movMan,
                objFunc,
                tabuList,
                new BestEverAspirationCriteria(),
                false);

        // Start solving
        tabuSearch.setIterationsToGo(numIterations);
        tabuSearch.startSolving();

        // Show solution
        RRTSSolution best = (RRTSSolution) tabuSearch.getBestSolution();
        System.out.println(best.square);
        System.out.println("Fitness: " + LatinSquareFitness.fitness(best.square));
        // Return the solution
        return best.square;
    }

    public static void main(String[] args) {
        final RoundRobinTabuSearch rrts = new RoundRobinTabuSearch(100, 10, 10);
        rrts.solve();
    }
}
