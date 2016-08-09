package parallel.managers;

import parallel.GOL;
import parallel.managers.interfaces.IMatrixManager;
import parallel.presenters.interfaces.IMatrixPresenter;
import parallel.processors.GenerationProcessor;
import parallel.providers.interfaces.IScannerResponseProvider;

import javax.inject.Inject;

public class MatrixManager implements IMatrixManager {

    private IScannerResponseProvider scannerResponseProvider;
    private IMatrixPresenter matrixPresenter;

    public MatrixManager(IScannerResponseProvider scannerResponseProvider, IMatrixPresenter matrixPresenter) {

        this.scannerResponseProvider = scannerResponseProvider;
        this.matrixPresenter = matrixPresenter;
    }

    @Override
    public void construct() {
        int size = scannerResponseProvider.getScannerIntResponse("Enter matrix dimensions (10000)");
        size = size == 0 ? 10000 : size;
        GOL.matrix = new int[size][size];
        GenerationProcessor.generationCopy = new int[size][size];

        for (int col = 0; col < GOL.matrix.length; col++) {
            for (int row = 0; row < GOL.matrix[col].length; row++) {
                GOL.matrix[col][row] = 0;
                GenerationProcessor.generationCopy[col][row] = 0;
            }
        }
        addInitialPattern((size / 2));

       //matrixPresenter.present();

    }

    @Override
    public void migrate() {
        for (int col = 0; col < GenerationProcessor.generationCopy.length; col++) {
            for (int row = 0; row < GenerationProcessor.generationCopy[col].length; row++) {
                GOL.matrix[col][row] = GenerationProcessor.generationCopy[col][row];
            }
        }
    }


    private void addInitialPattern(int startPosition) {
        if(startPosition >= 4) {
            GOL.matrix[startPosition][startPosition] = 1;
            GOL.matrix[startPosition + 1][startPosition] = 1;
            GOL.matrix[startPosition][startPosition + 1] = 1;
            GOL.matrix[startPosition + 1][startPosition + 1] = 1;
            GOL.matrix[startPosition][startPosition + 2] = 1;

            GenerationProcessor.generationCopy[startPosition][startPosition] = 1;
            GenerationProcessor.generationCopy[startPosition + 1][startPosition] = 1;
            GenerationProcessor.generationCopy[startPosition][startPosition + 1] = 1;
            GenerationProcessor.generationCopy[startPosition + 1][startPosition + 1] = 1;
            GenerationProcessor.generationCopy[startPosition][startPosition + 2] = 1;
        }
    }
}
