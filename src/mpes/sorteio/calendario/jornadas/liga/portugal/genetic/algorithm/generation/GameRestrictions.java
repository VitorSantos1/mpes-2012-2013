/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendario.jornadas.liga.portugal.genetic.algorithm.generation;

import java.util.ArrayList;
import java.util.HashMap;
import mpes.sorteio.calendario.jornadas.liga.portugal.model.Game;

/**
 *
 * @author vitorsantos
 */
public class GameRestrictions {
    
    //Pair structure: Game, Number of Matchday
    private static HashMap<Game, ArrayList<Integer>> forbiddenGames = new HashMap<Game, ArrayList<Integer>>();
    
    public static HashMap<Game, ArrayList<Integer>> getForbiddenGames(){
        return forbiddenGames;
    }
}
