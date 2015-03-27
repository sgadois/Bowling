package dcll.SgadRmal.implementation;


import dcll.SgadRmal.interfaces.ILastThrow;

/**
 * Created by seb on 19/03/15.
 */
public class LastThrow extends Throw implements ILastThrow {

    private int third;

    public LastThrow() {
        third = -1;
    }

    @Override
    public void setSecond(final int score) {

    }

    @Override
    public void setThird(final int score) {

    }

    @Override
    public final int getThird() {
        return 0;
    }
}
