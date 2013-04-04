/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendario.jornadas.liga.portugal.model;

import java.util.ArrayList;

/**
 *
 * @author vitorsantos
 */
public class Team {
    private String teamName;
    private ArrayList<String> neighbourTeams;
    private String teamType;
    
    public Team(){
        teamName = new String();
        neighbourTeams = new ArrayList<String>();
        teamType = new String();
    }
    
    public Team(String name, ArrayList<String> neighbours, String type){
        teamName = name;
        neighbourTeams = neighbours;
        teamType = type;
    }
    
    public String getTeamName(){
        return teamName;
    }
    
    public ArrayList<String> getNeighbourTeams(){
        return neighbourTeams;
    }
    
    public String getTeamType(){
        return teamType;
    }
    
    public void setTeamName(String name){
        teamName = name;
    }
    
    public void setNeighbourTeams(ArrayList<String> neighbours){
        neighbourTeams = neighbours;
    }
    
    public void setTeamType(String type){
        teamType = type;
    }
}
