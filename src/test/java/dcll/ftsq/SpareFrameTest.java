package dcll.ftsq;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by emartin on 06/04/15.
 */
public class SpareFrameTest {
    private Frame frame, frame1;
    private Game game;
    private static final int VALID_BALL_1 = 1;
    private static final int NEXT = 2;

    @Before
    public void setUp() throws BallInvalidException, GameInvalidException {
        frame = new SpareFrame(VALID_BALL_1);
        frame1 = new SpareFrame(VALID_BALL_1);
        game = new Game(frame1, new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new OpenFrame(0, 0));
    }

    @Test(expected = GameInvalidException.class)
    public void testSetParentGameInvalidNull() throws GameInvalidException {
        frame.setParentGame(null);
    }

    @Test(expected = GameInvalidException.class)
    public void testSetParentGameInvalidAgain() throws GameInvalidException {
        frame1.setParentGame(game);
    }

    @Test
    public void testGetParentGame() {
        assertSame(game, frame1.getParentGame());
    }

    @Test
    public void testGetBalls() {
        assertEquals(2, frame.getBalls().size());
        assertEquals(VALID_BALL_1, frame.getBalls().get(0).getDownPins());
        Assert.assertEquals(Constants.NUMBER_OF_PINS - VALID_BALL_1, frame
                .getBalls().get(1).getDownPins());
    }

    @Test
    public void testGetBonusBalls() throws Exception {
        Frame ref = new SpareFrame(VALID_BALL_1);
        Game g = new Game(new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), ref,
                new StrikeFrame(), new OpenFrame(NEXT, 0));
        assertEquals(1, ref.getBonusBalls().size());
        Assert.assertEquals(Constants.NUMBER_OF_PINS, ref.getBonusBalls().get(0).getDownPins());
    }
}
