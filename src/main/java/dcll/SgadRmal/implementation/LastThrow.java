package dcll.SgadRmal.implementation;


import dcll.SgadRmal.exceptions.FirstTryNotDoneException;
import dcll.SgadRmal.exceptions.IncorrectValueForTryException;
import dcll.SgadRmal.exceptions.SecondTryNotDoneException;
import dcll.SgadRmal.interfaces.ILastThrow;

/**
 * Created by seb on 19/03/15.
 */
public class LastThrow extends Throw implements ILastThrow {

    private int third;
    private boolean allowThird;

    public LastThrow() {
        super();
        third = -1;
        allowThird = false;
    }

    @Override
    public void setSecond(final int score) throws IncorrectValueForTryException, FirstTryNotDoneException {
        if (first < MIN) {
            throw new FirstTryNotDoneException("First try not scored");
        }
        else if ((type != ThrowType.STRIKE && first + score > MAX) || score < MIN || score > MAX) {
            throw new IncorrectValueForTryException("Incorrect score");
        }
        else {
            second = score;
            if (first + second == MAX || type == ThrowType.STRIKE) {
                allowThird = true;
                if (type == null) {
                    type = ThrowType.SPARE;
                }
            }
            else {
                type = ThrowType.NORMAL;
            }
        }
    }

    @Override
    public void setThird(final int score) throws SecondTryNotDoneException, IncorrectValueForTryException {
        if (allowThird) {
            if (second < MIN) {
                throw new SecondTryNotDoneException("Second try not scored");
            }
            else if (score < MIN || score > MAX) {
                throw new IncorrectValueForTryException("Incorrect score");
            }
            else {
                third = score;
            }
        }
    }

    @Override
    public final int getThird() {
        return third;
    }
}
