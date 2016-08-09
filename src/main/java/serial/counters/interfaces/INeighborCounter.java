package serial.counters.interfaces;

public interface INeighborCounter {
     int getTopNeighborCount(int col, int row);

     int getBottomNeighborCount(int col, int row);

     int getLeftNeighborCount(int col, int row);

     int getRightNeighborCount(int col, int row);
}