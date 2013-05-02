/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mpes.sorteio.calendario.jornadas.liga.portugal.model;

/**
 * Implementation of symmetrical latin squares for round-robin scheduling
 * problems
 *
 * @author marcelo
 */
public class LatinSquare {

    /**
     * The number of teams in the square
     */
    private final int numTeams;
    /**
     * The array with scheduling data
     */
    private final int[] array;

    /**
     * Calculates the sum of the first {@code n} integers
     *
     * @param n The number of integers to sum
     * @return The sum of the first {@code n} integers
     */
    private static int sum(int n) {
        return n * (n + 1) / 2;
    }

    /**
     * Creates a latin square with the given number of teams
     *
     * @param numTeams The number of teams
     */
    public LatinSquare(int numTeams) {
        this.numTeams = numTeams;
        this.array = new int[sum(numTeams)];
        makeSchedule(numTeams);
    }

    /**
     * Copies a latin square
     *
     * @param original The original latin square
     */
    public LatinSquare(LatinSquare original) {
        // Copies the number of teams
        this.numTeams = original.numTeams;
        // Copies the schedule
        this.array = new int[original.array.length];
        for (int i = 0; i < array.length; i++) {
            array[i] = original.array[i];
        }
    }

    /**
     * Gets the index of a game in the array
     *
     * @param x The number of a team, between 1 and {@link #numTeams}
     * @param y The number of another team, between 1 and {@link #numTeams}
     * @return the index of the game in the array
     */
    private int getIndex(int x, int y) {
        // Get the indices based on 0
        final int small = Math.min(x, y) - 1;
        final int big = Math.max(x, y) - 1;
        // Position of a block. This is the same as sum(numTeams)-sum(small)
        final int offset = (2 * numTeams - small + 1) * small / 2;
        // The index is the block offset + the position offset
        return offset + numTeams - big - 1;
    }

    @Override
    public String toString() {
        // The string builder
        final StringBuilder sb = new StringBuilder();
        // Walks over the matrix and builds it's representation
        for (int i = 1; i <= numTeams; i++) {
            for (int j = 1; j <= numTeams; j++) {
                // Appends the slot number
                final int slot = array[getIndex(i, j)];
                sb.append(String.format("%3d", Math.abs(slot)));
                // Appends + or - to say who plays home
                if (slot > 0) {
                    sb.append('+');
                } else if (slot < 0) {
                    sb.append('-');
                } else {
                    sb.append(' ');
                }
            }
            sb.append('\n');
        }
        // Finishes
        return sb.toString();
    }

    /**
     * Creates a valid schedule for the given number of teams
     *
     * @param nTeams The number of teams
     */
    private void makeSchedule(int nTeams) {
        // If is an odd number
        if (nTeams % 2 != 0) {
            // Creates a schedule using modular aritmetics
            for (int i = 1; i <= nTeams; i++) {
                for (int j = i; j <= nTeams; j++) {
                    // Calculates the slot
                    final int slot = (i + j + 2) % nTeams + 1;
                    // Stores it in the array, distributing the home and non-home games
                    if (slot % 2 == 0) {
                        array[getIndex(i, j)] = slot;
                    } else {
                        array[getIndex(i, j)] = -slot;
                    }
                }
            }
        } else {
            // Is an even number

            // Creates a schedule for a system with one less team
            makeSchedule(nTeams - 1);
            // Fits the schedule to the bigger problem
            for (int i = 1; i <= nTeams; i++) {
                // Gets the indices
                final int diagIndex = getIndex(i, i);
                final int lastIndex = getIndex(i, nTeams);
                // Moves the value in the diagonal to the last column
                array[lastIndex] = array[diagIndex];
                // Stores an unreachable slot in the diagonal
                array[diagIndex] = nTeams;
            }
        }
    }

    /**
     * Gets the slot of a game
     *
     * @param x The number of a team, between 1 and {@link #numTeams()}
     * @param y The number of another team, between 1 and {@link #numTeams()}
     * @return the slot of the game
     */
    public int getSlot(int x, int y) {
        return Math.abs(array[getIndex(x, y)]);
    }

    /**
     * Tests if the team with the smaller ID plays home
     *
     * @param x The number of a team, between 1 and {@link #numTeams()}
     * @param y The number of another team, between 1 and {@link #numTeams()}
     * @return {@code true} if the team with the smaller identifier plays home
     */
    public boolean isSmallerHome(int x, int y) {
        return array[getIndex(x, y)] < 0;
    }

