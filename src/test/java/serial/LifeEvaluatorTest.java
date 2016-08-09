package serial;

import serial.evaluators.LifeEvaluator;
import serial.evaluators.interfaces.ILifeEvaluator;
import serial.counters.interfaces.INeighborCounter;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class LifeEvaluatorTest {

    private final ILifeEvaluator subject;
    private final INeighborCounter neighborCounter;

    public LifeEvaluatorTest() {

        neighborCounter = mock(INeighborCounter.class);
        subject = new LifeEvaluator(neighborCounter);
    }

    @Test
    public void isAlive_should_return_true_when_total_neighbor_count_is_3(){

        Mockito
                .when(neighborCounter.getBottomNeighborCount(0,1))
                .thenReturn(1);
        Mockito
                .when(neighborCounter.getLeftNeighborCount(0,1))
                .thenReturn(1);
        Mockito
                .when(neighborCounter.getTopNeighborCount(0,1))
                .thenReturn(1);
        Mockito
                .when(neighborCounter.getRightNeighborCount(0,1))
                .thenReturn(0);


        Assert.assertTrue(subject.isAlive(0,1));

    }

    @Test
    public void isAlive_should_return_true_when_total_neighbor_count_is_2(){

        Mockito
                .when(neighborCounter.getBottomNeighborCount(0,1))
                .thenReturn(1);
        Mockito
                .when(neighborCounter.getLeftNeighborCount(0,1))
                .thenReturn(1);
        Mockito
                .when(neighborCounter.getTopNeighborCount(0,1))
                .thenReturn(0);
        Mockito
                .when(neighborCounter.getRightNeighborCount(0,1))
                .thenReturn(0);


        Assert.assertTrue(subject.isAlive(0,1));

    }

    @Test
    public void isAlive_should_return_false_when_total_neighbor_count_is_less_than_2(){

        Mockito
                .when(neighborCounter.getBottomNeighborCount(0,1))
                .thenReturn(1);
        Mockito
                .when(neighborCounter.getLeftNeighborCount(0,1))
                .thenReturn(0);
        Mockito
                .when(neighborCounter.getTopNeighborCount(0,1))
                .thenReturn(0);
        Mockito
                .when(neighborCounter.getRightNeighborCount(0,1))
                .thenReturn(0);


        Assert.assertFalse(subject.isAlive(0,1));

    }

    @Test
    public void isAlive_should_return_false_when_total_neighbor_count_is_greater_than_3(){

        Mockito
                .when(neighborCounter.getBottomNeighborCount(0,1))
                .thenReturn(1);
        Mockito
                .when(neighborCounter.getLeftNeighborCount(0,1))
                .thenReturn(1);
        Mockito
                .when(neighborCounter.getTopNeighborCount(0,1))
                .thenReturn(1);
        Mockito
                .when(neighborCounter.getRightNeighborCount(0,1))
                .thenReturn(1);


        Assert.assertFalse(subject.isAlive(0,1));

    }
}