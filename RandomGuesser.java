package mastermind;

import java.util.Random;

/** A guessing algorithm made to test certain features before working on implementing Knuth's
 * algorithm.
 *
 * @author brendansullivan */
public class RandomGuesser extends Guesser {

    /** Needed to guess a random possible solution. */
    Random rand= new Random();

    /** A constructor. All the fields besides rand exist in the superclass. */
    public RandomGuesser() {
        super();
    }

    /** Very simple algorithm: we guess randomly from the list of remaining possible solutions. Turn
     * is incremented, and lastGuess is updated. */
    @Override
    public String guess() {
        turn++ ;
        int index= rand.nextInt(remaining.size());
        lastGuess= remaining.get(index);
        return lastGuess;
    }

}
