package matrix.inverse;

import matrix.IInverse;
import matrix.Matrix;
import matrix.MatrixContext;

public class Inverse2 implements IInverse {
	@Override
	public Matrix invert(Matrix m0) {
		Matrix m = m0.adjunct().transposed();
		double d = MatrixContext.det(m0);
		if ( d == 0) {
			System.err.println("This Matrix can't be inverted");
		}
		return m.times(1/d);
	}
}
