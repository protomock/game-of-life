package serial;

import serial.managers.interfaces.IMatrixManager;
import serial.counters.interfaces.INeighborCounter;
import serial.processors.interfaces.IGenerationProcessor;

import javax.inject.Inject;

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
