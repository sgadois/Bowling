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
        if (this.getFirst() < MIN) {
            throw new InvalidScoreException("First try not done");
        }
        else if (score < MIN || score > MAX) {
            throw new InvalidScoreException(ERR_VALUE);
        }
        else if (this.getType() != ThrowType.STRIKE && this.getFirst() + score > MAX) {
            throw new InvalidScoreException(ERR_TOO_HIGH);
        }
        else {
            setTheSecond(score);
            if (this.getFirst() + this.getSecond() == MAX || this.getType() == ThrowType.STRIKE) {
                if (this.getType() == null) {
                    setType(ThrowType.SPARE);
                }
            }
            else {
                setType(ThrowType.NORMAL);
                third = 0;
            }
        }
    }

    @Override
    public void setThird(final int score) throws InvalidScoreException {
        if (this.getType() == ThrowType.NORMAL) {
            throw new InvalidScoreException("Try not allowed");
        }
        else if (this.getSecond() < MIN) {
            throw new InvalidScoreException("Second try not done");
        }
        else if (score < MIN || score > MAX) {
            throw new InvalidScoreException(ERR_VALUE);
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
