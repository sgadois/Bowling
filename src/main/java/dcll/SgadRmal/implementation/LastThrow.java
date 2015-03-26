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
    public void setSecond(int score) {

    }

    @Override
    public void setThird(int score) {

    }

    @Override
    public int getThird() {
        return 0;
    }
}
