package dcll.ftsq.ranking;

import dcll.ftsq.Game;

/**
 * Created by emartin on 06/04/15.
 */
public class Player {
    /**
     * The player's game.
     */
    private Game game;

    /**
     * @return the player's game
     */
    public final Game getGame() {
        return game;
    }

    /**
     * The player's name.
     */
    private String name;

    /**
     * @return the player's name
     */
    public final String getName() {
        return name;
    }

    /**
     * @param pName the player's name
     * @param pGame the player's game
     */
    public Player(final String pName, final Game pGame) {
        name = pName;
        game = pGame;
    }
}
