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
        boolean permut = true;
        int j;

        for (int i = array.length - 1; i > 1; i--) {
            j = i;
            while (permut || j == 1) {
                if (scoring.calcScore(array[j].getGame()) > scoring.calcScore(array[j - 1].getGame())) {
                    temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                    permut = false;
                }
                j--;
            }
            permut = true;
        }
        return array;
    }
}
