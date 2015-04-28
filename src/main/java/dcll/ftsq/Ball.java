package dcll.ftsq;

/**
 * Created by emartin on 05/04/15.
 */
public class Ball {
    /**
     * Number of pins down with the current ball.
     */
    private int downPins;

    /**
     * @return Number of pins down with the current ball.
     */
    public final int getDownPins() {
        return downPins;
    }

    /**
     * This constructor is intentionally not public.
     * @param pDownPins Number of pins down with the current ball
     * @param maxDownPins Maximum value for pDownPins (used for verification).
     * @throws BallInvalidException if the numbers are invalid.
     */
    protected Ball(final int pDownPins, final int maxDownPins)
            throws BallInvalidException {
        if (0 <= pDownPins && pDownPins <= maxDownPins) {
            downPins = pDownPins;
        } else {
            throw new BallInvalidException();
        }
    }
}
