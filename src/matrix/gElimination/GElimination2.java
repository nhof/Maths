package matrix.gElimination;

import matrix.IGElim;
import matrix.Matrix;

public class GElimination2 implements IGElim {
	@Override
	public Matrix gElimination(Matrix solver) {
		int n0 = solver.nDim();
		int m0 = solver.mDim();
		int n = Math.min(n0, m0);
		boolean invertible = true;
		for (int j = 0; j < n; j++) {
			for(int underDiag = j+1; solver.getValue(j, j) == 0 && underDiag<n0 ;underDiag++){
				if (solver.getValue(underDiag, j) != 0) {
					solver = solver.switchRows(underDiag, j);
				}
			}
			if (solver.getValue(j, j) != 0) {
				solver = solver.multiplyRow(j, 1 / solver.getValue(j, j));
				for (int i = 0; i < n0; i++) {
					if (i != j) {
						solver = solver.addTimesRow(j, i, -solver.getValue(i, j));
					}
				}
			} else {
				invertible = false;
			}
		}
		if (invertible == false) {
			System.out.println("The Matrix is not invertible!");
		}
		return solver;
	}

}
