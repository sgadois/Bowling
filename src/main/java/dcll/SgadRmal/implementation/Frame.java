package dcll.SgadRmal.implementation;

import dcll.SgadRmal.exceptions.LastThrowErrorException;
import dcll.SgadRmal.interfaces.IFrame;
import dcll.SgadRmal.interfaces.ILastThrow;
import dcll.SgadRmal.interfaces.IThrow;

/**
 * Created by seb on 19/03/15.
 */
public class Frame implements IFrame {

    /**
     * The number of throw for a game, minus the last throw
     */
    private static final int NB_THROW_GAME = 9;

    /**
     * A table containing the throw of the game
     */
    private IThrow iThrows[];

    /**
     * The last throw of the game
     */
    private ILastThrow iLastThrow;

    /**
     * The number of throw done
     */
    private int nbThrowDone;

    /**
     * The score of the game
     */
    private int score;

    public Frame() {
        iThrows = new IThrow[NB_THROW_GAME];
        score = 0;
        nbThrowDone = 0;
    }

    @Override
    public void addThrow(final IThrow t) {
        if (nbThrowDone < NB_THROW_GAME) {
            iThrows[nbThrowDone] = t;
        }
        nbThrowDone++;
    }

    @Override
    public final void addLastThrow(final ILastThrow t) throws LastThrowErrorException {
        if (nbThrowDone < NB_THROW_GAME) {
            throw new LastThrowErrorException("Not enough throw done before the last");
        } else {
            iLastThrow = t;
        }
    }

    @Override
    public final int computeScore() {

        return score;
    }
}
