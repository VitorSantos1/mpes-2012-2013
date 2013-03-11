/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendário.jornadas.liga.portugal.model;

/**
 *
 * @author vitorsantos
 */
public class Game {
    private Team visitedTeam;
    private Team visitorTeam;
    
    public Game(){
        visitedTeam = new Team();
        visitorTeam = new Team();
    }
    
    public Game(Team visited, Team visitor){
        visitedTeam = visited;
        visitorTeam = visitor;
    }
    
    public Team getVisitedTeam(){
        return visitedTeam;
    }
    
    public Team getVisitorTeam(){
        return visitorTeam;
    }
    
    public void setVisitedTeam(Team visited){
        visitedTeam = visited;
    }
    
    public void setVisitorTeam(Team visitor){
        visitorTeam = visitor;
    }
    
    public Game reverseTeams(){
        return new Game(this.visitorTeam, this.visitedTeam);
    }
}
