package parallel;


import parallel.processors.GenerationProcessor;

public class PositionCalculator implements IPositionCalculator {

    private int nextStartColumn;
    private int nextStartRow;

    @Override
    public IPositionCalculator getNextStartPosition(int col, int row, int cellsPerProcessor) {
        int index = 0;
        int currentColumn = col;
        int currentRow = row;

        while(cellsPerProcessor != 0){
            cellsPerProcessor--;

            int lastColumn = GenerationProcessor.generationCopy[currentRow].length - 1;

            if(currentColumn == lastColumn){
                currentRow = cellsPerProcessor  != 0 || currentRow != lastColumn ? currentRow + 1 : currentRow;
                currentColumn = cellsPerProcessor != 0 || currentRow != lastColumn ? 0 : currentColumn;
            } else {
                currentColumn++;
            }
        }

        this.nextStartColumn = currentColumn;
        this.nextStartRow = currentRow;

        return this;
    }

    @Override
    public int getColumn() {
        return this.nextStartColumn;
    }

    @Override
    public int getRow() {
        return this.nextStartRow;
    }
}
