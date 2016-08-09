package parallel.processors;

import parallel.IProcessManager;
import parallel.managers.interfaces.IMatrixManager;
import parallel.presenters.interfaces.IMatrixPresenter;
import parallel.processors.interfaces.IGenerationProcessor;
import parallel.providers.interfaces.IScannerResponseProvider;

import javax.inject.Inject;

public class GenerationProcessor implements IGenerationProcessor {
    private IScannerResponseProvider scannerResponseProvider;
    private IMatrixPresenter matrixPresenter;
    private IMatrixManager matrixManager;
    private IProcessManager processManager;
    public static int[][] generationCopy;

    public GenerationProcessor(IScannerResponseProvider scannerResponseProvider, IMatrixPresenter matrixPresenter, IMatrixManager matrixManager, IProcessManager processManager) {
        this.scannerResponseProvider = scannerResponseProvider;
        this.matrixPresenter = matrixPresenter;
        this.matrixManager = matrixManager;
        this.processManager = processManager;
    }

    @Override
    public int getNumberOfGenerations() {
        int numOfGenerations = scannerResponseProvider.getScannerIntResponse("How many generations do you want to loop through? (1000) ");
        return numOfGenerations == 0 ? 1000 : numOfGenerations;
    }

    @Override
    public void setProcessingInfo() {
        processManager.calculateThreadInfo();
    }

    @Override
    public void processGeneration() {
        processManager.allocateAvailableThreads();
        matrixManager.migrate();
       // matrixPresenter.present();
    }
}
