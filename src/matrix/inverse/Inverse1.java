package matrix.inverse;

import matrix.IInverse;
import matrix.Matrix;
import matrix.MatrixContext;

public class Inverse1 implements IInverse {
	
	@Override
	public Matrix invert(Matrix m) {
		int n = m.nDim();
		if (MatrixContext.det(m) == 0) {
			System.err.println("This Matrix can't be inverted");
		}
		Matrix solver = m.extendColumns(Matrix.unitMatrix(n, n));
		return solver.gElimination(solver).returnColumns(n, 2 * n - 1);
	}

}
