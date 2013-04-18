/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendario.jornadas.liga.portugal.generation.genetic.algorithm;

import java.util.Random;
import org.uncommons.maths.binary.BitString;
import org.uncommons.watchmaker.framework.factories.BitStringFactory;

/**
 *
 * @author vitorsantos
 */
public class CustomBitStringFactory extends BitStringFactory {

    private final int length;
    private final int nTeams;
    private final int geneSize;

    /**
     * @param length The length of all bit strings created by this factory.
     */
    public CustomBitStringFactory(int l, int nt, int gs) {
        super(l);
        length = l;
        nTeams = nt;
        geneSize = gs;
    }

    /**
     * Generates a random bit string, with a uniform distribution of ones and
     * zeroes.
     *
     * @param rng The source of randomness for setting the bits.
     * @return A random bit string of the length configured for this factory.
     */
    public BitString generateRandomCandidate(Random rng) {
        //Aplicar a restrição de executar apenas sequências válidas aqui!!!!

        BitString newString = new BitString(length, rng);
        String temp = newString.toString();
        int index = 0;

        while (!(temp.length() <= 0)) {
            String sequence = temp.substring(0, geneSize);

            while (!(Integer.parseInt(sequence, 2) <= nTeams)) {
                sequence = new BitString(geneSize, rng).toString();
                newString = new BitString(newString.toString().substring(0, geneSize * index)
                        + sequence
                        + newString.toString().substring(geneSize * (index + 1)));
            }

            temp = temp.substring(geneSize);
            index++;
        }

        return newString;
    }
}
