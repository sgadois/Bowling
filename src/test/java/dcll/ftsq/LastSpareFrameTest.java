package dcll.ftsq;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by emartin on 06/04/15.
 */
public class LastSpareFrameTest {

    private Frame frame, frame1;
    private static final int VALID_BALL_1 = 9;
    private static final int VALID_BONUS_1 = 10;
    private Game game;

    @Before
    public void setUp() throws Exception {
        frame = new LastSpareFrame(VALID_BALL_1, VALID_BONUS_1);
        frame1 = new LastSpareFrame(VALID_BALL_1, VALID_BONUS_1);
        game = new Game(new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), frame1);
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
    public void testGetBonusBalls() {
        assertEquals(1, frame.getBonusBalls().size());
        assertEquals(VALID_BONUS_1, frame.getBonusBalls().get(0).getDownPins());
    }

    @Test(expected = BallInvalidException.class)
    public void testInvalidFrame() throws Exception {
        new LastSpareFrame(Constants.NUMBER_OF_PINS, VALID_BONUS_1);
    }
}
