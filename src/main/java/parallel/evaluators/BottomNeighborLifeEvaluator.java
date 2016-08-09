package parallel.evaluators;

import parallel.GOL;
import parallel.evaluators.interfaces.IBottomNeighborLifeEvaluator;

public class BottomNeighborLifeEvaluator implements IBottomNeighborLifeEvaluator {
    @Override
    public boolean isBottomLeftNeighborAlive(int col, int row) {
        col = col == 0 ? (GOL.matrix.length - 1) : (col - 1);
        row = rowAdjuster(row);
        return GOL.matrix[col][row] == 1;
    }

    @Override
    public boolean isBottomCenterNeighborAlive(int col, int row) {
        row = rowAdjuster(row);
        return GOL.matrix[col][row] == 1;
    }

    @Override
    public boolean isBottomRightNeighborAlive(int col, int row) {
        col = col == (GOL.matrix.length - 1) ? 0 : (col + 1);
        row = rowAdjuster(row);
        return GOL.matrix[col][row] == 1;
    }

    private int rowAdjuster(int row){
        return row == (GOL.matrix.length - 1) ? 0 : (row + 1);
    }
}
