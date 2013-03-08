/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendário.jornadas.liga.portugal.genetic.algorithm.generation;

import java.util.List;
import org.uncommons.watchmaker.framework.FitnessEvaluator;

/**
 *
 * @author vitorsantos
 */
public class GamesGenerationFitnessEvaluator implements FitnessEvaluator<String>{

    //Fitness is calculated here.
    @Override
    public double getFitness(String t, List<? extends String> list) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //Here, it evaluates if the fittest individual has the highest score possible or not.
    @Override
    public boolean isNatural() {
        return true;
    }
    
}
