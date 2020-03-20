package mastermind;

import java.util.LinkedList;
import java.util.List;

public class KnuthGuesser extends Guesser {

    /** A static list of every possible code.  Necessary for this algorithm.  
     */
    private final String[] codes;

    /** A static list of every possible result.  Also needed.
     * 
     */
    private final int[] results= { 0, 1, 2, 3, 4, 10, 11, 12, 13, 20, 21, 22, 30, 40 };

    /** A constructor.  After the superclass creates remaining, we use it
     *  to initialize codes without repeating processing.
     */
    public KnuthGuesser() {
        super();
        codes= remaining.toArray(new String[remaining.size()]);
    }

    /** Knuth's algorithm.  It is guaranteed to work in 5 or fewer moves.
     * It works as follows:
     * 
     * 1. The starting guess is 1122, as other starting guesses do not 
     *  guaranteed 5 or fewer moves.
     *  
     * 2. For every possible code, not just the ones remaining, we iterate 
     *  through every possible result.  The number of codes that would be left
     *  in remaining is this code's score  (currScore) for the given result.
     *  
     * 3. We determine the highest score amongst all possible results for each 
     *  code.  We call this maxScore.
     *  
     * 4. We create a list (options) of the codes with the lowest score.  The 
     *  value  of this score is called minimax.  If a  given code has a lower 
     *  maxScore than minimax, we empty the list and replace with this code, 
     *  as well as update minimax.  
     *  
     * 5. Once the algorithm finishes iterating through the codes, we have
     *  a list of options.  We iterate through and attempt to use codes that 
     *  are still possible solutions.  If not, it is fine to use one that isn't,
     *  since it will still ultimately help us narrow down faster.  The list
     *  of options is sorted, and so we always use the lowest numerical option 
     *  first (Knuth's convention).  
     */
    @Override
    public String guess() {
        turn++ ;
        if (turn == 1) {
            lastGuess= "1122";
            return lastGuess;
        }
        List<String> options= new LinkedList<>();
        int minimax= 1297;
        for (String s : codes) {
            int maxScore= 0;
            for (int r : results) {
                int currScore= 0;
                for (String ans : remaining) {
                    if (result(ans, s) == r) {
                        currScore++ ;
                    }
                }
                maxScore= Math.max(maxScore, currScore);
            }
            if (maxScore < minimax) {
                options.clear();
                minimax= maxScore;
            }
            if (maxScore == minimax) {
                options.add(s);
            }

        }
        options.sort(null);
        boolean found= false;
        int i= 0;
        while (!found && i < options.size()) {
            if (remaining.contains(options.get(i))) {
                found= true;
                lastGuess= options.get(i);
            }
            i++ ;
        }
        if (!found) {
            lastGuess= options.get(0);
        }
        return lastGuess;
    }

}
