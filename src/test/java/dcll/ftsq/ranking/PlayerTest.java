package dcll.ftsq.ranking;

import dcll.ftsq.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

/**
 * Created by emartin on 08/04/15.
 */
public class PlayerTest {

    private static final String VALID_NAME = "Alice";
    private static final Game VALID_GAME = null;
    private Player player;

    @Before
    public void setUp() {
        player = new Player(VALID_NAME, VALID_GAME);
    }

    @Test
    public void testGetGame() throws Exception {
        assertSame(VALID_GAME, player.getGame());
    }

    @Test
    public void testGetName() throws Exception {
        assertEquals(VALID_NAME, player.getName());
    }
}
