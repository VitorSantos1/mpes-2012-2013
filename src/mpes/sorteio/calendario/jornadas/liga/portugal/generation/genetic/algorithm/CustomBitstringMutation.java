/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendario.jornadas.liga.portugal.generation.genetic.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.uncommons.maths.binary.BitString;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;

/**
 * Mutator for bit strings that keep the teams valid
 */
public class CustomBitstringMutation implements EvolutionaryOperator<BitString> {

    /**
     * Probability a team will change
     */
    private final double teamMutationProbability;
    /**
     * Number teams
     */
    private final int numTeams;
    /**
     * Number of bits in a team
     */
    private final int numBits;

    /**
     * Creates the mutation operator for bit strings of teams
     *
     * @param teamMutationProbability the probability a team will change
     * @param numTeams the number of teams
     */
    public CustomBitstringMutation(double teamMutationProbability, int numTeams) {
        this.teamMutationProbability = teamMutationProbability;
        this.numTeams = numTeams;

        int numTypes = numTeams + 1; // Number of representations of things
        // Finds the number of bits used to represent the teams + 000
        int nBits = 0;
        do {
            nBits++;
            numTypes >>= 1;
        } while (numTypes != 0);
        this.numBits = nBits;
    }

    @Override
    public List<BitString> apply(List<BitString> list, Random random) {
        System.out.println("MUTATION");
        // List of mutants
        List<BitString> mutants = new ArrayList<BitString>(list.size());

        // For each bit string
        for (BitString bs : list) {
            // Makes a copy
            final BitString cl = bs.clone();
            // Stores the mutant
            mutants.add(cl);
            // For each team in the chromossome
            for (int tIndex = 0; tIndex < cl.getLength() % numBits; tIndex++) {
                // If the team must change
                if (random.nextDouble() < teamMutationProbability) {
                    // The other team
                    final int other;
                    // If it plays home
                    if (random.nextBoolean()) {
                        // The other is a team
                        other = random.nextInt(numTeams) + 1;
                    } else {
                        // It plays out
                        other = 0;
                    }
                    System.out.println (" Bit 0: " + tIndex *numBits + " nbits : " + numBits);
                    // Put it in the gene
                    for (int bit = 0; bit < numBits; bit++) {
                        // Store in the string the bit from the number
                        cl.setBit(tIndex * numBits + bit,
                                (other & (1 << (numBits - bit - 1))) != 0);
                    }
                }
            }
        }
        return mutants;
    }
}
