package parallel;

public interface IPositionCalculator {
    IPositionCalculator getNextStartPosition(int col, int row, int cellsPerProcessor);

    int getColumn();

    int getRow();
}
