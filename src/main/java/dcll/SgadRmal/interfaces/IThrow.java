package dcll.SgadRmal.interfaces;

/**
 * Created by seb on 19/03/15.
 */
public interface IThrow {
    /**
     * Set the score of the first try of the throw.
     *
     * @param score Number of pins knocked over
     */
    void setFirst(int score);

    /**
     * Set the score of the second try of the throw.
     *
     * @param score Number of pins knocked over
     */
    void setSecond(int score);

    /**
     * @return Score of the first try
     */
    int getFirst();

    /**
     * @return Score of the second try
     */
    int getSecond();
}
