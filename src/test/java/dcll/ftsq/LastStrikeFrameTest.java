package dcll.ftsq;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by emartin on 06/04/15.
 */
public class LastStrikeFrameTest {
    private Frame frame, frame1;
    private Game game;
    private static final int VALID_BONUS_1 = 9;
    private static final int VALID_BONUS_2 = 10;

    @Before
    public void setUp() throws Exception {
        frame = new LastStrikeFrame(VALID_BONUS_1, VALID_BONUS_2);
        frame1 = new LastStrikeFrame(VALID_BONUS_1, VALID_BONUS_2);
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
        assertEquals(1, frame.getBalls().size());
        Assert.assertEquals(Constants.NUMBER_OF_PINS, frame.getBalls().get(0)
                .getDownPins());
    }

    @Test
    public void testGetBonusBalls() {
        assertEquals(2, frame.getBonusBalls().size());
        assertEquals(VALID_BONUS_1, frame.getBonusBalls().get(0).getDownPins());
        assertEquals(VALID_BONUS_2, frame.getBonusBalls().get(1).getDownPins());
    }

    @Test(expected = BallInvalidException.class)
    public void testInvalidFrame() throws Exception {
        new LastStrikeFrame(Constants.NUMBER_OF_PINS+1, Constants.NUMBER_OF_PINS);
    }
}
