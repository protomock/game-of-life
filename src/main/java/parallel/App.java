package parallel;

import parallel.counters.NeighborCounter;
import parallel.counters.interfaces.INeighborCounter;
import parallel.evaluators.*;
import parallel.evaluators.interfaces.*;
import parallel.managers.MatrixManager;
import parallel.managers.interfaces.IMatrixManager;
import parallel.presenters.MatrixPresenter;
import parallel.presenters.interfaces.IMatrixPresenter;
import parallel.processors.GenerationProcessor;
import parallel.processors.interfaces.IGenerationProcessor;
import parallel.providers.MatrixInformationProvider;
import parallel.providers.PartialProcessProvider;
import parallel.providers.ProcessorInformationProvider;
import parallel.providers.ScannerResponseProvider;
import parallel.providers.interfaces.IMatrixInformationProvider;
import parallel.providers.interfaces.IPartialProcessProvider;
import parallel.providers.interfaces.IProcessorInformationProvider;
import parallel.providers.interfaces.IScannerResponseProvider;

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
    private final IProcessorInformationProvider processorInformationProvider;
    private final IMatrixInformationProvider matrixInformationProvider;
    private final IPartialProcessProvider partialProcessProvider;
    private final IPositionCalculator positionCalculator;
    private final IProcessManager processManager;

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
        this.processorInformationProvider = new ProcessorInformationProvider();
        this.matrixInformationProvider = new MatrixInformationProvider();
        this.partialProcessProvider = new PartialProcessProvider(lifeEvaluator);
        this.positionCalculator = new PositionCalculator();
        this.processManager = new ProcessManager(processorInformationProvider, matrixInformationProvider,partialProcessProvider, positionCalculator);
        this.generationProcessor = new GenerationProcessor(scannerResponseProvider,matrixPresenter, matrixManager, processManager);
    }

    public GOL init(){
        return new GOL(matrixManager,generationProcessor);
    }

    public static void main(String[] args) {

        App app = new App();
        app.init().setup();

    }
}
