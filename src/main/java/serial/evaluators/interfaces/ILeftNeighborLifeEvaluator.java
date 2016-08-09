package serial.evaluators.interfaces;

public interface ILeftNeighborLifeEvaluator {

    boolean isLeftTopNeighborAlive(int col, int row);

    boolean isLeftCenterNeighborAlive(int col, int row);

    boolean isLeftBottomNeighborAlive(int col, int row);
}
