package dcll.ftsq;

/**
 * Created by emartin on 05/04/15.
 */
public class GameInvalidException extends Exception {
    /**
     * Exception corresponding to an invalid call to Game's constructor.
     * @param message the exception message.
     */
    public GameInvalidException(final String message) {
        super(message);
    }
}
