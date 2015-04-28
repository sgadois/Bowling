package dcll.bowling2;

import dcll.ftsq.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by seb on 28/04/15.
 */
public class ScoringTest {

    @Test
    public void testPerfectScore() throws Exception {
        Game game = new Game(new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new LastStrikeFrame(10,10));
        Scoring scoring = new Scoring();

        assertEquals(300, scoring.calcScore(game));
    }

    @Test
    public void testWorstScore() throws Exception {
        Game game = new Game(new OpenFrame(0,0), new OpenFrame(0,0),
                new OpenFrame(0,0), new OpenFrame(0,0),
                new OpenFrame(0,0), new OpenFrame(0,0),
                new OpenFrame(0,0), new OpenFrame(0,0),
                new OpenFrame(0,0), new OpenFrame(0,0));
        Scoring scoring = new Scoring();

        assertEquals(0, scoring.calcScore(game));
    }

    @Test
    public void testMildScore() throws Exception {
        Game game = new Game(new StrikeFrame(), new SpareFrame(5),
                new OpenFrame(2,3), new OpenFrame(4,5),
                new StrikeFrame(), new OpenFrame(2,3),
                new SpareFrame(3), new OpenFrame(2,5),
                new StrikeFrame(), new LastSpareFrame(2,3));
        Scoring scoring = new Scoring();

        assertEquals(118, scoring.calcScore(game));
    }
}
