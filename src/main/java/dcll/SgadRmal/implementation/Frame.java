package dcll.SgadRmal.implementation;

import dcll.SgadRmal.exceptions.InvalidFrameException;
import dcll.SgadRmal.interfaces.IFrame;

/**
 * Created by seb on 19/03/15.
 */
public class Frame implements IFrame {

    /**
     * The number of throw for a game, minus the last throw.
     */
    private static final int NB_THROW_GAME = 9;

    /**
     * A table containing the throw of the game.
     */
    private Throw[] iThrows;

    /**
     * The last throw of the game.
     */
    private LastThrow iLastThrow;

    /**
     * The number of throw done.
     */
    private int nbThrowDone;

    /**
     * The score of the game.
     */
    private int score;

    /**
     * Constructor.
     */
    public Frame() {
        iThrows = new Throw[NB_THROW_GAME];
        score = 0;
        nbThrowDone = 0;
    }

    @Override
    public final void addThrow(final Throw t) throws InvalidFrameException {
        if (t.getType() == null) {
            throw new InvalidFrameException("Invalid throw type");
        } else if (nbThrowDone >= NB_THROW_GAME) {
            throw new InvalidFrameException("Too many throw");
        } else {
            iThrows[nbThrowDone] = t;
            nbThrowDone++;
        }
    }

    @Override
    public final void addLastThrow(final LastThrow t) throws InvalidFrameException {
        if (t.getType() == null) {
            throw new InvalidFrameException("Invalid throw type");
        } else if (nbThrowDone < NB_THROW_GAME) {
            throw new InvalidFrameException("Not enough throw done before the last");
        } else {
            iLastThrow = t;
        }
    }

    @Override
    public final int computeScore() throws InvalidFrameException {
        if (nbThrowDone < NB_THROW_GAME) {
            throw new InvalidFrameException("Not enough throw done");
        } else if (iLastThrow == null) {
            throw new InvalidFrameException("Last throw not done");
        } else {
            canComputeScore();
            return score;
        }
    }

    /**
     * Add the score of all the throw into the score of the game.
     */
    private void canComputeScore() {
        for (int i = 0; i < nbThrowDone; i++) {
            switch (iThrows[i].getType()) {
                case NORMAL:
                    addScoreNormalThrow(i);
                    break;
                case SPARE:
                    addScoreSpareThrow(i);
                    break;
                case STRIKE:
                    addScoreStrikeThrow(i);
                    break;
                default:
                    break;
            }
        }
        addScoreLastThrow();
    }

    /**
     * Add the score of the last throw to the score of the game.
     */
    private void addScoreLastThrow() {
        score += iLastThrow.getFirst();
        score += iLastThrow.getSecond();
        if (iLastThrow.getType() != ThrowType.NORMAL) {
            score += iLastThrow.getThird();
        }
    }

    /**
     * Add the score of a normal throw to the score of the game.
     *
     * @param i index of the throw
     */
    private void addScoreNormalThrow(final int i) {
        score += iThrows[i].getFirst();
        score += iThrows[i].getSecond();
    }

    /**
     * Add the score of a spare throw to the score of the game.
     *
     * @param i index of the throw
     */
    private void addScoreSpareThrow(final int i) {
        score += iThrows[i].getFirst();
        score += iThrows[i].getSecond();
        if (i == nbThrowDone - 1) {
            score += iLastThrow.getFirst();
        } else {
            score += iThrows[i + 1].getFirst();
        }
    }

    /**
     * Add the score of a strike throw to the score of the game.
     *
     * @param i index of the throw
     */
    private void addScoreStrikeThrow(final int i) {
        score += iThrows[i].getFirst();
        if (i == nbThrowDone - 2) {
            score += iThrows[i + 1].getFirst();
            if (iThrows[i + 1].getType() == ThrowType.STRIKE) {
                score += iLastThrow.getFirst();
            } else {
                score += iThrows[i + 1].getSecond();
            }
        } else if (i == nbThrowDone - 1) {
            score += iLastThrow.getFirst();
            score += iLastThrow.getSecond();
        } else {
            score += iThrows[i + 1].getFirst();
            if (iThrows[i + 1].getType() == ThrowType.STRIKE) {
                score += iThrows[i + 2].getFirst();
            } else {
                score += iThrows[i + 1].getSecond();
            }
        }
    }

}
