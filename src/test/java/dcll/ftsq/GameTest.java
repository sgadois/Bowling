package dcll.ftsq;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by emartin on 06/04/15.
 */
public class GameTest {

    // @Before
    // public void setUp() throws Exception {
    //
    // }

    @Test
    public void testInit() throws Exception {
        Game game = new Game(new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new OpenFrame(0, 0));
        assertNotNull(game.getFrames());
    }
    
    @Test(expected = GameInvalidException.class)
    public void testInvalidNumberOfFrames() throws GameInvalidException {
        new Game();
    }

    @Test(expected = GameInvalidException.class)
    public void testInvalidTypeBasicFrame() throws Exception {
        Game game = new Game(new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new LastStrikeFrame(0, 0), new OpenFrame(0, 0));
    }

    @Test(expected = GameInvalidException.class)
    public void testInvalidTypeLastFrame() throws Exception {
        Game game = new Game(new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame());
    }

    @Test(expected = GameInvalidException.class)
    public void testInvalidNotPairwiseDiff1() throws Exception {
        Frame frame = new StrikeFrame();
        Game game = new Game(frame, frame,
        frame, frame,
        frame, frame,
        frame, frame,
        frame, new OpenFrame(0, 0));
    }

    @Test(expected = GameInvalidException.class)
    public void testInvalidNotPairwiseDiff2() throws Exception {
        Frame frame = new OpenFrame(0, 0);
        Game game = new Game(new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                frame, frame);
    }
}
