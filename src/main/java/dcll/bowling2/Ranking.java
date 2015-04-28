package dcll.bowling2;

import dcll.ftsq.ranking.InterfaceRanking;
import dcll.ftsq.ranking.Player;

/**
 * Created by seb on 28/04/15.
 */
public class Ranking implements InterfaceRanking {
    @Override
    public Player[] rank(Player... array) {
        Scoring scoring = new Scoring();
        Player temp;
        boolean permut = false;

        for (int i = array.length; i > 0; i--) {
            while(permut || i == 0) {
                if (scoring.calcScore(array[i].getGame()) > scoring.calcScore(array[i - 1].getGame())) {
                    temp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = temp;
                    permut = true;
                    i++;
                }
            }
            permut = false;
        }
        return array;
    }
}
