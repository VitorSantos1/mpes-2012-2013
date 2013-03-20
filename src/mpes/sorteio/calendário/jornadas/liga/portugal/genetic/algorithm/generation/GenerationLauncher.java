/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendário.jornadas.liga.portugal.genetic.algorithm.generation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import mpes.sorteio.calendário.jornadas.liga.portugal.model.Championship;
import mpes.sorteio.calendário.jornadas.liga.portugal.model.Game;
import org.uncommons.maths.binary.BitString;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
import org.uncommons.watchmaker.framework.factories.BitStringFactory;
import org.uncommons.watchmaker.framework.operators.BitStringCrossover;
import org.uncommons.watchmaker.framework.operators.BitStringMutation;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.StochasticUniversalSampling;
import org.uncommons.watchmaker.framework.termination.TargetFitness;

/**
 *
 * @author vitorsantos
 */
public class GenerationLauncher {

    /**
     * Sequence of steps to follow:
     *
     * 1. A GenerationalEvolutionEngine is the perfect engine to perform a
     * genetic algorithm. 2. A BitStringFactory must be used as a candidate
     * factory. 3. BitStringCrossover must be used as an evolutionary operator.
     * 4. The fitness evaluator must be performed by our class
     * GamesGenerationByHomeTeamFitnessEvaluator. 5. The Selection process must
     * be decided in the appropriate time. 6. After all this process, the list
     * of candidates must be (printed, examined, ???) after a performed step. If
     * it's necessary, a new one must be done.
     */
    private Championship c;
    private String algorithmType;
    private static final int TARGET_FITNESS = 500;

    public GenerationLauncher(Championship newC, String aT) {
        c = newC;
        algorithmType = aT;
    }

    public Championship getChampionship() {
        return c;
    }

    public String getAlgorithmType() {
        return algorithmType;
    }

    //Insertion of forbidden games.
    public void insertForbiddenGames() {
        ArrayList<Game> candidateGames = new ArrayList<Game>();

        Game cdnXcsm = new Game(c.getTeamByName("CD Nacional"), c.getTeamByName("CS Marítimo"));
        Game csmXcdn = cdnXcsm.reverseTeams();
        candidateGames.add(cdnXcsm);
        candidateGames.add(csmXcdn);

        Game scbXvsc = new Game(c.getTeamByName("SC Braga"), c.getTeamByName("Vitória SC"));
        Game vscXscb = scbXvsc.reverseTeams();
        candidateGames.add(scbXvsc);
        candidateGames.add(vscXscb);

        Game cfuXcsm = new Game(c.getTeamByName("CF União"), c.getTeamByName("CS Marítimo"));
        Game csmXcfu = cfuXcsm.reverseTeams();
        candidateGames.add(cfuXcsm);
        candidateGames.add(csmXcfu);

        ArrayList<Integer> forbiddenMatchDays = new ArrayList<Integer>();
        forbiddenMatchDays.add(1);
        forbiddenMatchDays.add(2);
        forbiddenMatchDays.add(3);
        forbiddenMatchDays.add(4);

        GameRestrictions.getForbiddenGames().clear();

        for (Game g : candidateGames) {
            if (g.getVisitedTeam() != null && g.getVisitorTeam() != null) {
                GameRestrictions.getForbiddenGames().put(g, forbiddenMatchDays);
            }
        }
    }

    //Determine the size of gene, according to how the GA will be operated.
    public int determineTeamGeneSize(int nTeams) {
        if (algorithmType.equals("GA-HT")) {
            //Adding a sequence for all 0's.
            nTeams += 1;
        }

        double powerOfTwoExponent = 1;

        while (Math.pow(2.0, powerOfTwoExponent) < nTeams) {
            powerOfTwoExponent++;
        }

        return (int) powerOfTwoExponent;
    }

    public int determineGeneSize() {
        int teamGeneSize = this.determineTeamGeneSize(c.getTeams().size());

        //GA-HT: equipa * n de equipas adversárias * n de equipas total
        //GA-M: jornada * n de equipas total * n de jornadas (= n de equipas - 1)
        return teamGeneSize * (c.getTeams().size() - 1) * c.getTeams().size();
    }

    //It translates information in bits to objects, displaying information for use by MainWindow.
    public void genesToObjectsTranslation(BitString result) {
    }

    //Initialization of generation launcher
    public void start() {
        BitStringFactory bsf = new BitStringFactory(this.determineGeneSize());
        List<EvolutionaryOperator<BitString>> operators = new LinkedList<EvolutionaryOperator<BitString>>();

        //TODO: Operators to experiment different parameters
        /**
         * Examples: In Crossover: How many cross points? Cross points with
         * probabilities? In Mutation: Probability? Probability in each bit?
         *
         * This operations can be done with an auxiliary GUI Tool...
         */
        operators.add(new BitStringCrossover());
        operators.add(new BitStringMutation(new Probability(0.02)));

        EvolutionaryOperator<BitString> pipeline = new EvolutionPipeline<BitString>(operators);
        GenerationalEvolutionEngine<BitString> gee = null;

        if (algorithmType.equals("GA-HT")) {
            gee = new GenerationalEvolutionEngine<BitString>(bsf,
                    pipeline,
                    new GamesGenerationByHomeTeamFitnessEvaluator(c.getTeams(), this.determineTeamGeneSize(c.getTeams().size())),
                    new StochasticUniversalSampling(),
                    new MersenneTwisterRNG());
        }

        //TODO: Operators to experiment different parameters
        /**
         * Examples: Number of population? Elitism? If yes, how many elitists?
         * Which terminations? Maximum Number of Generations? Enable Stagnation
         * for how many generations? Target fitness?
         *
         * This operations can be done with an auxiliary GUI Tool...
         */
        BitString result = gee.evolve(15, 0, new TargetFitness(TARGET_FITNESS, true));

        this.genesToObjectsTranslation(result);
    }
}
