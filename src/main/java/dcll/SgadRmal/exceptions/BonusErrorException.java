package dcll.SgadRmal.exceptions;

/**
 * Created by Romain on 25/03/2015.
 */
public class BonusErrorException extends Exception {

    /**
     * Instance of BonusErrorMessage
     *
     * @param message associated with the error
     */
    public BonusErrorException(final String message) {
        super(message);
    }
}
