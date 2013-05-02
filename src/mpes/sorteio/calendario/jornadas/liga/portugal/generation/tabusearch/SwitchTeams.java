/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendario.jornadas.liga.portugal.generation.tabusearch;

import mpes.sorteio.calendario.jornadas.liga.portugal.model.LatinSquare;
import org.coinor.opents.Solution;

/**
 * Move that switches the schedules of 2 teams
 *
 * @author marcelo
 */
public class SwitchTeams implements RRTSMove {

    private final int team1;
    private final int team2;

    /**
     * Creates a switch team move
     *
     * @param team1 One team
     * @param team2 The other team
     */
    public SwitchTeams(int team1, int team2) {
        this.team1 = team1;
        this.team2 = team2;
    }


    @Override
    public void operateOn(Solution soln) {
        move((RRTSSolution) soln);
    }

    @Override
    public void move(RRTSSolution soln) {
        soln.square.switchTeams(team1, team2);
    }

    @Override
    public void undo(RRTSSolution soln) {
        move(soln);
    }

    @Override
    public int hashCode() {
        return (team1 << 9) | (team2 << 1);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SwitchTeams other = (SwitchTeams) obj;
        if (this.team1 != other.team1) {
            return false;
        }
        if (this.team2 != other.team2) {
            return false;
        }
        return true;
    }
    
    
}
