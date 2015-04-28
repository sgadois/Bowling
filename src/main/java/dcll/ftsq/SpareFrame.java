package dcll.ftsq;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by emartin on 05/04/15.
 */
public class SpareFrame implements BasicFrame {

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
     * @return The list of bonus balls for this frame (1 element).
     */
    public final List<Ball> getBonusBalls() {
        final List<Frame> frames = getParentGame().getFrames();
        final Frame nextFrame = frames.get(frames.indexOf(this) + 1);
        return Collections.singletonList(nextFrame.getBalls().get(0));
    }

    /**
     * @param fstBall The number of pins down for the first ball.
     * @throws BallInvalidException if the numbers are invalid.
     */
    public SpareFrame(final int fstBall) throws BallInvalidException {
        balls = new ArrayList<Ball>(2);
        balls.add(new Ball(fstBall, Constants.NUMBER_OF_PINS - 1));
        final int sndBall = Constants.NUMBER_OF_PINS - fstBall;
        balls.add(new Ball(sndBall, sndBall));
    }
}
