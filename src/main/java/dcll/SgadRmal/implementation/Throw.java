package dcll.SgadRmal.implementation;

import dcll.SgadRmal.exceptions.InvalidScoreException;
import dcll.SgadRmal.interfaces.IThrow;

/**
 * Created by romain on 19/03/15.
 */
public class Throw implements IThrow {

    /**
     * Score for the first try.
     */
    private int first;
    /**
     * Score for the second try if not a strike.
     */
    private int second;
    /**
     * Type of the throw.
     */
    private ThrowType type;
    /**
     * Minimum score for a try.
     */
    protected static final int MIN = 0;
    /**
     * Maximum score for a try.
     */
    protected static final int MAX = 10;
    /**
     * Error message if attempt to score more then the max at the second try.
     */
    protected static final String ERR_TOO_HIGH = "Score is too high";
    /**
     * Error message if score is not between min and max value.
     */
    protected static final String ERR_VALUE = "Incorrect value for a score";

    /**
     * Constructor for Throw.
     */
    public Throw() {
        first = -1;
        second = -1;
        type = null;
    }

    @Override
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

    @Override
    public void setSecond(final int score) throws InvalidScoreException {
        if (first < MIN) {
            throw new InvalidScoreException("First try not done");
        } else if (type == ThrowType.STRIKE) {
            throw new InvalidScoreException("Try not allowed");
        } else if (first + score > MAX) {
            throw new InvalidScoreException(ERR_TOO_HIGH);
        } else {
            second = score;
            if (first + second == MAX) {
                type = ThrowType.SPARE;
            } else {
                type = ThrowType.NORMAL;
            }
        }
    }

    @Override
    public final int getFirst() {
        return first;
    }

    @Override
    public final int getSecond() {
        return second;
    }

    @Override
    public final ThrowType getType() {
        return type;
    }

    /**
     * Define the type of the throw.
     *
     * @param aType ThrowType
     */
    protected final void setType(final ThrowType aType) {
        this.type = aType;
    }

    /**
     * Define the score of the second try.
     *
     * @param score score of the try
     */
    protected final void setTheSecond(final int score) {
        second = score;
    }

}
