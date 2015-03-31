package dcll.SgadRmal.implementation;


import dcll.SgadRmal.exceptions.InvalidScoreException;
import dcll.SgadRmal.interfaces.ILastThrow;

/**
 * Created by romain on 19/03/15.
 */
public class LastThrow extends Throw implements ILastThrow {

    private int third;

    public LastThrow() {
        super();
        third = -1;
    }

    @Override
    public void setSecond(final int score) throws InvalidScoreException {
        if (first < MIN) {
            throw new InvalidScoreException("First try not done");
        }
        else if (score < MIN || score > MAX) {
            throw new InvalidScoreException(ERR_VALUE);
        }
        else if (type != ThrowType.STRIKE && first + score > MAX) {
            throw new InvalidScoreException(ERR_TOO_HIGH);
        }
        else {
            second = score;
            if (first + second == MAX || type == ThrowType.STRIKE) {
                if (type == null) {
                    type = ThrowType.SPARE;
                }
            }
            else {
                type = ThrowType.NORMAL;
                third = 0;
            }
        }
    }

    @Override
    public void setThird(final int score) throws InvalidScoreException {
        if (type == ThrowType.NORMAL) {
            throw new InvalidScoreException("Try not allowed");
        }
        else if (second < MIN) {
            throw new InvalidScoreException("Second try not done");
        }
        else if (score < MIN || score > MAX) {
            throw new InvalidScoreException(ERR_VALUE);
        }
        else if (second < MAX && second + score > MAX) {
            throw new InvalidScoreException(ERR_TOO_HIGH);
        }
        else {
            third = score;
        }
    }

    @Override
    public final int getThird() {
        return third;
    }
}
