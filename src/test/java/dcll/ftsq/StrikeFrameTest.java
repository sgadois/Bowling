package dcll.ftsq;

import org.junit.Before;
import org.junit.Test;

import static dcll.ftsq.Constants.NUMBER_OF_PINS;
import static org.junit.Assert.*;

/**
 * Created by emartin on 06/04/15.
 */
public class StrikeFrameTest {
    private Frame frame, frame1;
    private Game game;

    private static final int BONUS_1 = NUMBER_OF_PINS; // Strike
    private static final int BONUS_2 = 2;

    private static final int ALT_BONUS_1 = 1;
    private static final int ALT_BONUS_2 = NUMBER_OF_PINS - ALT_BONUS_1; // Spare

    @Before
    public void setUp() throws BallInvalidException, GameInvalidException {
        frame = new StrikeFrame();
        frame1 = new StrikeFrame();
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
        assertEquals(1, frame.getBalls().size());
        assertEquals(NUMBER_OF_PINS, frame.getBalls().get(0).getDownPins());
    }

    @Test
    public void testGetBonusBalls_StrikeOpen() throws Exception {
        Frame ref = new StrikeFrame();
        Game g = new Game(new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), ref,
                new StrikeFrame(), new OpenFrame(BONUS_2, 0));
        assertEquals(2, ref.getBonusBalls().size());
        assertEquals(BONUS_1, ref.getBonusBalls().get(0).getDownPins());
        assertEquals(BONUS_2, ref.getBonusBalls().get(1).getDownPins());
    }

    @Test
    public void testGetBonusBalls_Spare() throws Exception {
        Frame ref = new StrikeFrame();
        Game g = new Game(new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), ref,
                new SpareFrame(ALT_BONUS_1), new OpenFrame(0, 0));
        assertEquals(2, ref.getBonusBalls().size());
        assertEquals(ALT_BONUS_1, ref.getBonusBalls().get(0).getDownPins());
        assertEquals(ALT_BONUS_2, ref.getBonusBalls().get(1).getDownPins());
    }
}
