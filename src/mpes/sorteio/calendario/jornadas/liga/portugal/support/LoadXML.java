/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendario.jornadas.liga.portugal.support;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import mpes.sorteio.calendario.jornadas.liga.portugal.model.Championship;
import mpes.sorteio.calendario.jornadas.liga.portugal.model.Team;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author vitorsantos
 */
public class LoadXML {
    private Championship c;
    private String address;
    private String errorMsg;
    
    public LoadXML(String newAddress){
        c = new Championship();
        address = newAddress;
        errorMsg = new String();
    }
    
    public Championship getChampionship(){
        return c;
    }
    
    public String getErrorMsg(){
        return errorMsg;
    }
    
    public boolean start(){
        try{
            File fXmlFile = new File(address);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            
            doc.getDocumentElement().normalize();
            
            NodeList nList = doc.getElementsByTagName("team");
            
            for(int i = 0; i < nList.getLength(); i++){
                Node n = nList.item(i);
                
                if(n.getNodeName().equals("team")){
                    Element e = (Element) n;
                    
                    NodeList nodeOfNeighbours = e.getElementsByTagName("neighbours");
                    ArrayList<String> teamNeighbours = new ArrayList<String>();
                    
                    for(int j = 0; j < nodeOfNeighbours.getLength(); j++){
                        teamNeighbours.add(nodeOfNeighbours.item(j).getTextContent());
                    }
                    
                    Team t = new Team(e.getElementsByTagName("name").item(0).getTextContent(), 
                            teamNeighbours, 
                            e.getElementsByTagName("type").item(0).getTextContent());
                    
                    c.getTeams().add(t);
                }
            }
            
            return true;
        }
        catch(ParserConfigurationException e){
            errorMsg = "Configuração do construtor de documento falhou.";
            return false;
        }
        catch(SAXException e){
            errorMsg = "Detector de documento falhou.";
            return false;
        }
        catch(IOException e){
            errorMsg = "Entrada do documento falhou.";
            return false;
        }
    }
}
