package dcll.SgadRmal;

import dcll.SgadRmal.exceptions.BonusErrorException;
import dcll.SgadRmal.implementation.BonusThrow;
import dcll.SgadRmal.implementation.Frame;
import dcll.SgadRmal.implementation.Throw;
import dcll.SgadRmal.interfaces.IThrow;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 * Created by seb on 25/03/15.
 */
public class FrameTest {

    private Frame jeu;
    @Mock
    private IThrow lancer;
    @Mock
    private IThrow bonus;

    @Before
    public void setUp() {
        lancer = Mockito.mock(Throw.class);
        bonus = Mockito.mock(BonusThrow.class);
        jeu = new Frame();
    }

    @Test(expected=BonusErrorException.class)
    public void testAddBonusThrowIllegal() {
        jeu.addThrow(bonus);
    }


}
