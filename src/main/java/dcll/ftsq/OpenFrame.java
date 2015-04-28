package dcll.ftsq;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by emartin on 05/04/15.
 */
public class OpenFrame implements BasicFrame, LastFrame {

    /**
     * A reference to the game that owns this frame.
     */
    private Game parentGame;

    /**
     * The list of balls for this frame (2 elements).
     */
    private List<Ball> balls;

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
     * @return null
     */
    public final List<Ball> getBonusBalls() {
        return null;
    }

    /**
     * @param fstBall The number of pins down for the first ball.
     * @param sndBall The number of pins down for the second ball.
     * @throws BallInvalidException if the numbers are invalid.
     */
    public OpenFrame(final int fstBall, final int sndBall)
            throws BallInvalidException {
        balls = new ArrayList<Ball>();
        balls.add(new Ball(fstBall, Constants.NUMBER_OF_PINS - 1));
        balls.add(new Ball(sndBall, Constants.NUMBER_OF_PINS - 1 - fstBall));
    }
}
