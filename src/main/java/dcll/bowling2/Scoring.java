package dcll.bowling2;

import dcll.ftsq.*;

/**
 * Created by seb on 28/04/15.
 */
public class Scoring implements InterfaceScore{
    @Override
    public int calcScore(Game game) {
        int score = 0;
        for (Frame frame : game.getFrames()) {
            for (Ball ball : frame.getBalls()) {
                score += ball.getDownPins();
            }
            if (!(frame instanceof OpenFrame)) {
                for (Ball ball : frame.getBonusBalls()) {
                    score += ball.getDownPins();
                }
            }
        }
        return score;
    }
}
