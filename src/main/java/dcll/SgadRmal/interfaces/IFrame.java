package dcll.SgadRmal.interfaces;

import dcll.SgadRmal.exceptions.InvalidFrameException;
import dcll.SgadRmal.implementation.LastThrow;
import dcll.SgadRmal.implementation.Throw;

/**
 * Created by seb on 19/03/15.
 */
public interface IFrame {
    /**
     * Add a Throw to the game.
     *
     * @param t Throw that will be added
     */
    void addThrow(Throw t) throws InvalidFrameException;

    /**
     * Add the last throw of the game.
     *
     * @param t The last throw
     */
    void addLastThrow(LastThrow t) throws InvalidFrameException;

    /**
     * Calculate the score of a bowling game.
     *
     * @return Score
     */
    int computeScore() throws InvalidFrameException;
}

