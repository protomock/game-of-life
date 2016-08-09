package serial.processors;

import dagger.Module;
import serial.GOL;
import serial.evaluators.interfaces.ILifeEvaluator;
import serial.managers.interfaces.IMatrixManager;
import serial.presenters.interfaces.IMatrixPresenter;
import serial.providers.interfaces.IScannerResponseProvider;
import serial.processors.interfaces.IGenerationProcessor;

import javax.inject.Inject;

public class GenerationProcessor implements IGenerationProcessor {
    private ILifeEvaluator lifeEvaluator;
    private IScannerResponseProvider scannerResponseProvider;
    private IMatrixPresenter matrixPresenter;
    private IMatrixManager matrixManager;
    public static int[][] generationCopy;

    public GenerationProcessor(ILifeEvaluator lifeEvaluator, IScannerResponseProvider scannerResponseProvider, IMatrixPresenter matrixPresenter, IMatrixManager matrixManager) {
        this.lifeEvaluator = lifeEvaluator;
        this.scannerResponseProvider = scannerResponseProvider;
        this.matrixPresenter = matrixPresenter;
        this.matrixManager = matrixManager;
    }

    @Override
    public int getNumberOfGenerations() {
        int numOfGenerations = scannerResponseProvider.getScannerIntResponse("How many generations do you want to loop through? (1000) ");
        return numOfGenerations == 0 ? 1000 : numOfGenerations;

    }

    @Override
    public void processGeneration() {

        for(int col = 0; col < GOL.matrix.length; col++){
            for(int row = 0; row < GOL.matrix[col].length; row++){
                generationCopy[col][row] = lifeEvaluator.isAlive(col,row) ? 1 : 0;
            }
        }

        matrixManager.migrate();
        matrixPresenter.present();
    }
}
