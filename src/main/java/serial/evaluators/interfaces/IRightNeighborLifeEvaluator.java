package serial.evaluators.interfaces;


public interface IRightNeighborLifeEvaluator {
    boolean isRightTopNeighborAlive(int col, int row);

    boolean isRightCenterNeighborAlive(int col, int row);

    boolean isRightBottomNeighborAlive(int col, int row);
}
