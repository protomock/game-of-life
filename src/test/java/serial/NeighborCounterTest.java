package serial;

import serial.counters.NeighborCounter;
import serial.counters.interfaces.INeighborCounter;
import serial.evaluators.interfaces.IBottomNeighborLifeEvaluator;
import serial.evaluators.interfaces.ILeftNeighborLifeEvaluator;
import serial.evaluators.interfaces.IRightNeighborLifeEvaluator;
import serial.evaluators.interfaces.ITopNeighborLifeEvaluator;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class NeighborCounterTest {

    private final INeighborCounter subject;
    private final ITopNeighborLifeEvaluator topNeighborLifeEvaluatorMock;
    private final IBottomNeighborLifeEvaluator bottomNeighborLifeEvaluatorMock;
    private final ILeftNeighborLifeEvaluator leftNeighborLifeEvaluatorMock;
    private final IRightNeighborLifeEvaluator rightNeighborLifeEvaluatorMock;

    public NeighborCounterTest() {
        topNeighborLifeEvaluatorMock = mock(ITopNeighborLifeEvaluator.class);
        bottomNeighborLifeEvaluatorMock = mock(IBottomNeighborLifeEvaluator.class);
        leftNeighborLifeEvaluatorMock = mock(ILeftNeighborLifeEvaluator.class);
        rightNeighborLifeEvaluatorMock = mock(IRightNeighborLifeEvaluator.class);

        subject = new NeighborCounter(topNeighborLifeEvaluatorMock,
                bottomNeighborLifeEvaluatorMock,
                leftNeighborLifeEvaluatorMock,
                rightNeighborLifeEvaluatorMock);
    }

    @Test
    public void getTopNeighborCount_should_get_the_correct_count_for_first_row() {
        GOL.matrix = new int[3][3];

        Mockito
                .when(topNeighborLifeEvaluatorMock.isTopLeftNeighborAlive(0,1))
                .thenReturn(true);

        Mockito
                .when(topNeighborLifeEvaluatorMock.isTopCenterNeighborAlive(0,1))
                .thenReturn(false);

        Mockito
                .when(topNeighborLifeEvaluatorMock.isTopRightNeighborAlive(0,1))
                .thenReturn(true);


        int actual = subject.getTopNeighborCount(0, 1);

        Assert.assertEquals(2, actual);

    }

    @Test
    public void getBottomNeighborCount_should_get_the_correct_count_for_last_row() {
        GOL.matrix = new int[3][3];

        Mockito
                .when(bottomNeighborLifeEvaluatorMock.isBottomLeftNeighborAlive(2,1))
                .thenReturn(false);

        Mockito
                .when(bottomNeighborLifeEvaluatorMock.isBottomCenterNeighborAlive(2,1))
                .thenReturn(false);

        Mockito
                .when(bottomNeighborLifeEvaluatorMock.isBottomRightNeighborAlive(2,1))
                .thenReturn(true);


        int actual = subject.getBottomNeighborCount(2, 1);

        Assert.assertEquals(1, actual);

    }


    @Test
    public void getLeftNeighborCount_should_get_the_correct_count_for_first_column() {
        GOL.matrix = new int[3][3];

        Mockito
                .when(leftNeighborLifeEvaluatorMock.isLeftTopNeighborAlive(0,0))
                .thenReturn(true);

        Mockito
                .when(leftNeighborLifeEvaluatorMock.isLeftCenterNeighborAlive(0,0))
                .thenReturn(false);

        Mockito
                .when(leftNeighborLifeEvaluatorMock.isLeftBottomNeighborAlive(0,0))
                .thenReturn(false);


        int actual = subject.getLeftNeighborCount(0, 0);

        Assert.assertEquals(1, actual);

    }

    @Test
    public void getRightNeighborCount_should_get_the_correct_count_for_last_column() {
        GOL.matrix = new int[3][3];

        Mockito
                .when(rightNeighborLifeEvaluatorMock.isRightTopNeighborAlive(2,2))
                .thenReturn(true);

        Mockito
                .when(rightNeighborLifeEvaluatorMock.isRightCenterNeighborAlive(2,2))
                .thenReturn(true);

        Mockito
                .when(rightNeighborLifeEvaluatorMock.isRightBottomNeighborAlive(2,2))
                .thenReturn(true);


        int actual = subject.getRightNeighborCount(2, 2);

        Assert.assertEquals(3, actual);

    }
}