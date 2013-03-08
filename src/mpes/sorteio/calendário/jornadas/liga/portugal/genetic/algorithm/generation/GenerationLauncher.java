/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendário.jornadas.liga.portugal.genetic.algorithm.generation;

import mpes.sorteio.calendário.jornadas.liga.portugal.model.Championship;

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
       
    }
    
    //Determine the size of gene, according to how the GA will be operated.
    public int determineGeneSize(int nTeams){
        if(algorithmType.equals("GA-HT")){
            //Adding a sequence for all 0's.
            nTeams += 1;
        }    
         
        double powerOfTwoBase = 1;
            
        while(Math.pow(powerOfTwoBase, 2.0) < nTeams){
            powerOfTwoBase++;
        }
        
        
        return (int) powerOfTwoBase;
    }
    
    //Initialization of generation launcher
    public void start(){
        
    }
}
