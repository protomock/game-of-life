package parallel;

import parallel.evaluators.interfaces.ILifeEvaluator;
import parallel.processors.GenerationProcessor;

public class PartialProcess extends Thread implements IPartialProcess {

    private final ILifeEvaluator lifeEvaluator;
    private int startColumn = 0;
    private int startRow = 0;
    private int numberOfCells = 0;

    public PartialProcess(String threadName, ILifeEvaluator lifeEvaluator) {
        super(threadName);
        this.lifeEvaluator = lifeEvaluator;
    }

    @Override
    public IPartialProcess setStartPosition(int startColumn, int startRow){
        this.startColumn = startColumn;
        this.startRow = startRow;
        return this;
    }

    @Override
    public void runFor(int numberOfCells) {
        this.numberOfCells = numberOfCells;
        start();
    }

    @Override
    public void run() {
        super.run();

        int count = 0;
        int col = startColumn;
        int row = startRow;
        while(row < GenerationProcessor.generationCopy[col].length && numberOfCells != count){
            while (col < GenerationProcessor.generationCopy.length && numberOfCells != count){
                GenerationProcessor.generationCopy[col][row] = lifeEvaluator.isAlive(col,row) ? 1 : 0;
                count++;
                col++;
            }
            if(col == GenerationProcessor.generationCopy.length){
                col = 0;
            }
            row++;
        }

    }
}
