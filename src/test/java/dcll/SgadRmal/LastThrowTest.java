package dcll.SgadRmal;

import dcll.SgadRmal.exceptions.FirstTryNotDoneException;
import dcll.SgadRmal.exceptions.IncorrectValueForTryException;
import dcll.SgadRmal.exceptions.SecondTryNotDoneException;
import dcll.SgadRmal.implementation.LastThrow;
import dcll.SgadRmal.implementation.ThrowType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by seb on 26/03/15.
 */
public class LastThrowTest {

    private LastThrow lastThrow;

    @Before
    public void setUp() {
        lastThrow = new LastThrow();
    }


    @Test
    public void testCreateLastThrow() {

        // Assertions
        Assert.assertEquals("The value of the first try must be -1", -1, lastThrow.getFirst());
        Assert.assertEquals("The value of the second try must be -1", -1, lastThrow.getSecond());
        Assert.assertEquals("The value of the third try must be -1", -1, lastThrow.getThird());
    }

    @Test
    public void testStrike() throws IncorrectValueForTryException {

        // Setup
        final int score = 10;

        // Code under tests
        lastThrow.setFirst(score);

        // Assertions
        Assert.assertEquals(score, lastThrow.getFirst());
        Assert.assertEquals(-1, lastThrow.getSecond());
        Assert.assertEquals(-1, lastThrow.getThird());
        Assert.assertEquals(ThrowType.STRIKE, lastThrow.getType());
    }

    @Test
    public void testSpare() throws IncorrectValueForTryException, FirstTryNotDoneException {

        // Setup
        final int score = 5;
        lastThrow.setFirst(5);

        // Code under test
        lastThrow.setSecond(score);

        // Assertions
        Assert.assertEquals(score, lastThrow.getSecond());
        Assert.assertEquals(-1, lastThrow.getThird());
        Assert.assertEquals(ThrowType.SPARE, lastThrow.getThird());
    }

    @Test
    public void testNormal() throws IncorrectValueForTryException, FirstTryNotDoneException {

        // Setup
        final int score = 2;
        lastThrow.setFirst(1);

        // Code under test
        lastThrow.setSecond(score);

        // Assertions
        Assert.assertEquals(score, lastThrow.getSecond());
        Assert.assertEquals(0, lastThrow.getThird());
        Assert.assertEquals(ThrowType.NORMAL, lastThrow.getThird());
    }

    @Test(expected = IncorrectValueForTryException.class)
    public void testSetIncorrectValueSecondTry() throws IncorrectValueForTryException, FirstTryNotDoneException {

        // Setup
        final int score = 51;
        lastThrow.setFirst(1);

        // Code under test
        lastThrow.setSecond(score);
    }

    @Test(expected = IncorrectValueForTryException.class)
    public void testSetIncorrectValueThirdTry() throws IncorrectValueForTryException, FirstTryNotDoneException, SecondTryNotDoneException {

        // Setup
        final int score = 27;
        lastThrow.setFirst(10);
        lastThrow.setSecond(5);

        // Code under test
        lastThrow.setThird(score);
    }

    @Test(expected = SecondTryNotDoneException.class)
    public void testSetThirdBeforeSecond() throws IncorrectValueForTryException, SecondTryNotDoneException {

        // Setup
        final int score = 7;

        // Code under test
        lastThrow.setThird(score);
    }

    @Test(expected = IncorrectValueForTryException.class)
    public void testSetThirdTryInNormalThrow() throws IncorrectValueForTryException, FirstTryNotDoneException, SecondTryNotDoneException {

        // Setup
        final int score = 6;
        lastThrow.setFirst(1);
        lastThrow.setSecond(2);

        // Code under
        lastThrow.setThird(score);
    }

}
