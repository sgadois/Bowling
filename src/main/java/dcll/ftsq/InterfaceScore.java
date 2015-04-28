package dcll.ftsq;

/**
 * Created by emartin on 06/04/15.
 */
public interface InterfaceScore {

    /**
     * Calculate the score for a given bowling Game.
     * @param game the game
     * @return the score
     */
    int calcScore(Game game);
}
