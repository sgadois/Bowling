package dcll.ftsq;

import org.junit.Test;

/**
 * Created by emartin on 06/04/15.
 */
public class BallTest {

    @Test(expected = BallInvalidException.class)
    public void testNegative() throws Exception {
       new Ball(-1, 10);
    }
}