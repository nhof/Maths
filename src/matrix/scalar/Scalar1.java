package matrix.scalar;

import matrix.IScalar;
import matrix.Matrix;

public class Scalar1 implements IScalar {

	@Override
	public Matrix matMult(Matrix m1, Matrix m2) {
		int n = m1.nDim();
		int m = m1.mDim();
		int mo = m2.mDim();
		if (m != mo) {
			System.err.println("Those Matrices don't have the same size!");
		}
		int p = m2.nDim();
		Matrix newM = new Matrix(n, p);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < p; j++) {
				double sum = 0;
				for (int k = 0; k < m; k++) {
					sum += m1.getValue(i, k) * m2.getValue(k, j);
				}
				newM.setValue(i, j, sum);
			}
		}
		return newM;
		
	}

}
