package parallel.evaluators;

import parallel.counters.interfaces.INeighborCounter;
import parallel.evaluators.interfaces.ILifeEvaluator;

import javax.inject.Inject;

public class LifeEvaluator implements ILifeEvaluator {
    private INeighborCounter neighborCounter;

    public LifeEvaluator(INeighborCounter neighborCounter) {

        this.neighborCounter = neighborCounter;
    }

    @Override
    public boolean isAlive(int col, int row) {
        int neighbors = 0;

        neighbors = neighbors + neighborCounter.getTopNeighborCount(col,row);
        neighbors = neighbors + neighborCounter.getBottomNeighborCount(col,row);
        neighbors = neighbors + neighborCounter.getLeftNeighborCount(col,row);
        neighbors = neighbors + neighborCounter.getRightNeighborCount(col,row);

        return neighbors >= 2 && neighbors <= 3;

    }
}
