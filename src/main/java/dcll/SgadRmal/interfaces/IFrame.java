package dcll.SgadRmal.interfaces;

import dcll.SgadRmal.exceptions.InvalidFrameException;

/**
 * Created by seb on 19/03/15.
 */
public interface IFrame {
    /**
     * Add a Throw to the game.
     *
     * @param t Throw that will be added
     */
    void addThrow(IThrow t);

    /**
     * Add the last throw of the game.
     *
     * @param t The last throw
     */
    void addLastThrow(ILastThrow t) throws InvalidFrameException;

    /**
     * Calculate the score of a bowling game.
     *
     * @return Score
     */
    int computeScore();
}

