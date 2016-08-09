package serial.evaluators;

import serial.GOL;
import serial.evaluators.interfaces.IRightNeighborLifeEvaluator;

public class RightNeighborLifeEvaluator implements IRightNeighborLifeEvaluator {
    @Override
    public boolean isRightTopNeighborAlive(int col, int row) {
        col = colAdjuster(col);
        row = row == 0 ? (GOL.matrix.length - 1) : (row - 1);
        return GOL.matrix[col][row] == 1;
    }

    @Override
    public boolean isRightCenterNeighborAlive(int col, int row) {
        col = colAdjuster(col);
        return GOL.matrix[col][row] == 1;
    }

    @Override
    public boolean isRightBottomNeighborAlive(int col, int row) {
        col = colAdjuster(col);
        row = row == (GOL.matrix.length - 1) ? 0 : (row + 1);
        return GOL.matrix[col][row] == 1;
    }

    private int colAdjuster(int col){
        return col == (GOL.matrix.length - 1) ? 0 : (col + 1);
    }
}
