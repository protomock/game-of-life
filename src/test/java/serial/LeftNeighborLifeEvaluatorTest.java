package serial;

import serial.evaluators.LeftNeighborLifeEvaluator;
import serial.evaluators.interfaces.ILeftNeighborLifeEvaluator;
import org.junit.Assert;
import org.junit.Test;


public class LeftNeighborLifeEvaluatorTest {

    private final ILeftNeighborLifeEvaluator subject;

    public LeftNeighborLifeEvaluatorTest() {

        subject = new LeftNeighborLifeEvaluator();
    }

    @Test
    public void isLeftTopNeighborAlive_should_return_true_for_happy_path() {
        populateMatrix(8, 8);
        GOL.matrix[1][1] = 1;

        boolean actual = subject.isLeftTopNeighborAlive(2, 2);
        Assert.assertTrue(actual);
    }

    @Test
    public void isLeftTopNeighborAlive_should_return_true_when_at_last_position() {
        populateMatrix(8, 8);
        GOL.matrix[7][7] = 1;

        boolean actual = subject.isLeftTopNeighborAlive(0, 0);
        Assert.assertTrue(actual);
    }

    @Test
    public void isLeftCenterNeighborAlive_should_return_true_for_happy_path() {
        populateMatrix(8, 8);
        GOL.matrix[1][2] = 1;

        boolean actual = subject.isLeftCenterNeighborAlive(2, 2);
        Assert.assertTrue(actual);
    }

    @Test
    public void isLeftCenterNeighborAlive_should_return_true_when_at_last_position() {
        populateMatrix(8, 8);
        GOL.matrix[7][3] = 1;

        boolean actual = subject.isLeftCenterNeighborAlive(0, 3);
        Assert.assertTrue(actual);
    }

    @Test
    public void isLeftBottomNeighborAlive_should_return_true_for_happy_path() {
        populateMatrix(8, 8);
        GOL.matrix[1][3] = 1;

        boolean actual = subject.isLeftBottomNeighborAlive(2, 2);
        Assert.assertTrue(actual);
    }

    @Test
    public void isLeftBottomNeighborAlive_should_return_true_when_at_last_position() {
        populateMatrix(8, 8);
        GOL.matrix[7][0] = 1;

        boolean actual = subject.isLeftBottomNeighborAlive(0, 7);
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