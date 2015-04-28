package dcll.ftsq;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by emartin on 05/04/15.
 */
public class LastStrikeFrame implements LastFrame {
    /**
     * A reference to the game that owns this frame.
     */
    private Game parentGame;

    /**
     * The list of balls for this frame (1 element).
     */
    private List<Ball> balls;

    /**
     * The list of bonus balls for this frame (2 elements).
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
     * @return The list of balls for this frame (1 element).
     */
    public final List<Ball> getBalls() {
        return Collections.unmodifiableList(balls);
    }

    /**
     * @return The list of bonus balls for this frame (2 elements).
     */
    public final List<Ball> getBonusBalls() {
        return Collections.unmodifiableList(bonusBalls);
    }

    /**
     * @param fstBonus The number of pins down for the first extra bonus ball.
     * @param sndBonus The number of pins down for the second extra bonus ball.
     * @throws BallInvalidException if the numbers are invalid.
     */
    public LastStrikeFrame(final int fstBonus, final int sndBonus)
            throws BallInvalidException {
        balls = new ArrayList<Ball>(1);
        balls.add(new Ball(Constants.NUMBER_OF_PINS, Constants.NUMBER_OF_PINS));

        bonusBalls = new ArrayList<Ball>(2);
        bonusBalls.add(new Ball(fstBonus, Constants.NUMBER_OF_PINS));
        bonusBalls.add(new Ball(sndBonus, Constants.NUMBER_OF_PINS));
    }
}
