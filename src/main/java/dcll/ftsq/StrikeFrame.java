package dcll.ftsq;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by emartin on 05/04/15.
 */
public class StrikeFrame implements BasicFrame {

    /**
     * A reference to the game that owns this frame.
     */
    private Game parentGame;

    /**
     * The list of balls for this frame (1 element).
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
     * @return The list of balls for this frame (1 element).
     */
    public final List<Ball> getBalls() {
        return Collections.unmodifiableList(balls);
    }

    /**
     * @return The list of bonus balls for this frame (2 elements).
     */
    public final List<Ball> getBonusBalls() {
        final List<Frame> frames = getParentGame().getFrames();
        final int myIndex = frames.indexOf(this);
        final Frame nextFrame = frames.get(myIndex + 1);
        final List<Ball> nextBalls = nextFrame.getBalls();
        if (nextBalls.size() == 2) {
            return Collections.unmodifiableList(nextBalls);
            // (here, unmodifiableList was not compulsory)
        } else {
            // here, nextBalls.size() == 1;
            // so we know that nextFrame is a StrikeFrame or a LastStrikeFrame
            final List<Ball> nextBonusBalls = nextFrame.getBonusBalls();
            List<Ball> nextBalls2 = new ArrayList<Ball>(nextBalls);
            nextBalls2.add(nextBonusBalls.get(0));
            return Collections.unmodifiableList(nextBalls2);
        }
    }

    /**
     * @throws BallInvalidException (this should never occur in practice)
     */
    public StrikeFrame() throws BallInvalidException {
        balls = new ArrayList<Ball>(1);
        balls.add(new Ball(Constants.NUMBER_OF_PINS, Constants.NUMBER_OF_PINS));
    }
}
