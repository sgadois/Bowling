package dcll.ftsq;

import java.util.List;

/**
 * Created by emartin on 05/04/15.
 */
public interface Frame {
    /**
     * This function is mostly useful in classes SpareFrame and StrikeFrame.
     * @return a reference to the parent Game.
     */
    Game getParentGame();

    /**
     * Set the Game that owns this Frame.
     * This function can be called only once (otherwise it throws an exception).
     * This function is especially used in the Game class.
     * @param game the Game that owns this Frame. Must be non-null
     * @throws GameInvalidException if it's not the 1st call, or if game is null
     */
    void setParentGame(final Game game) throws GameInvalidException;

    /**
     * @return The list of balls for this frame (1 or 2 elements).
     */
    List<Ball> getBalls();

    /**
     * @return The list of bonus balls for this frame. This list can be null, or
     * contain 1 element (for LastSpareFrame) or 2 (for LastStrikeFrame).
     */
    List<Ball> getBonusBalls();
}
