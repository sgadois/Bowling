package dcll.SgadRmal;

import dcll.SgadRmal.exceptions.InvalidFrameException;
import dcll.SgadRmal.implementation.Frame;
import dcll.SgadRmal.implementation.LastThrow;
import dcll.SgadRmal.implementation.Throw;
import dcll.SgadRmal.implementation.ThrowType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by romain on 19/03/15.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Throw.class, LastThrow.class})
public class FrameTest {

    private Frame jeu;
    private Throw[] listThrow;
    @Mock
    private LastThrow lastLancer;

    @Before
    public void setUp() {
        lastLancer = PowerMockito.mock(LastThrow.class);
        jeu = new Frame();
    }

    /*
     * Testing add throws methods
     *
     * 1. check if throw have a ThrowType
     * 2. check that adding more than 9 Throws is impossible
     * 3. check that a LastThrow isn't added before 9 Throws
     */

    @Test (expected = InvalidFrameException.class)
    public void testAddThrowUndefinedType() throws InvalidFrameException {
        Throw lancer = PowerMockito.mock(Throw.class);
        when(lancer.getType()).thenReturn(null);
        jeu.addThrow(lancer);
    }

    @Test (expected = InvalidFrameException.class)
    public void testAddLastThrowUndefinedType() throws InvalidFrameException {
        for (int i=0; i<9; ++i) {
            Throw unLancer = PowerMockito.mock(Throw.class);
            when(unLancer.getType()).thenReturn(ThrowType.NORMAL);
            jeu.addThrow(unLancer);
        }
        when(lastLancer.getType()).thenReturn(null);
        jeu.addLastThrow(lastLancer);
    }

    @Test (expected = InvalidFrameException.class)
    public void testAddThrowMoreThanNine() throws InvalidFrameException {
        for (int i=0; i<10; ++i) {
            Throw unLancer = PowerMockito.mock(Throw.class);
            when(unLancer.getType()).thenReturn(ThrowType.NORMAL);
            jeu.addThrow(unLancer);
        }
    }

    @Test (expected = InvalidFrameException.class)
    public void testAddLastThrowIllegalFirstThrow() throws InvalidFrameException {
        when(lastLancer.getType()).thenReturn(ThrowType.NORMAL);
        jeu.addLastThrow(lastLancer);
    }

    @Test (expected = InvalidFrameException.class)
    public void testAddLastThrowIllegalEighthThrow() throws InvalidFrameException {
        for (int i=0; i<8; ++i) {
            Throw unLancer = PowerMockito.mock(Throw.class);
            when(unLancer.getType()).thenReturn(ThrowType.NORMAL);
            jeu.addThrow(unLancer);
        }
        jeu.addLastThrow(lastLancer);
    }


    /*
     * Testing compute score method
     *
     * 1. Check that is impossible to compute an incomplete frame
     * 2. Check value of the score returned
     */

    @Test (expected = InvalidFrameException.class)
    public void testComputeScoreWithoutThrow() throws InvalidFrameException {
        int score = jeu.computeScore();
    }

    @Test (expected = InvalidFrameException.class)
    public void testComputeScoreWithoutLastThrow() throws InvalidFrameException {
        for (int i=0; i<9; ++i) {
            Throw unLancer = PowerMockito.mock(Throw.class);
            when(unLancer.getType()).thenReturn(ThrowType.NORMAL);
            jeu.addThrow(unLancer);
        }
        int score = jeu.computeScore();
    }

    @Test
    public void testComputeScoreOnlyNormalThrows() throws InvalidFrameException {
        listThrow = new Throw[9];
        when(lastLancer.getFirst()).thenReturn(4);
        when(lastLancer.getSecond()).thenReturn(4);
        when(lastLancer.getType()).thenReturn(ThrowType.NORMAL);
        int finalScore = 80;
        for (int i=0; i<9; ++i) {
            Throw unLancer = PowerMockito.mock(Throw.class);
            when(unLancer.getFirst()).thenReturn(4);
            when(unLancer.getSecond()).thenReturn(4);
            when(unLancer.getType()).thenReturn(ThrowType.NORMAL);
            jeu.addThrow(unLancer);
            listThrow[i] = unLancer;
        }
        jeu.addLastThrow(lastLancer);
        int testScore = jeu.computeScore();

        for (int i=0; i<9; ++i) {
            verify(listThrow[i]).getFirst();
            verify(listThrow[i]).getSecond();
        }
        verify(lastLancer).getFirst();
        verify(lastLancer).getSecond();
        verify(lastLancer, never()).getThird();
        assertEquals(finalScore, testScore);
    }

