package dcll.SgadRmal;

import dcll.SgadRmal.exceptions.LastThrowErrorException;
import dcll.SgadRmal.implementation.Frame;
import dcll.SgadRmal.implementation.Throw;
import dcll.SgadRmal.implementation.ThrowType;
import dcll.SgadRmal.interfaces.ILastThrow;
import dcll.SgadRmal.interfaces.IThrow;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by seb on 19/03/15.
 * @author Romain
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Throw.class)
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
        when(lastLancer.getType()).thenReturn(ThrowType.NORMAL);
        int finalScore = 80;
        for (int i=0; i<9; ++i) {
            Throw unLancer = PowerMockito.mock(Throw.class);
            when(unLancer.getFirst()).thenReturn(4);
            when(unLancer.getSecond()).thenReturn(4);
            when(unLancer.getType()).thenReturn(ThrowType.NORMAL);
            jeu.addThrow(unLancer);
        }
        jeu.addLastThrow(lastLancer);
        int testScore = jeu.computeScore();

        verify(lastLancer, never()).getThird();
        assertEquals(finalScore, testScore);
    }

    @Test
    public void testComputeScoreMaxScore() throws LastThrowErrorException {
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
        }
        jeu.addLastThrow(lastLancer);

        assertEquals(finalScore, jeu.computeScore());
    }

    @Test
    public void testComputeScoreSpare() throws LastThrowErrorException {
        when(lastLancer.getFirst()).thenReturn(5);
        when(lastLancer.getSecond()).thenReturn(5);
        when(lastLancer.getThird()).thenReturn(5);
        when(lastLancer.getType()).thenReturn(ThrowType.SPARE);
        int finalScore = 150;
        for (int i=0; i<10; ++i) {
            Throw unLancer = PowerMockito.mock(Throw.class);
            when(unLancer.getFirst()).thenReturn(5);
            when(unLancer.getSecond()).thenReturn(5);
            when(unLancer.getType()).thenReturn(ThrowType.SPARE);
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

        assertEquals(finalScore, jeu.computeScore());
    }
}
