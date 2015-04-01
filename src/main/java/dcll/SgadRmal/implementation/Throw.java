package dcll.SgadRmal.implementation;

import dcll.SgadRmal.exceptions.InvalidScoreException;

/**
 * Created by romain on 19/03/15.
 */
public class Throw extends AThrow {

    /**
     * Constructor for Throw.
     */
    public Throw() {
        super();
    }

    @Override
    public final void setSecond(final int score) throws InvalidScoreException {
        if (getFirst() < MIN) {
            throw new InvalidScoreException("First try not done");
        } else if (getType() == ThrowType.STRIKE) {
            throw new InvalidScoreException("Try not allowed");
        } else if (getFirst() + score > MAX) {
            throw new InvalidScoreException(ERR_TOO_HIGH);
        } else {
            setHisSecond(score);
            if (getFirst() + getSecond() == MAX) {
                setType(ThrowType.SPARE);
            } else {
                setType(ThrowType.NORMAL);
            }
        }
    }

}
