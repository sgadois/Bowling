package dcll.SgadRmal.interfaces;

/**
 * Created by seb on 19/03/15.
 */
public interface IFrame {
    /**
     * Add a Throw to the game.
     * @param t     Throw that will be added
     */
    void addThrow(IThrow t);

    /**
     * Calculate the score of a bowling game.
     * @return  Score
     */
    int computeScore();
}

