package serial;

import serial.counters.NeighborCounter;
import serial.counters.interfaces.INeighborCounter;
import serial.evaluators.*;
import serial.evaluators.interfaces.*;
import serial.managers.MatrixManager;
import serial.managers.interfaces.IMatrixManager;
import serial.presenters.MatrixPresenter;
import serial.presenters.interfaces.IMatrixPresenter;
import serial.processors.GenerationProcessor;
import serial.processors.interfaces.IGenerationProcessor;
import serial.providers.ScannerResponseProvider;
import serial.providers.interfaces.IScannerResponseProvider;

public class App {

    private final ITopNeighborLifeEvaluator topNeighborLifeEvaluator;
    private final IBottomNeighborLifeEvaluator bottomNeighborLifeEvaluator;
    private final ILeftNeighborLifeEvaluator leftNeighborLifeEvaluator;
    private final IRightNeighborLifeEvaluator rightNeighborLifeEvaluator;
    private final INeighborCounter neighborCounter;
    private final IMatrixManager matrixManager;
    private final IScannerResponseProvider scannerResponseProvider;
    private final IGenerationProcessor generationProcessor;
    private final ILifeEvaluator lifeEvaluator;
    private final IMatrixPresenter matrixPresenter;

    public App(){
        this.topNeighborLifeEvaluator = new TopNeighborLifeEvaluator();
        this.bottomNeighborLifeEvaluator = new BottomNeighborLifeEvaluator();
        this.leftNeighborLifeEvaluator = new LeftNeighborLifeEvaluator();
        this.rightNeighborLifeEvaluator = new RightNeighborLifeEvaluator();
        this.neighborCounter = new NeighborCounter(topNeighborLifeEvaluator,bottomNeighborLifeEvaluator,leftNeighborLifeEvaluator,rightNeighborLifeEvaluator);
        this.scannerResponseProvider = new ScannerResponseProvider();
        this.matrixPresenter = new MatrixPresenter();
        this.lifeEvaluator = new LifeEvaluator(neighborCounter);
        this.matrixManager = new MatrixManager(scannerResponseProvider,matrixPresenter);
        this.generationProcessor = new GenerationProcessor(lifeEvaluator,scannerResponseProvider,matrixPresenter, matrixManager);
    }

    public GOL init(){
        return new GOL(matrixManager,generationProcessor);
    }

    public static void main(String[] args) {

        App app = new App();
        app.init().setup();


        //        long start = System.currentTimeMillis();
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[i].length; j++) {
//                life.isAlive(matrix, i, j);
//                // System.out.println("----------GRAPH--------------------");
//                // life.printMatrix(matrix);
//                // System.out.print("(" + i + "," + j + ")\n");
//                // System.out.println("-----------------------------------");
//            }
//        }
//        //life.printMatrix(matrix);
//        long stop = System.currentTimeMillis();
//        double elapsed = (stop - start) / 1000.0;
//        System.out.println("Time for completetion:  " + elapsed + " seconds");
//
//        // life.printMatrix(matrix);

    }
}
