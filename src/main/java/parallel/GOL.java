package parallel;

import parallel.managers.interfaces.IMatrixManager;
import parallel.processors.interfaces.IGenerationProcessor;

public class GOL implements IGOL {
    public static int[][] matrix;
    private IGenerationProcessor generationProcessor;
    private IMatrixManager matrixManager;

    public GOL(IMatrixManager matrixManager, IGenerationProcessor generationProcessor) {
        this.matrixManager = matrixManager;
        this.generationProcessor = generationProcessor;
    }


    public void setup() {
        matrixManager.construct();

        generationProcessor.setProcessingInfo();

        int numOfGenerations = generationProcessor.getNumberOfGenerations();

        long start = System.currentTimeMillis();
        for (int i = 0; i < numOfGenerations; i++) {
            generationProcessor.processGeneration();
        }

        long stop = System.currentTimeMillis();
        double elapsed = (stop - start) / 1000.0;
        System.out.println("Time for completetion:  " + elapsed + " seconds");

    }
}
