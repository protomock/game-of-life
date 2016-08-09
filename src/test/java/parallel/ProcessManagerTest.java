package parallel;

import org.junit.Test;
import org.mockito.Mockito;
import parallel.evaluators.interfaces.ILifeEvaluator;
import parallel.providers.interfaces.IMatrixInformationProvider;
import parallel.providers.interfaces.IPartialProcessProvider;
import parallel.providers.interfaces.IProcessorInformationProvider;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class ProcessManagerTest {

    private final IProcessManager subject;
    private final IProcessorInformationProvider processorInformationProvider;
    private final IMatrixInformationProvider matrixInformationProvider;
    private final IPartialProcessProvider partialProcessProvider;
    private final ILifeEvaluator lifeEvaluator;
    private final IPositionCalculator positionCalculator;

    public ProcessManagerTest() {
        processorInformationProvider = mock(IProcessorInformationProvider.class);
        matrixInformationProvider = mock(IMatrixInformationProvider.class);
        partialProcessProvider = mock(IPartialProcessProvider.class);
        positionCalculator = mock(IPositionCalculator.class);

        lifeEvaluator = mock(ILifeEvaluator.class);

        subject = new ProcessManager(processorInformationProvider,matrixInformationProvider,partialProcessProvider, positionCalculator);
    }

    @Test
    public void allocateAvailableThreads_should_determine_create_the_correct_amount_of_threads_for_matrix_size(){

        IPartialProcess partialProcess1 = mock(IPartialProcess.class);
        IPartialProcess partialProcess2 = mock(IPartialProcess.class);
        IPartialProcess partialProcess3 = mock(IPartialProcess.class);
        IPartialProcess partialProcess4 = mock(IPartialProcess.class);


        Mockito
                .when(matrixInformationProvider.getMatrixSize())
                .thenReturn(100);

        Mockito
                .when(processorInformationProvider.getNumberOfProcessors())
                .thenReturn(4);

        Mockito
                .when(partialProcessProvider.createPartialProcess(anyString()))
                .thenReturn(partialProcess1)
                .thenReturn(partialProcess2)
                .thenReturn(partialProcess3)
                .thenReturn(partialProcess4);

        Mockito
                .when(partialProcess1.setStartPosition(anyInt(),anyInt()))
                .thenReturn(partialProcess1);

        Mockito
                .when(partialProcess2.setStartPosition(anyInt(),anyInt()))
                .thenReturn(partialProcess2);

        Mockito
                .when(partialProcess3.setStartPosition(anyInt(),anyInt()))
                .thenReturn(partialProcess3);

        Mockito
                .when(partialProcess4.setStartPosition(anyInt(),anyInt()))
                .thenReturn(partialProcess4);

        Mockito
                .when(positionCalculator.getNextStartPosition(0,0,25))
                .thenReturn(positionCalculator);

        Mockito
                .when(positionCalculator.getNextStartPosition(5,2,25))
                .thenReturn(positionCalculator);

        Mockito
                .when(positionCalculator.getNextStartPosition(0,5,25))
                .thenReturn(positionCalculator);

        Mockito
                .when(positionCalculator.getNextStartPosition(5,7,25))
                .thenReturn(positionCalculator);
        Mockito
                .when(positionCalculator.getColumn())
                .thenReturn(5)
                .thenReturn(0)
                .thenReturn(5);
        Mockito
                .when(positionCalculator.getRow())
                .thenReturn(2)
                .thenReturn(5)
                .thenReturn(7);

        subject.allocateAvailableThreads();

        Mockito
                .verify(partialProcess1,times(1)).setStartPosition(0,0);

        Mockito
                .verify(partialProcess1,times(1)).runFor(25);

        Mockito
                .verify(partialProcess2,times(1)).setStartPosition(5,2);

        Mockito
                .verify(partialProcess2,times(1)).runFor(25);

        Mockito
                .verify(partialProcess3,times(1)).setStartPosition(0,5);

        Mockito
                .verify(partialProcess3,times(1)).runFor(25);

        Mockito
                .verify(partialProcess4,times(1)).setStartPosition(5,7);

        Mockito
                .verify(partialProcess4,times(1)).runFor(25);

    }

    @Test
    public void allocateAvailableThreads_should_determine_create_the_correct_amount_of_threads_for_small_matrix_size(){

        IPartialProcess partialProcess1 = mock(IPartialProcess.class);
        IPartialProcess partialProcess2 = mock(IPartialProcess.class);


        Mockito
                .when(matrixInformationProvider.getMatrixSize())
                .thenReturn(16);

        Mockito
                .when(processorInformationProvider.getNumberOfProcessors())
                .thenReturn(2);

        Mockito
                .when(partialProcessProvider.createPartialProcess(anyString()))
                .thenReturn(partialProcess1)
                .thenReturn(partialProcess2);

        Mockito
                .when(partialProcess1.setStartPosition(anyInt(),anyInt()))
                .thenReturn(partialProcess1);

        Mockito
                .when(partialProcess2.setStartPosition(anyInt(),anyInt()))
                .thenReturn(partialProcess2);

        Mockito
                .when(positionCalculator.getNextStartPosition(0,0,8))
                .thenReturn(positionCalculator);

        Mockito
                .when(positionCalculator.getNextStartPosition(0,2,8))
                .thenReturn(positionCalculator);

        Mockito
                .when(positionCalculator.getColumn())
                .thenReturn(0);
        Mockito
                .when(positionCalculator.getRow())
                .thenReturn(2);


        subject.allocateAvailableThreads();

        Mockito
                .verify(partialProcess1,times(1)).setStartPosition(0,0);

        Mockito
                .verify(partialProcess1,times(1)).runFor(8);

        Mockito
                .verify(partialProcess2,times(1)).setStartPosition(0,2);

        Mockito
                .verify(partialProcess2,times(1)).runFor(8);


    }

    @Test
    public void allocateAvailableThreads_should_determine_create_the_correct_amount_of_threads_for_tiny_matrix_size(){

        IPartialProcess partialProcess1 = mock(IPartialProcess.class);


        Mockito
                .when(matrixInformationProvider.getMatrixSize())
                .thenReturn(4);

        Mockito
                .when(processorInformationProvider.getNumberOfProcessors())
                .thenReturn(8);

        Mockito
                .when(partialProcessProvider.createPartialProcess(anyString()))
                .thenReturn(partialProcess1);

        Mockito
                .when(partialProcess1.setStartPosition(anyInt(),anyInt()))
                .thenReturn(partialProcess1);

        Mockito
                .when(positionCalculator.getNextStartPosition(0,0,4))
                .thenReturn(positionCalculator);

        Mockito
                .when(positionCalculator.getColumn())
                .thenReturn(1);
        Mockito
                .when(positionCalculator.getRow())
                .thenReturn(1);


        subject.allocateAvailableThreads();

        Mockito
                .verify(partialProcess1,times(1)).setStartPosition(0,0);

        Mockito
                .verify(partialProcess1,times(1)).runFor(4);



    }

    @Test
    public void allocateAvailableThreads_should_determine_create_the_correct_amount_of_threads_for_odd_demension_matrix_size(){

        IPartialProcess partialProcess1 = mock(IPartialProcess.class);
        IPartialProcess partialProcess2 = mock(IPartialProcess.class);
        IPartialProcess partialProcess3 = mock(IPartialProcess.class);
        IPartialProcess partialProcess4 = mock(IPartialProcess.class);


        Mockito
                .when(matrixInformationProvider.getMatrixSize())
                .thenReturn(49); //7 X 7 matrix

        Mockito
                .when(processorInformationProvider.getNumberOfProcessors())
                .thenReturn(4);

        Mockito
                .when(partialProcessProvider.createPartialProcess(anyString()))
                .thenReturn(partialProcess1)
                .thenReturn(partialProcess2)
                .thenReturn(partialProcess3)
                .thenReturn(partialProcess4);

        Mockito
                .when(partialProcess1.setStartPosition(anyInt(),anyInt()))
                .thenReturn(partialProcess1);

        Mockito
                .when(partialProcess2.setStartPosition(anyInt(),anyInt()))
                .thenReturn(partialProcess2);

        Mockito
                .when(partialProcess3.setStartPosition(anyInt(),anyInt()))
                .thenReturn(partialProcess3);

        Mockito
                .when(partialProcess4.setStartPosition(anyInt(),anyInt()))
                .thenReturn(partialProcess4);

        Mockito
                .when(positionCalculator.getNextStartPosition(0,0,12))
                .thenReturn(positionCalculator);
        Mockito
                .when(positionCalculator.getNextStartPosition(5,1,12))
                .thenReturn(positionCalculator);

        Mockito
                .when(positionCalculator.getNextStartPosition(3,3,12))
                .thenReturn(positionCalculator);

        Mockito
                .when(positionCalculator.getNextStartPosition(1,5,13))
                .thenReturn(positionCalculator);

        Mockito
                .when(positionCalculator.getColumn())
                .thenReturn(5)
                .thenReturn(3)
                .thenReturn(1);

        Mockito
                .when(positionCalculator.getRow())
                .thenReturn(1)
                .thenReturn(3)
                .thenReturn(5);


        subject.allocateAvailableThreads();

        Mockito
                .verify(partialProcess1,times(1)).setStartPosition(0,0);

        Mockito
                .verify(partialProcess1,times(1)).runFor(12);

        Mockito
                .verify(partialProcess2,times(1)).setStartPosition(5,1);

        Mockito
                .verify(partialProcess2,times(1)).runFor(12);

        Mockito
                .verify(partialProcess3,times(1)).setStartPosition(3,3);

        Mockito
                .verify(partialProcess3,times(1)).runFor(12);

        Mockito
                .verify(partialProcess4,times(1)).setStartPosition(1,5);

        Mockito
                .verify(partialProcess4,times(1)).runFor(13);


    }
}