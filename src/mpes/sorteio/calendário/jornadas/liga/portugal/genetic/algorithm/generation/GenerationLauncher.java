/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendário.jornadas.liga.portugal.genetic.algorithm.generation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
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

/**
 *
 * @author vitorsantos
 */
public class GenerationLauncher {
    /**
     * Sequence of steps to follow:
     * 
     * 1. A GenerationalEvolutionEngine is the perfect engine to perform a genetic algorithm.
     * 2. A BitStringFactory must be used as a candidate factory.
     * 3. BitStringCrossover must be used as an evolutionary operator.
     * 4. The fitness evaluator must be performed by our class GamesGenerationFitnessEvaluator.
     * 5. The Selection process must be decided in the appropriate time.
     * 6. After all this process, the list of candidates must be (printed, examined, ???) after a performed step. 
     *         If it's necessary, a new one must be done.
     */
    
    private Championship c;
    private String algorithmType;
    
    private static final int TARGET_FITNESS = 500;
    
    public GenerationLauncher(Championship newC, String aT){
        c = newC;
        algorithmType = aT;
    }
    
    public Championship getChampionship(){
        return c;
    }
    
    public String getAlgorithmType(){
        return algorithmType;
    }
    
    //Insertion of forbidden games.
    public void insertForbiddenGames(){
       ArrayList<Game> candidateGames = new ArrayList<Game>();
        
       Game scpXfcp = new Game(c.getTeamByName("Sporting CP"), c.getTeamByName("FC Porto"));
       Game fcpXscp = scpXfcp.reverseTeams();
       candidateGames.add(scpXfcp);
       candidateGames.add(fcpXscp);
       
       Game scpXslb = new Game(c.getTeamByName("Sporting CP"), c.getTeamByName("SL Benfica"));
       Game slbXscp = scpXslb.reverseTeams();
       candidateGames.add(scpXslb);
       candidateGames.add(slbXscp);
       
       Game slbXfcp = new Game(c.getTeamByName("SL Benfica"), c.getTeamByName("FC Porto"));
       Game fcpXslb = slbXfcp.reverseTeams();
       candidateGames.add(slbXfcp);
       candidateGames.add(fcpXslb);
       
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
       
       for(Game g : candidateGames){
           if(g.getVisitedTeam() != null && g.getVisitorTeam() != null){
               GameRestrictions.getForbiddenGames().put(g, forbiddenMatchDays);
           }
       }
    }
    
    //Determine the size of gene, according to how the GA will be operated.
    public int determineTeamGeneSize(int nTeams){
        if(algorithmType.equals("GA-HT")){
            //Adding a sequence for all 0's.
            nTeams += 1;
        }    
         
        double powerOfTwoExponent = 1;
            
        while(Math.pow(2.0, powerOfTwoExponent) < nTeams){
            powerOfTwoExponent++;
        }
        
        return (int) powerOfTwoExponent;
    }
    
    public int determineGeneSize(){
        int teamGeneSize = this.determineTeamGeneSize(c.getTeams().size());
        
        //GA-HT: team * n de equipas adversárias * n de equipas total
        //GA-M: jornada * n de equipas total * n de jornadas (= n de equipas - 1)
        return teamGeneSize * (c.getTeams().size() - 1) * c.getTeams().size();
    }
    
    //Initialization of generation launcher
    public void start(){
        BitStringFactory bsf = new BitStringFactory(this.determineGeneSize());
        List<EvolutionaryOperator<BitString>> operators = new LinkedList<EvolutionaryOperator<BitString>>();
        
        //TODO: Operators to experiment different parameters
        operators.add(new BitStringCrossover()); 
        operators.add(new BitStringMutation(new Probability(0.02)));
        
        EvolutionaryOperator<BitString> pipeline = new EvolutionPipeline<BitString>(operators);
        
        GenerationalEvolutionEngine gee = new GenerationalEvolutionEngine(bsf, 
                pipeline,
                new GamesGenerationFitnessEvaluator(),
                new StochasticUniversalSampling(),
                new MersenneTwisterRNG());
    }
}
