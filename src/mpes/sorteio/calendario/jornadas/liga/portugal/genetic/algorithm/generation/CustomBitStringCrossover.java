/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendario.jornadas.liga.portugal.genetic.algorithm.generation;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.uncommons.maths.binary.BitString;
import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.operators.BitStringCrossover;

/**
 *
 * @author vitorsantos
 */
public class CustomBitStringCrossover extends BitStringCrossover {

    private int nBits;

    /**
     * Default is single-point cross-over, applied to all parents.
     */
    public CustomBitStringCrossover(int nBit) {
        super(1);
        nBits = nBit;
    }

    /**
     * Cross-over with a fixed number of cross-over points.
     *
     * @param crossoverPoints The constant number of cross-over points to use
     * for all cross-over operations.
     */
    public CustomBitStringCrossover(int crossoverPoints, int nBit) {
        super(crossoverPoints);
        nBits = nBit;
    }

    /**
     * Cross-over with a fixed number of cross-over points. Cross-over may or
     * may not be applied to a given pair of parents depending on the
     * {@code crossoverProbability}.
     *
     * @param crossoverPoints The constant number of cross-over points to use
     * for all cross-over operations.
     * @param crossoverProbability The probability that, once selected, a pair
     * of parents will be subjected to cross-over rather than being copied,
     * unchanged, into the output population.
     */
    public CustomBitStringCrossover(int crossoverPoints, Probability crossoverProbability, int nBit) {
        super(crossoverPoints, crossoverProbability);
        nBits = nBit;
    }

    /**
     * Cross-over with a variable number of cross-over points.
     *
     * @param crossoverPointsVariable A random variable that provides a number
     * of cross-over points for each cross-over operation.
     */
    public CustomBitStringCrossover(NumberGenerator<Integer> crossoverPointsVariable, int nBit) {
        super(crossoverPointsVariable);
        nBits = nBit;
    }

    /**
     * Sets up a cross-over implementation that uses a variable number of
     * cross-over points. Cross-over is applied to a proportion of selected
     * parent pairs, with the remainder copied unchanged into the output
     * population. The size of this evolved proportion is controlled by the
     * {@code crossoverProbabilityVariable} parameter.
     *
     * @param crossoverPointsVariable A variable that provides a (possibly
     * constant, possibly random) number of cross-over points for each
     * cross-over operation.
     * @param crossoverProbabilityVariable A variable that controls the
     * probability that, once selected, a pair of parents will be subjected to
     * cross-over rather than being copied, unchanged, into the output
     * population.
     */
    public CustomBitStringCrossover(NumberGenerator<Integer> crossoverPointsVariable,
            NumberGenerator<Probability> crossoverProbabilityVariable, int nBit) {
        super(crossoverPointsVariable, crossoverProbabilityVariable);
        nBits = nBit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected List<BitString> mate(BitString parent1, BitString parent2, int numberOfCrossoverPoints, Random rng) {
        if (parent1.getLength() != parent2.getLength()) {
            throw new IllegalArgumentException("Cannot perform cross-over with different length parents.");
        }
        BitString offspring1 = parent1.clone();
        BitString offspring2 = parent2.clone();
        // Apply as many cross-overs as required.
        for (int i = 0; i < numberOfCrossoverPoints; i++) {
            // Cross-over index is always greater than zero and less than
            // the length of the parent so that we always pick a point that
            // will result in a meaningful cross-over.
            // Overriden part of parent class applied here!!! 
            boolean numberGenerated = false;

            while (!numberGenerated) {
                int randomNumber = rng.nextInt(parent1.getLength() - 1);

                if (randomNumber % nBits == 0) {
                    int crossoverIndex = (1 + rng.nextInt(parent1.getLength() - 1));
                    offspring1.swapSubstring(offspring2, 0, crossoverIndex);
                    numberGenerated = true;
                }
            }
        }
        List<BitString> result = new ArrayList<BitString>(2);
        result.add(offspring1);
        result.add(offspring2);
        return result;
    }
}
