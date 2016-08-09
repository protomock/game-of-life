package parallel;

import org.junit.Assert;
import org.junit.Test;
import parallel.processors.GenerationProcessor;

import static org.junit.Assert.*;

public class PositionCalculatorTest {

    private final IPositionCalculator subject;

    public PositionCalculatorTest() {
        subject = new PositionCalculator();
    }

    @Test
    public void getNextStartPosition_should_return_the_correct_start_position_for_4_X_4_2_processors(){
        populateMatrix(4,4);
        IPositionCalculator actual = subject.getNextStartPosition(0,0,8);

        Assert.assertSame(subject,actual);
        Assert.assertEquals(0,actual.getColumn());
        Assert.assertEquals(2,actual.getRow());

    }

    @Test
    public void getNextStartPosition_should_return_the_correct_start_position_for_7_X_7_4_processors(){
        populateMatrix(7,7);
        IPositionCalculator actual = subject.getNextStartPosition(5,1,12);

        Assert.assertSame(subject,actual);
        Assert.assertEquals(3,actual.getColumn());
        Assert.assertEquals(3,actual.getRow());

    }

    @Test
    public void getNextStartPosition_should_return_the_correct_start_position_for_4_X_4_1_processor(){
        populateMatrix(4,4);
        int cellsPerProcessor = 16;
        IPositionCalculator actual = subject.getNextStartPosition(0,0,cellsPerProcessor);

        Assert.assertSame(subject,actual);
        Assert.assertEquals(3,actual.getColumn());
        Assert.assertEquals(3,actual.getRow());
        Assert.assertEquals(16,cellsPerProcessor); //Double check for reference vs copy;

    }

    @Test
    public void getNextStartPosition_should_return_the_correct_start_position_for_10_X_10_4_processors(){
        populateMatrix(10,10);
        IPositionCalculator actual = subject.getNextStartPosition(5,2,25);

        Assert.assertSame(subject,actual);
        Assert.assertEquals(0,actual.getColumn());
        Assert.assertEquals(5,actual.getRow());

    }


    private void populateMatrix(int numOfCol, int numOfRows){
        GenerationProcessor.generationCopy = new int[numOfCol][numOfRows];

        for(int i = 0; i < GenerationProcessor.generationCopy.length; i++){
            for(int j = 0; j < GenerationProcessor.generationCopy[i].length; j++){
                GenerationProcessor.generationCopy[i][j] = 0;
            }
        }
    }
}