package dcll.SgadRmal;

import dcll.SgadRmal.exceptions.LastThrowErrorException;
import dcll.SgadRmal.implementation.LastThrow;
import dcll.SgadRmal.implementation.Frame;
import dcll.SgadRmal.implementation.Throw;
import dcll.SgadRmal.interfaces.IThrow;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Created by Romain on 25/03/15.
 */
public class FrameTest {

    private Frame jeu;
    @Mock
    private IThrow lancer;
    @Mock
    private IThrow bonus;

    @Before
    public void setUp() {
        jeu = new Frame();
    }

    @Test(expected = LastThrowErrorException.class)
    public void testAddThrowBonusIllegal() {
        bonus = Mockito.mock(LastThrow.class);
        jeu.addThrow(bonus);
    }

    @Test(expected = LastThrowErrorException.class)
    public void testAddThrowBonusNotAllowed() {
        bonus = Mockito.mock(LastThrow.class);
        lancer = Mockito.mock(Throw.class);
        when(lancer.getFirst()).thenReturn(4);
        when(lancer.getSecond()).thenReturn(5);
        for (int i = 0; i < 9; ++i) {
            Throw unLancer = Mockito.mock(Throw.class);
            jeu.addThrow(unLancer);
        }
        jeu.addThrow(lancer);
        jeu.addThrow(bonus);
    }

    @Test
    public void testComputeScoreNoBonus() {
        int finalScore = 80;
        for (int i = 0; i < 10; ++i) {
            Throw unLancer = Mockito.mock(Throw.class);
            when(unLancer.getFirst()).thenReturn(4);
            when(unLancer.getSecond()).thenReturn(4);
            jeu.addThrow(unLancer);
        }
        assertEquals(finalScore, jeu.computeScore());
    }

    @Test
    public void testComputeScoreMaxScore() {
        int finalScore = 300;
        for (int i = 0; i < 10; ++i) {
            Throw strike = Mockito.mock(Throw.class);
            when(strike.getFirst()).thenReturn(10);
            jeu.addThrow(strike);
        }
        bonus = Mockito.mock(LastThrow.class);
        when(bonus.getFirst()).thenReturn(10);
        when(bonus.getSecond()).thenReturn(10);
        assertEquals(finalScore, jeu.computeScore());
    }

    @Test
    public void testComputeScoreSpare() {
        int finalScore = 150;
        for (int i = 0; i < 10; ++i) {
            Throw unLancer = Mockito.mock(Throw.class);
            when(unLancer.getFirst()).thenReturn(5);
            when(unLancer.getSecond()).thenReturn(5);
            jeu.addThrow(unLancer);
        }
        bonus = Mockito.mock(LastThrow.class);
        when(bonus.getFirst()).thenReturn(5);
        verify(bonus, never()).getSecond();
        assertEquals(finalScore, jeu.computeScore());
    }
}
