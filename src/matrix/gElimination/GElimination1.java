package matrix.gElimination;

import matrix.IGElim;
import matrix.Matrix;

public class GElimination1 implements IGElim{

	@Override
	public Matrix gElimination(Matrix solver) {
		int n0 = solver.nDim();
		int m0 = solver.mDim();
		int n = Math.min(n0, m0);
		for (int j = 0; j < n; j++) {
			int diag = j + 1;
			while (solver.getValue(j, j) == 0) {
				solver = solver.switchRows(diag, j);
				diag++;
			}
			solver = solver.multiplyRow(j, 1 / solver.getValue(j, j));
			for (int i = 0; i < n0; i++) {
				if (i != j) {
					solver = solver.addTimesRow(j, i, -solver.getValue(i, j));
				}
			}
		}
		return solver;
	}

}
