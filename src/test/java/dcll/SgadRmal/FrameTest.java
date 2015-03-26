package dcll.SgadRmal;

import dcll.SgadRmal.exceptions.LastThrowErrorException;
import dcll.SgadRmal.implementation.Frame;
import dcll.SgadRmal.implementation.Throw;
import dcll.SgadRmal.interfaces.ILastThrow;
import dcll.SgadRmal.interfaces.IThrow;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by seb on 19/03/15.
 * @author Romain
 *
 */
public class FrameTest {

    private Frame jeu;
    @Mock
    private IThrow lancer;
    @Mock
    private ILastThrow lastLancer;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        jeu = new Frame();
    }

    @Test (expected = LastThrowErrorException.class)
    public void testAddLastThrowIllegalFirstThrow() throws LastThrowErrorException {
        jeu.addLastThrow(lastLancer);
    }

    @Test (expected = LastThrowErrorException.class)
    public void testAddLastThrowIllegalEighthThrow() throws LastThrowErrorException {
        for (int i=0; i<8; ++i) {
            Throw unLancer = Mockito.mock(Throw.class);
            jeu.addThrow(unLancer);
        }
        jeu.addLastThrow(lastLancer);
    }

    @Test
    public void testComputeScoreNoBonus() throws LastThrowErrorException {
        when(lastLancer.getFirst()).thenReturn(4);
        when(lastLancer.getSecond()).thenReturn(4);
        int finalScore = 80;
        for (int i=0; i<9; ++i) {
            Throw unLancer = Mockito.mock(Throw.class);
            when(unLancer.getFirst()).thenReturn(4);
            when(unLancer.getSecond()).thenReturn(4);
            jeu.addThrow(unLancer);
        }
        jeu.addLastThrow(lastLancer);
        int testScore = jeu.computeScore();

        verify(lastLancer.getThird(), never());
        assertEquals(finalScore, testScore);
    }

    @Test
    public void testComputeScoreMaxScore() throws LastThrowErrorException {
        when(lastLancer.getFirst()).thenReturn(10);
        when(lastLancer.getSecond()).thenReturn(10);
        when(lastLancer.getThird()).thenReturn(10);
        int finalScore = 300;
        for (int i=0; i<9; ++i) {
            Throw strike = Mockito.mock(Throw.class);
            when(strike.getFirst()).thenReturn(10);
            jeu.addThrow(strike);
        }
        jeu.addLastThrow(lastLancer);

        assertEquals(finalScore, jeu.computeScore());
    }

    @Test
    public void testComputeScoreSpare() throws LastThrowErrorException {
        when(lastLancer.getFirst()).thenReturn(5);
        when(lastLancer.getSecond()).thenReturn(5);
        when(lastLancer.getThird()).thenReturn(5);
        int finalScore = 150;
        for (int i=0; i<10; ++i) {
            Throw unLancer = Mockito.mock(Throw.class);
            when(unLancer.getFirst()).thenReturn(5);
            when(unLancer.getSecond()).thenReturn(5);
            jeu.addThrow(unLancer);
        }
        jeu.addLastThrow(lastLancer);

        assertEquals(finalScore, jeu.computeScore());
    }

    @Test
    public void testComputeScoreStrikeSpare() throws LastThrowErrorException {
        when(lastLancer.getFirst()).thenReturn(5);
        when(lastLancer.getSecond()).thenReturn(5);
        when(lastLancer.getThird()).thenReturn(10);

        Throw strike = Mockito.mock(Throw.class);
        when(strike.getFirst()).thenReturn(10);

        Throw spare = Mockito.mock(Throw.class);
        when(spare.getFirst()).thenReturn(5);
        when(spare.getSecond()).thenReturn(5);

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

        assertEquals(finalScore, jeu.computeScore());
    }
}
