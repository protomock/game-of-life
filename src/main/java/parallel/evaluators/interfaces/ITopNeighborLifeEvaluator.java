package parallel.evaluators.interfaces;

public interface ITopNeighborLifeEvaluator {
    boolean isTopLeftNeighborAlive(int col, int row);

    boolean isTopRightNeighborAlive(int col, int row);

    boolean isTopCenterNeighborAlive(int col, int row);
}
