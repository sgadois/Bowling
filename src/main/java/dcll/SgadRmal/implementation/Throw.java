package dcll.SgadRmal.implementation;

import dcll.SgadRmal.exceptions.FirstTryNotDoneException;
import dcll.SgadRmal.exceptions.IncorrectValueForTryException;
import dcll.SgadRmal.interfaces.IThrow;

/**
 * Created by seb on 19/03/15.
 * @author Romain
 *
 */
public class Throw implements IThrow {

    protected int first;
    protected int second;
    protected ThrowType type;
    protected final static int MIN = 0;
    protected final static int MAX = 10;

    public Throw() {
        first = -1;
        second = -1;
        type = null;
    }

    @Override
    final public void setFirst(int score) throws IncorrectValueForTryException {
        if (score < MIN || score > MAX)
            throw new IncorrectValueForTryException("Value is incorrect");
        else {
            first = score;
            if (first == MAX) {
                type = ThrowType.STRIKE;
                second = 0;
            }
        }
    }

    @Override
    public void setSecond(int score) throws FirstTryNotDoneException, IncorrectValueForTryException {
        if (first < MIN)
            throw new FirstTryNotDoneException("First try is not set");
        else if (type == ThrowType.STRIKE || first + score > MAX)
            throw new IncorrectValueForTryException("Value is incorrect");
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