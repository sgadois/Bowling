package dcll.bowling2;

import dcll.ftsq.Ball;
import dcll.ftsq.Game;
import dcll.ftsq.InterfaceScore;
import dcll.ftsq.Frame;
import dcll.ftsq.OpenFrame;

/**
 * Created by seb on 28/04/15.
 */
public class Scoring implements InterfaceScore {
    @Override
    public final int calcScore(final Game game) {
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
