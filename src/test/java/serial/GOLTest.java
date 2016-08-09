package serial;

import serial.managers.interfaces.IMatrixManager;
import serial.processors.interfaces.IGenerationProcessor;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class GOLTest {
    private final IMatrixManager matrixManager;
    private final IGenerationProcessor generationProcessor;
    private final GOL subject;

    public GOLTest() {
        matrixManager = mock(IMatrixManager.class);
        generationProcessor = mock(IGenerationProcessor.class);
        subject = new GOL(matrixManager,generationProcessor);
    }
    @Test
    public void setup_should_call_matrix_manager(){

        Mockito
                .when(generationProcessor.getNumberOfGenerations())
                .thenReturn(10);

        subject.setup();

        Mockito
                .verify(matrixManager, times(1)).construct();

        Mockito
                .verify(generationProcessor,times(1)).getNumberOfGenerations();
        Mockito
                .verify(generationProcessor,times(10)).processGeneration();


    }



}
