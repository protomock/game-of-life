package parallel;

public interface IPartialProcess {
    IPartialProcess setStartPosition(int col, int row);

    void runFor(int numberOfCells);

}
