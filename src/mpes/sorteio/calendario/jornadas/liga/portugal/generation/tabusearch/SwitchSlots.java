/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendario.jornadas.liga.portugal.generation.tabusearch;

import mpes.sorteio.calendario.jornadas.liga.portugal.model.LatinSquare;
import org.coinor.opents.Solution;

/**
 * Move that switches 2 slots
 *
 * @author marcelo
 */
public class SwitchSlots implements RRTSMove {

    private final int slot1;
    private final int slot2;

    /**
     * Create a switch slots move
     *
     * @param slot1 One slot
     * @param slot2 The other slot
     */
    public SwitchSlots(int slot1, int slot2) {
        this.slot1 = slot1;
        this.slot2 = slot2;
    }

    @Override
    public void operateOn(Solution soln) {
        move((RRTSSolution) soln);
    }

    @Override
    public void move(RRTSSolution soln) {
        soln.square.switchSlots(slot1, slot2);
    }

    @Override
    public void undo(RRTSSolution soln) {
        move(soln);
    }

    @Override
    public int hashCode() {
        return (slot1 << 9) | (slot2 << 1) | 0x1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SwitchSlots other = (SwitchSlots) obj;
        if (this.slot1 != other.slot1) {
            return false;
        }
        if (this.slot2 != other.slot2) {
            return false;
        }
        return true;
    }
}
