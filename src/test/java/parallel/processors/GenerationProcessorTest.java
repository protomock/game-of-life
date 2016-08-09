package parallel.processors;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import parallel.GOL;
import parallel.IProcessManager;
import parallel.evaluators.interfaces.ILifeEvaluator;
import parallel.managers.interfaces.IMatrixManager;
import parallel.presenters.interfaces.IMatrixPresenter;
import parallel.processors.interfaces.IGenerationProcessor;
import parallel.providers.interfaces.IScannerResponseProvider;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class GenerationProcessorTest {


    private final IGenerationProcessor subject;
    private final ILifeEvaluator lifeEvaluator;
    private final IScannerResponseProvider scannerResponseProvider;
    private final IMatrixPresenter matrixPresenter;
    private final IMatrixManager matrixManager;
    private final IProcessManager processManager;

    public GenerationProcessorTest() {
        lifeEvaluator = mock(ILifeEvaluator.class);
        scannerResponseProvider = mock(IScannerResponseProvider.class);
        matrixPresenter = mock(IMatrixPresenter.class);
        matrixManager = mock(IMatrixManager.class);
        processManager = mock(IProcessManager.class);

        subject = new GenerationProcessor(scannerResponseProvider, matrixPresenter, matrixManager, processManager);
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
    public void processGeneration_calls_the_right_methods(){

        subject.processGeneration();


        Mockito
                .verify(processManager,times(1)).allocateAvailableThreads();
        Mockito
                .verify(matrixManager, times(1)).migrate();

        Mockito
                .verify(matrixPresenter, times(1)).present();
    }


}