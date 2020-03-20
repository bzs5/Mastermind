package mastermind;

import java.util.ArrayList;

/** A superclass for all the guessing algorithms. Designed to avoid repeating identical methods and
 * processing between subclasses, specifically for remove, result, and initializing remaining.
 *
 * @author brendansullivan */
public class Guesser {

    /** The value of the last guess, necessary for the remove method. */
    public String lastGuess;

    /** The current list of remaining possible answers. Almost all guessing algorithms will use this
     * list and attempt to narrow it down until only the true answer remains. */
    public ArrayList<String> remaining= new ArrayList<>();

    /** The current guess number. */
    protected int turn= 0;

    /** Constructor: nested loops initialize "remaining" with all the possible codes. */
    public Guesser() {
        super();
        for (int a= 1; a <= 6; a++ ) {
            for (int b= 1; b <= 6; b++ ) {
                for (int c= 1; c <= 6; c++ ) {
                    for (int d= 1; d <= 6; d++ ) {
                        remaining.add("" + a + b + c + d);
                    }
                }
            }
        }
    }

    /** @return turn, the current number of the guess. */
    public int getTurn() {
        return turn;
    }

    /** Stub method, since guessing a code is what defines each distinct guessing algorithm.
     * 
     * @return guess, the current guess at the solution. */
    public String guess() {
        return "";
    }

    /** Remove codes from remaining that have been disqualified as the possible answer. Performed by
     * creating a new list, and only copying over the codes that are consistent with the most recent
     * guess and feedback. Otherwise the code could not be the answer, as the given feedback would
     * not have been returned.
     * 
     * @param feedback, the result returned from the most recent guess. */
    public void remove(int feedback) {
        ArrayList<String> newVals= new ArrayList<>();
        for (String ans : remaining) {
            if (result(lastGuess, ans) == feedback) {
                newVals.add(ans);
            }
        }
        remaining= newVals;
    }

    /** Determines the result of a given guess and answer. Calculates the amount of exact hits, and
     * then the amount of hits that are shifted. Note that this method is symmetric, i.e result(a,b)
     * = result(b,a).
     *
     * @param guess, the algorithm's guess
     * @param ans,   the answer we check the guess against
     * @return result, a two-digit int where the first digit is the number of exactly correct digits
     *         and the second is the number of shifted digits. */
    public static int result(String guess, String ans) {
        int exact= 0;
        int moved= 0;
        int[] guessCount= new int[6];
        int[] ansCount= new int[6];
        int sum= 0;
        for (int i= 0; i < 4; i++ ) {
            int a= Character.getNumericValue(guess.charAt(i));
            int b= Character.getNumericValue(ans.charAt(i));
            if (a == b) {
                exact++ ;
            }
            guessCount[a - 1]++ ;
            ansCount[b - 1]++ ;
        }
        for (int j= 0; j < 6; j++ ) {
            sum+= Math.min(guessCount[j], ansCount[j]);
        }
        moved= sum - exact;
        return 10 * exact + moved;
    }
}
