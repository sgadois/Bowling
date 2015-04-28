package dcll.ftsq.ranking;

/**
 * Created by emartin on 06/04/15.
 */
public interface InterfaceRanking {
    /**
     * Rank the players from their bowling score.
     * Example: if the score of Alice > the score of Bob,
     *   then this method should return {Alice, Bob}
     *   where {...} is a Player[] array.
     * @param array the input array of players
     * @return the array of players after ranking
     */
    Player[] rank(Player... array);
}
