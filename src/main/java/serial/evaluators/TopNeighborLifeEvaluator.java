package serial.evaluators;

import dagger.Module;
import serial.GOL;
import serial.evaluators.interfaces.ITopNeighborLifeEvaluator;

public class TopNeighborLifeEvaluator implements ITopNeighborLifeEvaluator {
    @Override
    public boolean isTopLeftNeighborAlive(int col, int row) {
        col = colAdjuster(col);
        row = row == 0 ? (GOL.matrix.length - 1) : (row - 1);

        return GOL.matrix[col][row] == 1;
    }

    @Override
    public boolean isTopRightNeighborAlive(int col, int row) {
        col = colAdjuster(col);
        row = row == (GOL.matrix.length - 1) ? 0 : (row + 1);
        return GOL.matrix[col][row] == 1;
    }

    @Override
    public boolean isTopCenterNeighborAlive(int col, int row) {
        col = colAdjuster(col);
        return GOL.matrix[col][row] == 1;
    }

    private int colAdjuster(int col){
        return col == 0 ? (GOL.matrix.length - 1) : (col - 1);
    }
}
