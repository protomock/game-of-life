package parallel;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import parallel.evaluators.interfaces.ILifeEvaluator;
import parallel.processors.GenerationProcessor;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class PartialProcessTest {


    private final ILifeEvaluator lifeEvaluator;
    private final IPartialProcess subject;

    public PartialProcessTest() {
        lifeEvaluator = mock(ILifeEvaluator.class);
        subject =  new PartialProcess("some-name",lifeEvaluator);
    }

    @Test
    public void should_have_set_the_name_of_thread(){

        String actual = ((Thread)subject).getName();

        Assert.assertEquals("some-name", actual);
    }

    @Test
    public void setStartPosition_should_set_start_location_of_thread(){

        IPartialProcess actual = subject.setStartPosition(0,1);

        Assert.assertSame(subject, actual);
    }

    @Test
    public void runFor_should_set_number_of_cells_and_start_thread_when_the_first_thread() throws InterruptedException {
        populateMatrix(4,4);

        Mockito
                .when(lifeEvaluator.isAlive(anyInt(),anyInt()))
                .thenReturn(false);

        subject
                .setStartPosition(0,0)
                .runFor(16);

        ((Thread)subject).join();

        Mockito
                .verify(lifeEvaluator,times(16)).isAlive(anyInt(),anyInt());
    }

    @Test
    public void runFor_should_set_number_of_cells_and_start_thread_when_for_a_middle_thread() throws InterruptedException {
        populateMatrix(4,4);

        Mockito
                .when(lifeEvaluator.isAlive(anyInt(),anyInt()))
                .thenReturn(false);

        subject
                .setStartPosition(0,2)
                .runFor(8);

        ((Thread)subject).join();

        Mockito
                .verify(lifeEvaluator,times(8)).isAlive(anyInt(),anyInt());
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