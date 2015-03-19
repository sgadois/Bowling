package dcll.SgadRmal.interfaces;

import dcll.SgadRmal.implementation.Throw;

/**
 * Created by seb on 19/03/15.
 */
public interface IFrame {
    /**
     * Add a Throw to the game.
     * @param t     Throw that will be added
     */
    void addThrow(Throw t);

    /**
     * Calculate the score of a bowling game.
     * @return  Score
     */
    int computeScore();
}

