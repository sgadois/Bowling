package dcll.SgadRmal.implementation;

import dcll.SgadRmal.exceptions.InvalidScoreException;
import dcll.SgadRmal.interfaces.IThrow;

/**
 * Created by romain on 19/03/15.
 */
public class Throw implements IThrow {

    protected int first;
    protected int second;
    protected ThrowType type;
    protected final static int MIN = 0;
    protected final static int MAX = 10;
    protected final static String ERR_TOO_HIGH = "Score is too high";
    protected final static String ERR_VALUE = "Incorrect value for a score";

    public Throw() {
        first = -1;
        second = -1;
        type = null;
    }

    @Override
    final public void setFirst(int score) throws InvalidScoreException {
        if (score < MIN || score > MAX)
            throw new InvalidScoreException(ERR_VALUE);
        else {
            first = score;
            if (first == MAX) {
                type = ThrowType.STRIKE;
            }
        }
    }

    @Override
    public void setSecond(int score) throws InvalidScoreException {
        if (first < MIN)
            throw new InvalidScoreException("First try not done");
        else if (type == ThrowType.STRIKE)
            throw new InvalidScoreException("Try not allowed");
        else if (first + score > MAX)
            throw new InvalidScoreException(ERR_TOO_HIGH);
        else {
            second = score;
            if (first + second == MAX)
                type = ThrowType.SPARE;
            else
                type = ThrowType.NORMAL;
        }
    }

    @Override
    final public int getFirst() {
        return first;
    }

    @Override
    final public int getSecond() {
        return second;
    }

    @Override
    final public ThrowType getType() {
        return type;
    }
}