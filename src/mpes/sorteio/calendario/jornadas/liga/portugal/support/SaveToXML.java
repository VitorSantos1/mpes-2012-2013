/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendario.jornadas.liga.portugal.support;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import mpes.sorteio.calendario.jornadas.liga.portugal.model.Championship;
import mpes.sorteio.calendario.jornadas.liga.portugal.model.Team;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author vitorsantos
 */
public class SaveToXML {

    private Championship c;
    private String address;
    private String errorMsg;

    public SaveToXML(Championship newC, String newAddress) {
        c = newC;
        address = newAddress;
        errorMsg = new String();
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public boolean start() {
        try {
            if (c.getTeams().isEmpty()) {
                throw new IllegalArgumentException("Campeonato sem equipas.");
            }

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            //Championship tag
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("championship");
            doc.appendChild(rootElement);

            //Creation Time tag
            Element creationTime = doc.createElement("creationTime");
            creationTime.appendChild(doc.createTextNode(CalendarSupport.writeCurrentTime()));
            rootElement.appendChild(creationTime);

            //Teams tag
            Element teams = doc.createElement("teams");
            rootElement.appendChild(teams);

            //Team tag for each team
            for (Team t : c.getTeams()) {
                //Main tag
                Element team = doc.createElement("team");
                teams.appendChild(team);

                //Name tag
                Element teamName = doc.createElement("name");
                teamName.appendChild(doc.createTextNode(t.getTeamName()));
                team.appendChild(teamName);

                for (String nt : t.getNeighbourTeams()) {
                    //Neighbour Teams tag
                    Element teamNeighbours = doc.createElement("neighbours");
                    teamNeighbours.appendChild(doc.createTextNode(nt));
                    team.appendChild(teamNeighbours);
                }

                //Type tag
                Element teamType = doc.createElement("type");
                teamType.appendChild(doc.createTextNode(t.getTeamType()));
                team.appendChild(teamType);
            }

            //Build xml
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(address).toURI().getPath());

            transformer.transform(source, result);

            return true;
        } catch (IllegalArgumentException msg) {
            errorMsg = msg.getMessage();
            return false;
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(SaveToXML.class.getName()).log(Level.SEVERE, null, ex);
            errorMsg = "Preparação do XML falhou.";
            return false;
        } catch (TransformerException ex) {
            Logger.getLogger(SaveToXML.class.getName()).log(Level.SEVERE, null, ex);
            errorMsg = "Transformação do XML falhou.";
            return false;
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(SaveToXML.class.getName()).log(Level.SEVERE, null, ex);
            errorMsg = "Construção do documento-base falhou.";
            return false;
        }
    }
}
