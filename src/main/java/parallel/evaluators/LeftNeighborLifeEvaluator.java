package parallel.evaluators;

import parallel.GOL;
import parallel.evaluators.interfaces.ILeftNeighborLifeEvaluator;

public class LeftNeighborLifeEvaluator implements ILeftNeighborLifeEvaluator {
    @Override
    public boolean isLeftTopNeighborAlive(int col, int row) {
        col = col == 0 ? (GOL.matrix.length - 1) : (col - 1);
        row = row == 0 ? (GOL.matrix.length - 1) : (row - 1);
        return GOL.matrix[col][row] == 1;
    }

    @Override
    public boolean isLeftCenterNeighborAlive(int col, int row) {
        col = col == 0 ? (GOL.matrix.length - 1) : (col - 1);
        return GOL.matrix[col][row] == 1;
    }

    @Override
    public boolean isLeftBottomNeighborAlive(int col, int row) {
        col = col == 0 ? (GOL.matrix.length - 1) : (col - 1);
        row = row == (GOL.matrix.length - 1) ? 0 : (row + 1);
        return GOL.matrix[col][row] == 1;
    }
}
