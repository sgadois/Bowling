package dcll.ftsq;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by emartin on 05/04/15.
 *
 * Any instance of this class is a valid Bowling game and is composed with:
 *   (NUMBER_OF_FRAMES - 1) instances of BasicFrame,
 *   then 1 instance of LastFrame.
 */
public class Game {

    /**
     * The list of frames for this game.
     */
    private List<Frame> frames;

    /**
     * @return The list of frames for this game.
     */
    public final List<Frame> getFrames() {
        return Collections.unmodifiableList(frames);
    }

    /**
     * Crate a Game with Constants.NUMBER_OF_FRAMES frames.
     * @param array a NUMBER_OF_FRAMES-sized array, the last element must be a
     *              LastFrame and the other ones must be some BasicFrame's.
     *              All elements must be pairwise-different references.
     * @throws GameInvalidException if array is invalid.
     */
    public Game(final Frame... array) throws GameInvalidException {
        frames = new ArrayList<Frame>();
        if (array.length != Constants.NUMBER_OF_FRAMES) {
            throw new GameInvalidException("Wrong number of frames.");
        }
        for (int i = 0; i <= Constants.NUMBER_OF_FRAMES - 2; i++) {
            if (!(array[i] instanceof BasicFrame)) {
                throw new GameInvalidException("This Frame should be a "
                        + "BasicFrame.");
            }
            array[i].setParentGame(this);
            // as setParentGame() can only be called once, this ensures that
            // the added frames are pairwise different!
            frames.add(array[i]);
        }
        final int lastIndex = Constants.NUMBER_OF_FRAMES - 1;
        if (!(array[lastIndex] instanceof LastFrame)) {
            throw new GameInvalidException("This Frame should be a LastFrame");
        }
        array[lastIndex].setParentGame(this);
        // as setParentGame() can only be called once, this ensures that
        // the added frames are pairwise different!
        frames.add(array[lastIndex]);
    }
}
