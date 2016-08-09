package serial;

import serial.evaluators.RightNeighborLifeEvaluator;
import org.junit.Assert;
import org.junit.Test;

public class RightNeighborLifeEvaluatorTest {

    private final RightNeighborLifeEvaluator subject;

    public RightNeighborLifeEvaluatorTest() {
        subject = new RightNeighborLifeEvaluator();
    }

    @Test
    public void isRightTopNeighborAlive_should_return_true_for_happy_path() {
        populateMatrix(8, 8);
        GOL.matrix[3][1] = 1;

        boolean actual = subject.isRightTopNeighborAlive(2, 2);
        Assert.assertTrue(actual);
    }

    @Test
    public void isRightTopNeighborAlive_should_return_true_when_at_last_position() {
        populateMatrix(8, 8);
        GOL.matrix[0][7] = 1;

        boolean actual = subject.isRightTopNeighborAlive(7, 0);
        Assert.assertTrue(actual);
    }

    @Test
    public void isRightCenterNeighborAlive_should_return_true_for_happy_path() {
        populateMatrix(8, 8);
        GOL.matrix[3][2] = 1;

        boolean actual = subject.isRightCenterNeighborAlive(2, 2);
        Assert.assertTrue(actual);
    }

    @Test
    public void isRightCenterNeighborAlive_should_return_true_when_at_last_position() {
        populateMatrix(8, 8);
        GOL.matrix[0][3] = 1;

        boolean actual = subject.isRightCenterNeighborAlive(7, 3);
        Assert.assertTrue(actual);
    }

    @Test
    public void isRightBottomNeighborAlive_should_return_true_for_happy_path() {
        populateMatrix(8, 8);
        GOL.matrix[3][3] = 1;

        boolean actual = subject.isRightBottomNeighborAlive(2, 2);
        Assert.assertTrue(actual);
    }

    @Test
    public void isRightBottomNeighborAlive_should_return_true_when_at_last_position() {
        populateMatrix(8, 8);
        GOL.matrix[0][0] = 1;

        boolean actual = subject.isRightBottomNeighborAlive(7, 7);
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