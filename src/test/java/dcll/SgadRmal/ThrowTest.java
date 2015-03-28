package dcll.SgadRmal;

import dcll.SgadRmal.exceptions.FirstTryNotDoneException;
import dcll.SgadRmal.exceptions.IncorrectValueForTryException;
import dcll.SgadRmal.implementation.Throw;
import dcll.SgadRmal.implementation.ThrowType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by seb on 19/03/15.
 */
public class ThrowTest {

    private Throw lancer;

    @Before
    public void setUp() {
        lancer = new Throw();
    }

    @Test
    public void testCreateThrow() {

        // Assertions
        Assert.assertEquals("The value of first try is -1", -1, lancer.getFirst());
        Assert.assertEquals("The value of second try is -1", -1, lancer.getSecond());
        Assert.assertNull(lancer.getType());
    }

    @Test
    public void testSetFirst() throws IncorrectValueForTryException {

        // Setup
        final int score = 5;

        // Code under test
        lancer.setFirst(score);

        // Assertions
        Assert.assertEquals(score, lancer.getFirst());
    }

    @Test
    public void testSetSecond() throws IncorrectValueForTryException, FirstTryNotDoneException {

        // Setup
        final int score = 5;
        lancer.setFirst(4);

        // Code under test
        lancer.setSecond(score);

        // Assertions
        Assert.assertEquals(score, lancer.getSecond());
    }

    @Test
    public void testStrike() throws IncorrectValueForTryException {

        // Setup
        final int score = 10;

        // Code under test
        lancer.setFirst(score);

        // Assertions
        Assert.assertEquals(score, lancer.getFirst());
        Assert.assertEquals(ThrowType.STRIKE, lancer.getType());
    }

    @Test
    public void testSpare() throws IncorrectValueForTryException, FirstTryNotDoneException {

        // Setup
        final int score = 2;
        lancer.setFirst(8);

        // Code under test
        lancer.setSecond(score);

        // Assertions
        Assert.assertEquals(score, lancer.getSecond());
        Assert.assertEquals(ThrowType.SPARE, lancer.getType());
    }

    @Test
    public void testNormal() throws IncorrectValueForTryException, FirstTryNotDoneException {

        // Setup
        final int score = 1;
        lancer.setFirst(3);

        // Code under test
        lancer.setSecond(score);

        // Assertions
        Assert.assertEquals(score, lancer.getSecond());
        Assert.assertEquals(ThrowType.NORMAL, lancer.getType());
    }

    @Test
    public void testTypeNotSetYet() throws IncorrectValueForTryException {

        // Setup
        final int score = 5;

        // Code under test
        lancer.setFirst(score);

        // Assertions
        Assert.assertEquals(score, lancer.getFirst());
        Assert.assertNull(lancer.getType());
    }

    @Test(expected = FirstTryNotDoneException.class)
    public void testSetSecondBeforeFirst() throws FirstTryNotDoneException, IncorrectValueForTryException {

        // Setup
        final int score = 5;

        // Code under test
        lancer.setSecond(score);
    }

    @Test(expected = IncorrectValueForTryException.class)
    public void testSetIncorrectValueFirstTry() throws IncorrectValueForTryException {

        // Setup
        final int score = 11;

        // Code under test
        lancer.setFirst(score);
    }

    @Test(expected = IncorrectValueForTryException.class)
    public void testSetIncorrectValueAfterStrike() throws IncorrectValueForTryException, FirstTryNotDoneException {

        // Setup
        final int score = 1;
        lancer.setFirst(10);

        // Code under test
        lancer.setSecond(score);
    }

    @Test(expected = IncorrectValueForTryException.class)
    public void testSetTooMuchPinsKnockDown() throws IncorrectValueForTryException, FirstTryNotDoneException {

        // Setup
        final int score = 6;
        lancer.setFirst(5);

        // Code under test
        lancer.setSecond(score);
    }
}
