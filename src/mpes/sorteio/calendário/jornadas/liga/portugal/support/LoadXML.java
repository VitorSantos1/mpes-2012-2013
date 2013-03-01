/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendário.jornadas.liga.portugal.support;

import mpes.sorteio.calendário.jornadas.liga.portugal.model.Championship;

/**
 *
 * @author vitorsantos
 */
public class LoadXML {
    private Championship c;
    private String address;
    private String errorMsg;
    
    public LoadXML(Championship newC, String newAddress){
        c = newC;
        errorMsg = new String();
    }
    
    public Championship getChampionship(){
        return c;
    }
    
    public String getErrorMsg(){
        return errorMsg;
    }
    
    public boolean start(){
        return false;
    }
}
