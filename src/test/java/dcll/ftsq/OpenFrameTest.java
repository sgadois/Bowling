package dcll.ftsq;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by emartin on 06/04/15.
 */
public class OpenFrameTest {

    private Frame frame, frame1;
    private Game game;
    private static final int VALID_BALL_1 = 0;
    private static final int VALID_BALL_2 = 9;

    @Before
    public void setUp() throws Exception {
        frame = new OpenFrame(VALID_BALL_1, VALID_BALL_2);
        frame1 = new OpenFrame(VALID_BALL_1, VALID_BALL_2);
        game = new Game(new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), frame1);
                // To Do: another test with frame1 at the beginning...
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
        assertEquals(VALID_BALL_2, frame.getBalls().get(1).getDownPins());
    }

    @Test
    public void testGetBonusBalls() {
        assertNull(frame.getBonusBalls());
    }

    @Test(expected = BallInvalidException.class)
    public void testInvalidFrame() throws Exception {
        new OpenFrame(1, 9);
    }
}