package dcll.SgadRmal.interfaces;

import dcll.SgadRmal.exceptions.InvalidScoreException;
import dcll.SgadRmal.implementation.ThrowType;

/**
 * Created by seb on 19/03/15.
 */
public interface IThrow {
    /**
     * Set the score of the first try of the throw.
     *
     * @param score Number of pins knocked over
     */
    void setFirst(int score) throws InvalidScoreException;

    /**
     * Set the score of the second try of the throw.
     *
     * @param score Number of pins knocked over
     */
    void setSecond(int score) throws InvalidScoreException;

    /**
     * @return Score of the first try
     */
    int getFirst();

    /**
     * @return Score of the second try
     */
    int getSecond();

    /**
     * @return Type of the throw
     */
    ThrowType getType();
}
