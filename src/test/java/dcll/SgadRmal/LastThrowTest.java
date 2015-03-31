package dcll.SgadRmal;

import dcll.SgadRmal.exceptions.InvalidScoreException;
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
    public void testSetSecond() throws InvalidScoreException {

        // Setup
        final int score = 5;
        lastThrow.setFirst(2);

        // Code under test
        lastThrow.setSecond(score);

        // Assertions
        Assert.assertEquals(2, lastThrow.getFirst());
        Assert.assertEquals(score, lastThrow.getSecond());
    }

    @Test
    public void testSetThird() throws InvalidScoreException {

        // Setup
        final int score = 5;
        lastThrow.setFirst(2);
        lastThrow.setSecond(8);

        // Code under test
        lastThrow.setThird(score);

        // Assertions
        Assert.assertEquals(2, lastThrow.getFirst());
        Assert.assertEquals(8, lastThrow.getSecond());
        Assert.assertEquals(score, lastThrow.getThird());
        Assert.assertEquals(ThrowType.SPARE, lastThrow.getType());
    }


    @Test
    public void testCreateLastThrow() {

        // Assertions
        Assert.assertEquals("The value of the first try must be -1", -1, lastThrow.getFirst());
        Assert.assertEquals("The value of the second try must be -1", -1, lastThrow.getSecond());
        Assert.assertEquals("The value of the third try must be -1", -1, lastThrow.getThird());
    }

    @Test
    public void testStrike() throws InvalidScoreException {

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
    public void testSpare() throws InvalidScoreException {

        // Setup
        final int score = 5;
        lastThrow.setFirst(5);

        // Code under test
        lastThrow.setSecond(score);

        // Assertions
        Assert.assertEquals(score, lastThrow.getSecond());
        Assert.assertEquals(-1, lastThrow.getThird());
        Assert.assertEquals(ThrowType.SPARE, lastThrow.getType());
    }

    @Test
    public void testNormal() throws InvalidScoreException {

        // Setup
        final int score = 2;
        lastThrow.setFirst(1);

        // Code under test
        lastThrow.setSecond(score);

        // Assertions
        Assert.assertEquals(score, lastThrow.getSecond());
        Assert.assertEquals(0, lastThrow.getThird());
        Assert.assertEquals(ThrowType.NORMAL, lastThrow.getType());
    }

    @Test(expected = InvalidScoreException.class)
    public void testSetIncorrectValueSecondTry() throws InvalidScoreException {

        // Setup
        final int score = 51;
        lastThrow.setFirst(1);

        // Code under test
        lastThrow.setSecond(score);
    }

    @Test(expected = InvalidScoreException.class)
    public void testSetIncorrectValueThirdTry() throws InvalidScoreException {

        // Setup
        final int score = 27;
        lastThrow.setFirst(10);
        lastThrow.setSecond(5);

        // Code under test
        lastThrow.setThird(score);
    }

    @Test(expected = InvalidScoreException.class)
    public void testSetSecondBeforeFirst() throws InvalidScoreException {

        // Setup
        final int score = 5;

        // Code under test
        lastThrow.setSecond(score);
    }

    @Test(expected = InvalidScoreException.class)
    public void testSetThirdBeforeSecond() throws InvalidScoreException {

        // Setup
        final int score = 7;

        // Code under test
        lastThrow.setThird(score);
    }

    @Test(expected = InvalidScoreException.class)
    public void testSetThirdTryInNormalThrow() throws InvalidScoreException {

        // Setup
        final int score = 6;
        lastThrow.setFirst(1);
        lastThrow.setSecond(2);

        // Code under
        lastThrow.setThird(score);
    }

}
