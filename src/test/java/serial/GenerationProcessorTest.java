package serial;

import serial.evaluators.interfaces.ILifeEvaluator;
import serial.managers.interfaces.IMatrixManager;
import serial.presenters.interfaces.IMatrixPresenter;
import serial.processors.GenerationProcessor;
import serial.processors.interfaces.IGenerationProcessor;
import serial.providers.interfaces.IScannerResponseProvider;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class GenerationProcessorTest {
    private final IGenerationProcessor subject;
    private final ILifeEvaluator lifeEvaluator;
    private final IScannerResponseProvider scannerResponseProvider;
    private final IMatrixPresenter matrixPresenter;
    private final IMatrixManager matrixManager;

    public GenerationProcessorTest() {
        lifeEvaluator = mock(ILifeEvaluator.class);
        scannerResponseProvider = mock(IScannerResponseProvider.class);
        matrixPresenter = mock(IMatrixPresenter.class);
        matrixManager = mock(IMatrixManager.class);

        subject = new GenerationProcessor(lifeEvaluator, scannerResponseProvider, matrixPresenter, matrixManager);
    }

    @Test
    public void getNumberOfGenerations_when_user_response_is_provided(){
        Mockito
                .when(scannerResponseProvider.getScannerIntResponse("How many generations do you want to loop through? (1000) "))
                .thenReturn(300);

        int actual = subject.getNumberOfGenerations();

        Assert.assertEquals(300, actual);
    }
    @Test
    public void getNumberOfGenerations_when_user_response_is_not_provided(){
        Mockito
                .when(scannerResponseProvider.getScannerIntResponse("How many generations do you want to loop through? (1000) "))
                .thenReturn(0);

        int actual = subject.getNumberOfGenerations();

        Assert.assertEquals(1000, actual);

    }

    @Test
    public void processGeneration_when_cell_is_alive(){
        populateMatrix(10,10);

        Mockito
                .when(lifeEvaluator.isAlive(2,3))
                .thenReturn(true);

        subject.processGeneration();

        Assert.assertEquals(1, GenerationProcessor.generationCopy[2][3]);

        Mockito
                .verify(lifeEvaluator,times(100)).isAlive(anyInt(),anyInt());
        Mockito
                .verify(matrixManager, times(1)).migrate();

        Mockito
                .verify(matrixPresenter, times(1)).present();
    }

    @Test
    public void processGeneration_when_cell_is_not_alive(){
        populateMatrix(8,8);
        GenerationProcessor.generationCopy[1][2] = 1;

        Mockito
                .when(lifeEvaluator.isAlive(1,2))
                .thenReturn(false);

        subject.processGeneration();

        Assert.assertEquals(0,  GenerationProcessor.generationCopy[1][2]);

        Mockito
                .verify(lifeEvaluator,times(64)).isAlive(anyInt(),anyInt());

        Mockito
                .verify(matrixManager, times(1)).migrate();
        Mockito
                .verify(matrixPresenter, times(1)).present();
    }

    private void populateMatrix(int numOfCol, int numOfRows){
        GOL.matrix = new int[numOfCol][numOfRows];
        GenerationProcessor.generationCopy = new int[numOfCol][numOfRows];

        for(int i = 0; i <  GenerationProcessor.generationCopy.length; i++){
            for(int j = 0; j <  GenerationProcessor.generationCopy[i].length; j++){
                GenerationProcessor.generationCopy[i][j] = 0;
                GOL.matrix[i][j] = 0;
            }
        }
    }

}
