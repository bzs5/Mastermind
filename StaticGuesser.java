package mastermind;

public class StaticGuesser extends Guesser {

    public StaticGuesser() {
        super();
    }

    @Override
    public String guess() {
        turn++ ;
        switch (turn) {
        case 1:
            lastGuess= "1221";
            break;
        case 2:
            lastGuess= "2354";
            break;
        case 3:
            lastGuess= "3311";
            break;
        case 4:
            lastGuess= "4524";
            break;
        case 5:
            lastGuess= "5656";
            break;
        case 6:
            lastGuess= "6643";
            break;
        default:
            lastGuess= remaining.get(0);
        }
        return lastGuess;
    }

}
