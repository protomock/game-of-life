package serial;

import serial.evaluators.BottomNeighborLifeEvaluator;
import serial.evaluators.interfaces.IBottomNeighborLifeEvaluator;
import org.junit.Assert;
import org.junit.Test;

public class BottomNeighborLifeEvaluatorTest {

    private final IBottomNeighborLifeEvaluator subject;

    public BottomNeighborLifeEvaluatorTest(){
        subject = new BottomNeighborLifeEvaluator();
    }

    @Test
    public void isBottomLeftNeighborAlive_should_return_true_for_happy_path(){
        populateMatrix(8,8);
        GOL.matrix[1][3] = 1;

        boolean actual = subject.isBottomLeftNeighborAlive(2,2);
        Assert.assertTrue(actual);
    }

    @Test
    public void isBottomLeftNeighborAlive_should_return_true_when_at_last_position(){
        populateMatrix(8,8);
        GOL.matrix[7][0] = 1;

        boolean actual = subject.isBottomLeftNeighborAlive(0,7);
        Assert.assertTrue(actual);
    }

    @Test
    public void isBottomCenterNeighborAlive_should_return_true_for_happy_path(){
        populateMatrix(8,8);
        GOL.matrix[2][3] = 1;

        boolean actual = subject.isBottomCenterNeighborAlive(2,2);
        Assert.assertTrue(actual);
    }

    @Test
    public void isBottomCenterNeighborAlive_should_return_true_when_at_last_position(){
        populateMatrix(8,8);
        GOL.matrix[7][0] = 1;

        boolean actual = subject.isBottomCenterNeighborAlive(7,7);
        Assert.assertTrue(actual);
    }

    @Test
    public void isBottomRightNeighborAlive_should_return_true_for_happy_path(){
        populateMatrix(8,8);
        GOL.matrix[3][3] = 1;

        boolean actual = subject.isBottomRightNeighborAlive(2,2);
        Assert.assertTrue(actual);
    }

    @Test
    public void isBottomRightNeighborAlive_should_return_true_when_at_last_position(){
        populateMatrix(8,8);
        GOL.matrix[0][0] = 1;

        boolean actual = subject.isBottomRightNeighborAlive(7,7);
        Assert.assertTrue(actual);
    }

    private void populateMatrix(int numOfCol, int numOfRows){
        GOL.matrix = new int[numOfCol][numOfRows];

        for(int i = 0; i < GOL.matrix.length; i++){
            for(int j = 0; j < GOL.matrix[i].length; j++){
                GOL.matrix[i][j] = 0;
            }
        }
    }
}
