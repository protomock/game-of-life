package parallel;

import parallel.providers.interfaces.IMatrixInformationProvider;
import parallel.providers.interfaces.IPartialProcessProvider;
import parallel.providers.interfaces.IProcessorInformationProvider;

import java.util.ArrayList;

public class ProcessManager implements IProcessManager {

    private IProcessorInformationProvider processorCountProvider;
    private IMatrixInformationProvider matrixInformationProvider;
    private IPartialProcessProvider partialProcessProvider;
    private IPositionCalculator positionCalculator;
    private ArrayList<Thread> threads;
    private int totalNumberOfCells;
    private int totalNumberOfProcessors;
    private int cellsPerProcessor;
    private int offsetForLastProcessor;

    public ProcessManager(IProcessorInformationProvider processorCountProvider, IMatrixInformationProvider matrixInformationProvider, IPartialProcessProvider partialProcessProvider, IPositionCalculator positionCalculator) {
        this.processorCountProvider = processorCountProvider;
        this.matrixInformationProvider = matrixInformationProvider;
        this.partialProcessProvider = partialProcessProvider;
        this.positionCalculator = positionCalculator;
        this.threads = new ArrayList<Thread>();
    }

    public void calculateThreadInfo() {
        totalNumberOfCells = matrixInformationProvider.getMatrixSize();
        totalNumberOfProcessors = processorCountProvider.getNumberOfProcessors();
        cellsPerProcessor = totalNumberOfCells;

        if(!(totalNumberOfCells < totalNumberOfProcessors)){
            cellsPerProcessor = totalNumberOfCells/totalNumberOfProcessors;
        }

        if(cellsPerProcessor == totalNumberOfCells){
            totalNumberOfProcessors = 1;
        }

        int totalCellsToBeProcessed = totalNumberOfProcessors * cellsPerProcessor;
        offsetForLastProcessor = 0;
        if(!(totalCellsToBeProcessed == totalNumberOfCells)) {
            offsetForLastProcessor = totalNumberOfCells - totalCellsToBeProcessed;
        }
    }

    public void allocateAvailableThreads(){
        int currentColumn = 0;
        int currentRow = 0;
        for(int i = 0; i < totalNumberOfProcessors; i++ ){
            if(i == totalNumberOfProcessors - 1){
                cellsPerProcessor = cellsPerProcessor + offsetForLastProcessor;
            }
            String name = "(" + currentColumn +", " + currentRow +") for " + cellsPerProcessor;
            IPartialProcess partialProcess = partialProcessProvider.createPartialProcess(name);

            partialProcess
                    .setStartPosition(currentColumn, currentRow)
                    .runFor(cellsPerProcessor);

            threads.add((Thread) partialProcess);

            IPositionCalculator nextStartPosition = positionCalculator.getNextStartPosition(currentColumn,currentRow,cellsPerProcessor);
            currentColumn = nextStartPosition.getColumn();
            currentRow = nextStartPosition.getRow();
        }

        for (Thread thread :threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        threads.clear();

    }

}
