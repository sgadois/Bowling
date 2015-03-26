package dcll.SgadRmal;

import dcll.SgadRmal.exceptions.FirstTryNotDoneException;
import dcll.SgadRmal.exceptions.IncorrectValueForTryException;
import dcll.SgadRmal.implementation.Throw;
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
    }

    @Test
    public void testSetFirst() {

        // Setup
        final int score = 5;

        // Code under test
        lancer.setFirst(score);

        // Assertions
        Assert.assertEquals(score, lancer.getFirst());
    }

    @Test
    public void testSetSecond() {

        // Setup
        final int score = 5;
        lancer.setFirst(4);

        // Code under test
        lancer.setSecond(score);

        // Assertions
        Assert.assertEquals(score, lancer.getSecond());
    }

    @Test(expected = FirstTryNotDoneException.class)
    public void testSetSecondBeforeFirst() {

        // Setup
        final int score = 5;

        // Code under test
        lancer.setSecond(score);
    }

    @Test(expected = IncorrectValueForTryException.class)
    public void testSetIncorrectValueFirstTry() {

        // Code under test
        lancer.setFirst(11);
    }

    @Test(expected = IncorrectValueForTryException.class)
    public void testSetIncorrectValueAfterStrike() {

        // Setup
        lancer.setFirst(10);

        // Code under test
        lancer.setSecond(1);
    }

    @Test(expected = IncorrectValueForTryException.class)
    public void testSetTooMuchPinsKnockDown() {

        // Setup
        lancer.setFirst(5);

        // Code under test
        lancer.setSecond(6);
    }
}
