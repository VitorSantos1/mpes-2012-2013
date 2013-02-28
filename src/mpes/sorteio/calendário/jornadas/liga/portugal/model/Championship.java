/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendário.jornadas.liga.portugal.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author vitorsantos
 */
public class Championship {
    private HashMap<Integer, Matchday> matchDays;
    private ArrayList<Team> teams;
    
    public Championship(){
        matchDays = new HashMap<Integer, Matchday>();
        teams = new ArrayList<Team>();
    }
    
    public ArrayList<Team> getTeams(){
        return teams;
    }
    
    public HashMap<Integer, Matchday> getMatchDays(){
        return matchDays;
    }
}
