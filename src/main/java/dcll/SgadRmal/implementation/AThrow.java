package dcll.SgadRmal.implementation;

import dcll.SgadRmal.exceptions.InvalidScoreException;

/**
 * Created by romain on 01/04/2015.
 */
public abstract class AThrow {

    /**
     * Score for the first try.
     */
    private int first;

    /**
     * Score for the second try.
     */
    private int second;

    /**
     * Type of the throw.
     */
    private ThrowType type;

    /**
     * Minimum value authorized for score.
     */
    protected static final int MIN = 0;

    /**
     * Maximum value authorized for score.
     */
    protected static final int MAX = 10;

    /**
     * Error message if score is not between min and max.
     */
    protected static final String ERR_VALUE = "Incorrect value for a score";

    /**
     * Error message if second score is too high.
     */
    protected static final String ERR_TOO_HIGH = "Score is too high";

    /**
     * Default constructor.
     */
    public AThrow() {
        first = -1;
        second = -1;
        type = null;
    }

    /**
     *
     * @return score of the first try
     */
    public final int getFirst() {
        return first;
    }

    /**
     *
     * @return score of the second try
     */
    public final int getSecond() {
        return second;
    }

    /**
     *
     * @return type of the throw
     */
    public final ThrowType getType() {
        return type;
    }

    /**
     *
     * @param aType type to define the throw
     */
    protected final void setType(final ThrowType aType) {
        type = aType;
    }

    /**
     * Only for extended class to set the second try
     *
     * @param aSecond score to the second try
     */
    protected final void setHisSecond(final int aSecond) {
        second = aSecond;
    }

    /**
     *
     * @param score to record for the first try
     * @throws InvalidScoreException
     */
    public final void setFirst(final int score) throws InvalidScoreException {
        if (score < MIN || score > MAX) {
            throw new InvalidScoreException(ERR_VALUE);
        } else {
            first = score;
            if (first == MAX) {
                type = ThrowType.STRIKE;
            }
        }
    }

    /**
     *
     * @param score to record for the second try
     * @throws InvalidScoreException
     */
    public abstract void setSecond(final int score) throws InvalidScoreException;
}
