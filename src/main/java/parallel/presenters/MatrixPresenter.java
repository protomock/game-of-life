package parallel.presenters;

import parallel.GOL;
import parallel.presenters.interfaces.IMatrixPresenter;

public class MatrixPresenter implements IMatrixPresenter {
    @Override
    public void present() {
        for (int x = 0; x < GOL.matrix.length; x++) {
            for (int y = 0; y < GOL.matrix[x].length; y++) {
                System.out.print(GOL.matrix[x][y] + " ");
            }
            System.out.print("\n");
        }
        System.out.println("");
    }
}
