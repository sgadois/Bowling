package dcll.ftsq;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by emartin on 05/04/15.
 */
public class LastSpareFrame implements LastFrame {

    /**
     * A reference to the game that owns this frame.
     */
    private Game parentGame;

    /**
     * The list of balls for this frame (2 elements).
     */
    private List<Ball> balls;

    /**
     * The list of bonus balls for this frame (1 element).
     */
    private List<Ball> bonusBalls;

    /**
     * @return A reference to the game that owns this frame.
     */
    public final Game getParentGame() {
        return parentGame;
    }

    /**
     * Set the Game that owns this Frame.
     * This function can be called only once (otherwise it throws an exception).
     * This function is especially used in the Game class.
     * @param game the Game that owns this Frame. Must be non-null
     * @throws GameInvalidException if it's not the 1st call, or if game is null
     */
    public final void setParentGame(final Game game)
            throws GameInvalidException {
        if (parentGame != null) {
            throw new GameInvalidException("setParentGame() "
                      + "can be called only once.");
        }
        if (game == null) {
            throw new GameInvalidException("setParentGame() "
                      + "expects a non-null argument.");
        }
            parentGame = game;
    }

    /**
     * @return The list of balls for this frame (2 elements).
     */
    public final List<Ball> getBalls() {
        return Collections.unmodifiableList(balls);
    }

    /**
     * @return The list of bonus balls for this frame (1 element).
     */
    public final List<Ball> getBonusBalls() {
        return Collections.unmodifiableList(bonusBalls);
    }

    /**
     * @param fstBall The number of pins down for the first ball.
     * @param fstBonus The number of pins down for the extra bonus ball.
     * @throws BallInvalidException if the numbers are invalid.
     */
    public LastSpareFrame(final int fstBall, final int fstBonus)
            throws BallInvalidException {
        balls = new ArrayList<Ball>(2);
        balls.add(new Ball(fstBall, Constants.NUMBER_OF_PINS - 1));
        final int sndBall = Constants.NUMBER_OF_PINS - fstBall;
        balls.add(new Ball(sndBall, sndBall));

        bonusBalls = new ArrayList<Ball>(1);
        bonusBalls.add(new Ball(fstBonus, Constants.NUMBER_OF_PINS));
    }
}
