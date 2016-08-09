package serial;

import serial.managers.MatrixManager;
import serial.managers.interfaces.IMatrixManager;
import serial.presenters.interfaces.IMatrixPresenter;
import serial.providers.interfaces.IScannerResponseProvider;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;


public class MatrixManagerTest {

    private final IMatrixManager subject;
    private final IScannerResponseProvider scannerResponseProvider;
    private final IMatrixPresenter matrixPresenter;

    public MatrixManagerTest() {
        scannerResponseProvider = mock(IScannerResponseProvider.class);
        matrixPresenter = mock(IMatrixPresenter.class);
        subject = new MatrixManager(scannerResponseProvider, matrixPresenter);
    }

    @Test
    public void construct_should_construct_expected_matrix_from_user_response(){

        Mockito
                .when(scannerResponseProvider.getScannerIntResponse("Enter matrix dimensions (10000)"))
                .thenReturn(600);

        subject.construct();

        Assert.assertEquals(600, GOL.matrix.length);
        Assert.assertEquals(600, GOL.matrix[0].length);

        Mockito
                .verify(matrixPresenter,times(1)).present();
    }

    @Test
    public void construct_should_populate_the_matrix_with_correct_pattern(){

        Mockito
                .when(scannerResponseProvider.getScannerIntResponse("Enter matrix dimensions (10000)"))
                .thenReturn(60);

        subject.construct();

        Assert.assertEquals(0, GOL.matrix[0][0]);
        Assert.assertEquals(1, GOL.matrix[30][30]);
        Assert.assertEquals(1, GOL.matrix[31][30]);
        Assert.assertEquals(1, GOL.matrix[30][31]);
        Assert.assertEquals(1, GOL.matrix[31][31]);
        Assert.assertEquals(1, GOL.matrix[30][32]);

        Mockito
                .verify(matrixPresenter,times(1)).present();

    }

    @Test
    public void construct_should_populate_the_matrix_with_the_default_size(){

        Mockito
                .when(scannerResponseProvider.getScannerIntResponse("Enter matrix dimensions (10000)"))
                .thenReturn(0);

        subject.construct();

        Assert.assertEquals(10000, GOL.matrix.length);
        Assert.assertEquals(10000, GOL.matrix[0].length);

        Mockito
                .verify(matrixPresenter,times(1)).present();

    }

    @Test
    public void construct_should_handle_small_matrix_issue(){

        Mockito
                .when(scannerResponseProvider.getScannerIntResponse("Enter matrix dimensions (10000)"))
                .thenReturn(2);

        subject.construct();

        Assert.assertEquals(0, GOL.matrix[0][0]);
        Assert.assertEquals(0, GOL.matrix[0][1]);
        Assert.assertEquals(0, GOL.matrix[1][0]);
        Assert.assertEquals(0, GOL.matrix[1][1]);

        Mockito
                .verify(matrixPresenter,times(1)).present();

    }

}