    /**
     * Switches the games of two slots
     *
     * @param s1 The number of one slot. It's a number between 1 and
     * {@link #numSlots()}
     * @param s2 The number of the other slot. It's a number between 1 and
     * {@link #numSlots()}
     */
    public void switchSlots(int s1, int s2) {
        // Replaces the slots in the whole array
        for (int i = 0; i < array.length; i++) {
            if (array[i] == s1) {
                array[i] = s2;
            } else if (array[i] == -s1) {
                array[i] = -s2;
            } else if (array[i] == s2) {
                array[i] = s1;
            } else if (array[i] == -s2) {
                array[i] = -s1;
            }
        }
    }

    /**
     * Switches the teams of a game. The visitor becomes the home team and vice
     * versa
     *
     * @param x The number of a team. Must be between 1 and {@link #numTeams()}
     * @param y The number of another team. Must be between 1 and
     * {@link #numTeams()}
     */
    public void switchVisitor(int x, int y) {
        // Gets the index
        final int index = getIndex(x, y);
        // Changes
        array[index] = -array[index];
    }

    /**
     * Switches the schedule of two teams.
     *
     * @param x The number of a team. Must be between 1 and {@link #numTeams()}
     * @param y The number of another team. Must be between 1 and
     * {@link #numTeams()}
     */
    public void switchTeams(int x, int y) {
        // Walks over the the lines/columns os the teams switching the schedule
        for (int other = 1; other <= numTeams; other++) {
            // If it can be switched
            if (other != x && other != y) {
                final int xIndex = getIndex(x, other);
                final int yIndex = getIndex(y, other);
                // Switch the schedules
                int slot = array[xIndex];
                array[xIndex] = array[yIndex];
                array[yIndex] = slot;
            }
        }
        // Switches the slots they don't play
        final int xIndex = getIndex(x, x);
        final int yIndex = getIndex(y, y);
        // Switch the schedules
        int slot = array[xIndex];
        array[xIndex] = array[yIndex];
        array[yIndex] = slot;
    }

    /* *
     * Changes the slot of a single game
     *
     * @param x The number of a team. Must be between 1 and {@link #numTeams()}
     * @param y The number of another team. Must be between 1 and
     * {@link #numTeams()}
     * @param slot The new slot of the game. Must be 1 and {@link #numSlots()}
     * /
     public void changeSlot(int x, int y, final int slot) {
     // The old slot of the game
     int oldSlot;
     int newSlot = slot;


     // While we didn't reach a condition where the square is valid
     while (x <= numTeams) {
     // Gets the index of the game
     final int index = getIndex(x, y);
     // Saves the old slot
     oldSlot = array[index];
     // Replaces
     if (oldSlot > 0) {
     array[index] = newSlot;
     } else {
     array[index] = -newSlot;
     oldSlot = -oldSlot;
     }
     // Find the adversary for the game X used to play in this slot
     int foe = 1;
     while (foe <= numTeams && (getSlot(x, foe) != newSlot || foe == y)) {
     foe++;
     }

     System.out.println("changeSlot(" + x + ", " + y + ", " + newSlot + ")");
     //System.out.println(this);

     / *
     * The slot of the game of X with foe should be changed the same way
     * as we did with this one, so we switch the variables around and
     * repeat this loop
     * / 
     newSlot = oldSlot;
     y = x;
     x = foe;
     }
     } */
    /**
     * Gets the number of slots in the championship
     *
     * @return the number of slots
     */
    public int numSlots() {
        return numSlots(numTeams);
    }

    /**
     * Gets the number of teams in the championship
     *
     * @return the number of teams
     */
    public int numTeams() {
        return numTeams;
    }

    /**
     * Gets the number of slots in the championship
     *
     * @param numTeams The number of teams in the championship
     * @return the number of slots
     */
    public static int numSlots(int numTeams) {
        if (numTeams % 2 == 0) {
            return numTeams - 1;
        } else {
            return numTeams;
        }
    }
    /*
     public static void main(String[] args) {
     LatinSquare sq = new LatinSquare(6);
     System.out.println(sq);
     sq.switchTeams(1, 2);
     System.out.print(sq);
     }*/
}
