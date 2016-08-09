package parallel.providers;

import parallel.GOL;
import parallel.providers.interfaces.IMatrixInformationProvider;

public class MatrixInformationProvider implements IMatrixInformationProvider {
    @Override
    public int getMatrixSize() {
        return GOL.matrix.length * GOL.matrix[0].length;
    }
}
