package parallel.evaluators.interfaces;

public interface IBottomNeighborLifeEvaluator {

    boolean isBottomLeftNeighborAlive(int col, int row);

    boolean isBottomCenterNeighborAlive(int col, int row);

    boolean isBottomRightNeighborAlive(int col, int row);
}
