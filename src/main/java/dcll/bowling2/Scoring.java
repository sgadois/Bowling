package dcll.bowling2;

import dcll.ftsq.*;

import java.util.List;

/**
 * Created by seb on 28/04/15.
 */
public class Scoring implements InterfaceScore{
    @Override
    public int calcScore(Game game) {
        int score = 0;
        List<Frame> frameList = game.getFrames();
        for (Frame frame : frameList) {
            for (Ball ball : frame.getBalls()) {
                score += ball.getDownPins();
            }
            if (frame instanceof StrikeFrame || frame instanceof LastSpareFrame || frame instanceof LastStrikeFrame) {
                for (Ball ball : frame.getBalls()) {
                    score += ball.getDownPins();
                }
            }
        }
        return score;
    }
}
