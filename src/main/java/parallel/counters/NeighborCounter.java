package parallel.counters;

import parallel.counters.interfaces.INeighborCounter;
import parallel.evaluators.interfaces.IBottomNeighborLifeEvaluator;
import parallel.evaluators.interfaces.ILeftNeighborLifeEvaluator;
import parallel.evaluators.interfaces.IRightNeighborLifeEvaluator;
import parallel.evaluators.interfaces.ITopNeighborLifeEvaluator;


public class NeighborCounter implements INeighborCounter {

    private final ITopNeighborLifeEvaluator topNeighborLifeEvaluator;
    private IBottomNeighborLifeEvaluator bottomNeighborLifeEvaluator;
    private ILeftNeighborLifeEvaluator leftNeighborLifeEvaluator;
    private IRightNeighborLifeEvaluator rightNeighborLifeEvaluator;

    public NeighborCounter(ITopNeighborLifeEvaluator topNeighborLifeEvaluator, IBottomNeighborLifeEvaluator bottomNeighborLifeEvaluator, ILeftNeighborLifeEvaluator leftNeighborLifeEvaluator, IRightNeighborLifeEvaluator rightNeighborLifeEvaluator) {
        this.topNeighborLifeEvaluator = topNeighborLifeEvaluator;
        this.bottomNeighborLifeEvaluator = bottomNeighborLifeEvaluator;
        this.leftNeighborLifeEvaluator = leftNeighborLifeEvaluator;
        this.rightNeighborLifeEvaluator = rightNeighborLifeEvaluator;
    }

    @Override
    public int getTopNeighborCount(int row, int col) {
        int aliveNeighbors = 0;
        if(topNeighborLifeEvaluator.isTopLeftNeighborAlive(row, col)){ aliveNeighbors++; }
        if(topNeighborLifeEvaluator.isTopRightNeighborAlive(row, col)){ aliveNeighbors++; }
        if(topNeighborLifeEvaluator.isTopCenterNeighborAlive(row, col)){ aliveNeighbors++; }
        return aliveNeighbors;
    }


    @Override
    public int getBottomNeighborCount(int row, int col) {
        int aliveNeighbors = 0;
        if(bottomNeighborLifeEvaluator.isBottomLeftNeighborAlive(row, col)){ aliveNeighbors++; }
        if(bottomNeighborLifeEvaluator.isBottomCenterNeighborAlive(row, col)){ aliveNeighbors++; }
        if(bottomNeighborLifeEvaluator.isBottomRightNeighborAlive(row, col)){ aliveNeighbors++; }
        return aliveNeighbors;
    }

    @Override
    public int getLeftNeighborCount(int row, int col) {
        int aliveNeighbors = 0;
        if(leftNeighborLifeEvaluator.isLeftTopNeighborAlive(row, col)){ aliveNeighbors++; }
        if(leftNeighborLifeEvaluator.isLeftCenterNeighborAlive(row, col)){ aliveNeighbors++; }
        if(leftNeighborLifeEvaluator.isLeftBottomNeighborAlive(row, col)){ aliveNeighbors++; }
        return aliveNeighbors;
    }

    @Override
    public int getRightNeighborCount(int row, int col) {
        int aliveNeighbors = 0;
        if(rightNeighborLifeEvaluator.isRightTopNeighborAlive(row, col)){ aliveNeighbors++; }
        if(rightNeighborLifeEvaluator.isRightCenterNeighborAlive(row, col)){ aliveNeighbors++; }
        if(rightNeighborLifeEvaluator.isRightBottomNeighborAlive(row, col)){ aliveNeighbors++; }
        return aliveNeighbors;
    }
}
