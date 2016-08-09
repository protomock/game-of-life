package serial;

import serial.evaluators.TopNeighborLifeEvaluator;
import serial.evaluators.interfaces.ITopNeighborLifeEvaluator;
import org.junit.Assert;
import org.junit.Test;


public class TopNeighborLifeEvaluatorTest {

    private final ITopNeighborLifeEvaluator subject;

    public TopNeighborLifeEvaluatorTest(){

        subject = new TopNeighborLifeEvaluator();

    }

    @Test
    public void isTopLeftNeighborAlive_should_return_true_left_neighbor_happy_path(){
        populateMatrix(8,8);
        GOL.matrix[1][1] = 1;

        boolean actual = subject.isTopLeftNeighborAlive(2,2);

        Assert.assertTrue(actual);
    }


    @Test
    public void isTopLeftNeighborAlive_should_return_true_left_neighbor_when_at_first_column(){
        populateMatrix(8,8);
        GOL.matrix[7][1] = 1;

        boolean actual = subject.isTopLeftNeighborAlive(0,2);

        Assert.assertTrue(actual);
    }

    @Test
    public void isTopLeftNeighborAlive_should_return_true_left_neighbor_when_at_first_position(){
        populateMatrix(8,8);
        GOL.matrix[7][7] = 1;

        boolean actual = subject.isTopLeftNeighborAlive(0,0);

        Assert.assertTrue(actual);
    }

    @Test
    public void isTopRightNeighborAlive_should_return_true_left_neighbor_happy_path(){
        populateMatrix(8,8);
        GOL.matrix[1][3] = 1;

        boolean actual = subject.isTopRightNeighborAlive(2,2);

        Assert.assertTrue(actual);
    }


    @Test
    public void isTopRightNeighborAlive_should_return_true_left_neighbor_when_at_first_column(){
        populateMatrix(8,8);
        GOL.matrix[7][3] = 1;

        boolean actual = subject.isTopRightNeighborAlive(0,2);

        Assert.assertTrue(actual);
    }

    @Test
    public void isTopRightNeighborAlive_should_return_true_left_neighbor_when_at_last_position(){
        populateMatrix(8,8);
        GOL.matrix[7][0] = 1;

        boolean actual = subject.isTopRightNeighborAlive(0,7);

        Assert.assertTrue(actual);
    }

    @Test
    public void isTopCenterNeighborAlive_should_return_true_left_neighbor_happy_path(){
        populateMatrix(8,8);
        GOL.matrix[1][2] = 1;

        boolean actual = subject.isTopCenterNeighborAlive(2,2);

        Assert.assertTrue(actual);
    }


    @Test
    public void isTopCenterNeighborAlive_should_return_true_left_neighbor_when_at_first_column(){
        populateMatrix(8,8);
        GOL.matrix[7][2] = 1;

        boolean actual = subject.isTopCenterNeighborAlive(0,2);

        Assert.assertTrue(actual);
    }



    private void populateMatrix(int numOfRows, int numOfCol){
        GOL.matrix = new int[numOfRows][numOfCol];

        for(int i = 0; i < GOL.matrix.length; i++){
            for(int j = 0; j < GOL.matrix[i].length; j++){
                GOL.matrix[i][j] = 0;
            }
        }
    }
}
