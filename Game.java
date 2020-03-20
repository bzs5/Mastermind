package mastermind;

import java.util.Scanner;

/** An instance of a Mastermind game with a given code.
 *
 * @author brendansullivan */
public class Game {
    String ans;

    /** Constructor. Creates a game with the provided code.
     *
     * @param c, the user's code */
    public Game(String c) {
        ans= c;
    }

    /** Runs the game. The code is taken from a keyboard input. */
    public static void main(String[] args) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter your code: ");
        String input= scanner.nextLine();
        Game game= new Game(input);
        StaticGuesser guesser= new StaticGuesser();
        int result= 0;
        do {
            String guess= guesser.guess();
            System.out.println("Guess " + guesser.getTurn() + ": " + guess);
            result= Guesser.result(guess, game.ans);
            System.out.println("Result: " + result / 10 + " perfect, " + result % 10 + " moved");
            guesser.remove(result);
        } while (result != 40);
    }
}
