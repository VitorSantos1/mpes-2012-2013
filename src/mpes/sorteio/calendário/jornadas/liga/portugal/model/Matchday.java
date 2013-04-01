/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendário.jornadas.liga.portugal.model;

import java.util.ArrayList;
/**
 *
 * @author vitorsantos
 */
public class Matchday {
    private ArrayList<Game> gamesList;
    
    public Matchday(){
        gamesList = new ArrayList<Game>();
    }
    
    public ArrayList<Game> getGameList(){
        return gamesList;
    }
}
