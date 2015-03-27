package dcll.SgadRmal.interfaces;

import dcll.SgadRmal.exceptions.IncorrectValueForTryException;
import dcll.SgadRmal.exceptions.SecondTryNotDoneException;

/**
 * Created by Romain on 26/03/2015.
 */
public interface ILastThrow extends IThrow {

    /**
     * Set the score of the third try of the last throw.
     *
     * @param score Number of pins knocked over
     */
    void setThird(int score) throws SecondTryNotDoneException, IncorrectValueForTryException;

    /**
     * @return Score of the third try
     */
    int getThird();
}
