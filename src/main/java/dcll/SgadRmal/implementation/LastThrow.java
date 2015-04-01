package dcll.SgadRmal.implementation;


import dcll.SgadRmal.exceptions.InvalidScoreException;

/**
 * Created by romain on 19/03/15.
 */
public class LastThrow extends AThrow {

    /**
     * The third score if spare or strike.
     */
    private int third;

    /**
     * LastThrow constructor.
     */
    public LastThrow() {
        super();
        third = -1;
    }

    @Override
    public final void setSecond(final int score) throws InvalidScoreException {
        if (getFirst() < MIN) {
            throw new InvalidScoreException("First try not done");
        } else if (score < MIN || score > MAX) {
            throw new InvalidScoreException(ERR_VALUE);
        } else if (getType() != ThrowType.STRIKE && getFirst() + score > MAX) {
            throw new InvalidScoreException(ERR_TOO_HIGH);
        } else {
            setHisSecond(score);
            if (getType() == null) {
                if (getFirst() + getSecond() == MAX) {
                    setType(ThrowType.SPARE);
                } else {
                    setType(ThrowType.NORMAL);
                    third = 0;
                }
            }
        }
    }

    /**
     * If type strike or spare player can have a last try.
     *
     * @param score of the bonus score.
     * @throws InvalidScoreException if invalid score.
     */
    public final void setThird(final int score) throws InvalidScoreException {
        if (this.getType() == ThrowType.NORMAL) {
            throw new InvalidScoreException("Try not allowed");
        } else if (this.getSecond() < MIN) {
            throw new InvalidScoreException("Second try not done");
        } else if (score < MIN || score > MAX) {
            throw new InvalidScoreException(ERR_VALUE);
        } else {
            third = score;
        }
    }

    /**
     *
     * @return the third try score.
     */
    public final int getThird() {
        return third;
    }
}
