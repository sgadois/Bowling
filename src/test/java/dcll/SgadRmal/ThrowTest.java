package dcll.SgadRmal;

import dcll.SgadRmal.exceptions.InvalidScoreException;
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
    public void testSetFirst() throws InvalidScoreException {

        // Setup
        final int score = 5;

        // Code under test
        lancer.setFirst(score);

        // Assertions
        Assert.assertEquals(score, lancer.getFirst());
    }

    @Test
    public void testSetSecond() throws InvalidScoreException {

        // Setup
        final int score = 5;
        lancer.setFirst(4);

        // Code under test
        lancer.setSecond(score);

        // Assertions
        Assert.assertEquals(score, lancer.getSecond());
    }

    @Test
    public void testStrike() throws InvalidScoreException {

        // Setup
        final int score = 10;

        // Code under test
        lancer.setFirst(score);

        // Assertions
        Assert.assertEquals(score, lancer.getFirst());
        Assert.assertEquals(ThrowType.STRIKE, lancer.getType());
        Assert.assertEquals(-1, lancer.getSecond());
    }

    @Test
    public void testSpare() throws InvalidScoreException {

        // Setup
        final int score = 2;
        lancer.setFirst(8);

        // Code under test
        lancer.setSecond(score);

        // Assertions
        Assert.assertEquals(score, lancer.getSecond());
        Assert.assertEquals(ThrowType.SPARE, lancer.getType());
        Assert.assertEquals(10, lancer.getFirst() + lancer.getSecond());
    }

    @Test
    public void testNormal() throws InvalidScoreException {

        // Setup
        boolean normalThrow = false;
        final int score = 1;
        lancer.setFirst(3);

        // Code under test
        lancer.setSecond(score);

        // Assertions
        Assert.assertEquals(score, lancer.getSecond());
        Assert.assertEquals(ThrowType.NORMAL, lancer.getType());
        if (lancer.getFirst() + lancer.getSecond() < 10) {
            normalThrow = true;
        }
        Assert.assertTrue(normalThrow);
    }

    @Test
    public void testTypeNotSetYet() throws InvalidScoreException {

        // Setup
        final int score = 5;

        // Code under test
        lancer.setFirst(score);

        // Assertions
        Assert.assertEquals(score, lancer.getFirst());
        Assert.assertNull(lancer.getType());
    }

    @Test(expected = InvalidScoreException.class)
    public void testSetSecondBeforeFirst() throws InvalidScoreException {

        // Setup
        final int score = 5;

        // Code under test
        lancer.setSecond(score);
    }

    @Test(expected = InvalidScoreException.class)
    public void testSetIncorrectPositiveValueFirstTry() throws InvalidScoreException {

        // Setup
        final int score = 11;

        // Code under test
        lancer.setFirst(score);
    }

    @Test(expected = InvalidScoreException.class)
    public void testSetIncorrectNegativeValueFirstTry() throws InvalidScoreException {

        // Setup
        final int score = -1;

        // Code under test
        lancer.setFirst(score);
    }

    @Test(expected = InvalidScoreException.class)
    public void testSetIncorrectPositiveValueSecondTry() throws InvalidScoreException {

        // Setup
        final int score = 11;
        lancer.setFirst(6);

        // Code under test
        lancer.setSecond(score);
    }

    @Test(expected = InvalidScoreException.class)
    public void testSetIncorrectNegativeFirstTry() throws InvalidScoreException {

        // Setup
        final int score = -1;
        lancer.setFirst(6);

        // Code under test
        lancer.setSecond(score);
    }

    @Test(expected = InvalidScoreException.class)
    public void testSetIncorrectValueAfterStrike() throws InvalidScoreException {

        // Setup
        final int score = 1;
        lancer.setFirst(10);

        // Code under test
        lancer.setSecond(score);
    }

    @Test(expected = InvalidScoreException.class)
    public void testSetTooMuchPinsKnockDown() throws InvalidScoreException {

        // Setup
        final int score = 6;
        lancer.setFirst(5);

        // Code under test
        lancer.setSecond(score);
    }
}
