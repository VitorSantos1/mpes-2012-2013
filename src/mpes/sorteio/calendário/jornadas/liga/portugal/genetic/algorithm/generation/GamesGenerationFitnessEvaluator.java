/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendário.jornadas.liga.portugal.genetic.algorithm.generation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import mpes.sorteio.calendário.jornadas.liga.portugal.model.Team;
import org.uncommons.maths.binary.BitString;
import org.uncommons.watchmaker.framework.FitnessEvaluator;

/**
 *
 * @author vitorsantos
 */
public class GamesGenerationFitnessEvaluator implements FitnessEvaluator<BitString> {

    public ArrayList<Team> teams;
    public int nBitsPerTeam;
    public HashMap<BitString, Team> teamsByGene;

    public GamesGenerationFitnessEvaluator(ArrayList<Team> t, int nBits) {
        teams = t;
        nBitsPerTeam = nBits;
        teamsByGene = new HashMap<BitString, Team>();
        
        while(!(teams.size() <= 0)){
            this.attributeGeneToATeam();
        }
    }

    //Fitness is calculated here.
    @Override
    public double getFitness(BitString t, List<? extends BitString> list) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //Here, it evaluates if the fittest individual has the highest score possible or not.
    @Override
    public boolean isNatural() {
        return true;
    }

    //Attribute a gene for each team
    private void attributeGeneToATeam() {
        /**
         * Steps: 1. See the number of elements already present in the hash map.
         * 2. Determine the next bit string sequence. 
         * 2.1 If the sequence has a lower number of bits than nBitsPerTeam, add the remainder to the left. 
         * 3. Select a random team for the sequence.
         */
        int nElemsInHash = teamsByGene.size();
        String newGene = Integer.toBinaryString(nElemsInHash);
        
        while(newGene.length() < nBitsPerTeam){
            newGene = "0" + newGene;
        }
        
        int randomTeamIndex = new Random().nextInt(teams.size());
        
        teamsByGene.put(new BitString(newGene), teams.get(randomTeamIndex));
        
        teams.remove(randomTeamIndex);
    }

    //Fitness computing functions
    public double detectForbiddenGames(BitString t) {
        return 0.0;
    }

    public double detectConsecutiveBigGames(BitString t) {
        return 0.0;
    }

    public double detectAwayGamesFor2GeographicallyCloseTeams(BitString t) {
        return 0.0;
    }

    public double detectHomeGamesFor2GeographicallyCloseTeams(BitString t) {
        return 0.0;
    }

    public double detectTwoOrMoreBigGamesOnTheSameStadium(BitString t) {
        return 0.0;
    }

    public double detectIfFirstAndLastGameAreBothAwayOrHome(BitString t) {
        return 0.0;
    }

    public double detectMirrorImageOfHomeTeam(BitString t) {
        return 0.0;
    }
}
