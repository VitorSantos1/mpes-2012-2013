/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendário.jornadas.liga.portugal.model;

/**
 *
 * @author vitorsantos
 */
public class Team {
    private String teamName;
    private String teamDistrict;
    private String teamType;
    
    public Team(){
        teamName = new String();
        teamDistrict = new String();
        teamType = new String();
    }
    
    public Team(String name, String district, String type){
        teamName = name;
        teamDistrict = district;
        teamType = type;
    }
    
    public String getTeamName(){
        return teamName;
    }
    
    public String getTeamDistrict(){
        return teamDistrict;
    }
    
    public String getTeamType(){
        return teamType;
    }
    
    public void setTeamName(String name){
        teamName = name;
    }
    
    public void setTeamDistrict(String district){
        teamDistrict = district;
    }
    
    public void setTeamType(String type){
        teamType = type;
    }
}
