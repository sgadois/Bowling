package dcll.SgadRmal.exceptions;

/**
 * Created by Romain on 25/03/2015.
 */
public class LastThrowErrorException extends Exception {

    /**
     * Instance of BonusErrorMessage
     *
     * @param message associated with the error
     */
    public LastThrowErrorException(final String message) {
        super(message);
    }
}