    @Test
    public void testComputeScoreOnlyStrike() throws InvalidFrameException {
        listThrow = new Throw[9];
        when(lastLancer.getFirst()).thenReturn(10);
        when(lastLancer.getSecond()).thenReturn(10);
        when(lastLancer.getThird()).thenReturn(10);
        when(lastLancer.getType()).thenReturn(ThrowType.STRIKE);
        int finalScore = 300;
        for (int i=0; i<9; ++i) {
            Throw strike = PowerMockito.mock(Throw.class);
            when(strike.getFirst()).thenReturn(10);
            when(strike.getType()).thenReturn(ThrowType.STRIKE);
            jeu.addThrow(strike);
            listThrow[i] = strike;
        }
        jeu.addLastThrow(lastLancer);
        int testScore = jeu.computeScore();

        verify(listThrow[0]).getFirst();
        verify(listThrow[1], times(2)).getFirst();
        verify(listThrow[2], times(3)).getFirst();
        verify(listThrow[8], times(3)).getFirst();
        verify(lastLancer, times(3)).getFirst();
        verify(lastLancer, times(2)).getSecond();
        verify(lastLancer).getThird();
        assertEquals(finalScore, testScore);
    }

    @Test
    public void testComputeScoreOnlySpare() throws InvalidFrameException {
        listThrow = new Throw[9];
        when(lastLancer.getFirst()).thenReturn(5);
        when(lastLancer.getSecond()).thenReturn(5);
        when(lastLancer.getThird()).thenReturn(5);
        when(lastLancer.getType()).thenReturn(ThrowType.SPARE);
        int finalScore = 150;
        for (int i=0; i<9; ++i) {
            Throw unLancer = PowerMockito.mock(Throw.class);
            when(unLancer.getFirst()).thenReturn(5);
            when(unLancer.getSecond()).thenReturn(5);
            when(unLancer.getType()).thenReturn(ThrowType.SPARE);
            jeu.addThrow(unLancer);
            listThrow[i] = unLancer;
        }
        jeu.addLastThrow(lastLancer);
        int testScore = jeu.computeScore();

        for (int i=0; i<8; ++i) {
            verify(listThrow[i+1], times(2)).getFirst();
        }
        verify(lastLancer, times(2)).getFirst();
        verify(lastLancer).getSecond();
        verify(lastLancer).getThird();
        assertEquals(finalScore, testScore);
    }

    @Test
    public void testComputeScoreStrikeSpare() throws InvalidFrameException {
        when(lastLancer.getFirst()).thenReturn(5);
        when(lastLancer.getSecond()).thenReturn(5);
        when(lastLancer.getThird()).thenReturn(10);
        when(lastLancer.getType()).thenReturn(ThrowType.SPARE);

        Throw strike = PowerMockito.mock(Throw.class);
        when(strike.getFirst()).thenReturn(10);
        when(strike.getType()).thenReturn(ThrowType.STRIKE);

        Throw spare = PowerMockito.mock(Throw.class);
        when(spare.getFirst()).thenReturn(5);
        when(spare.getSecond()).thenReturn(5);
        when(spare.getType()).thenReturn(ThrowType.SPARE);

        int finalScore = 200;

        jeu.addThrow(strike); // 20
        jeu.addThrow(spare); // 20
        jeu.addThrow(strike); // 20
        jeu.addThrow(spare); // 20
        jeu.addThrow(strike); // 20
        jeu.addThrow(spare); // 20
        jeu.addThrow(strike); // 20
        jeu.addThrow(spare); // 20
        jeu.addThrow(strike); // 20
        jeu.addLastThrow(lastLancer); // 20

        int testScore = jeu.computeScore();

        verify(lastLancer, times(2)).getFirst();
        verify(lastLancer, times(2)).getSecond();
        verify(lastLancer).getThird();
        assertEquals(finalScore, testScore);
    }
}
