package dcll.bowling2;

import dcll.ftsq.*;
import dcll.ftsq.ranking.Player;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by seb on 28/04/15.
 */
public class RankingTest {

    @Test
    public void testRanking() throws Exception {
        Game perfectGame = new Game(new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new StrikeFrame(),
                new StrikeFrame(), new LastStrikeFrame(10, 10));
        Game badGame = new Game(new OpenFrame(0, 0), new OpenFrame(0, 0),
                new OpenFrame(0, 0), new OpenFrame(0, 0),
                new OpenFrame(0, 0), new OpenFrame(0, 0),
                new OpenFrame(0, 0), new OpenFrame(0, 0),
                new OpenFrame(0, 0), new OpenFrame(0, 0));
        Game mildGame = new Game(new StrikeFrame(), new SpareFrame(5),
                new OpenFrame(2, 3), new OpenFrame(4, 5),
                new StrikeFrame(), new OpenFrame(2, 3),
                new SpareFrame(3), new OpenFrame(2, 5),
                new StrikeFrame(), new LastSpareFrame(2, 3));
        Player robert = new Player("Robert", perfectGame);
        Player roger = new Player("Roger", mildGame);
        Player gerard = new Player("Gerard", badGame);

        Player[] correctRanking = {robert,roger,gerard};
        Ranking ranking = new Ranking();

        assertEquals(correctRanking, ranking.rank(new Player[]{roger, gerard, robert}));

    }
}
