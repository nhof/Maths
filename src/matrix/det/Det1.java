package matrix.det;

import matrix.IDet;
import matrix.Matrix;

public class Det1 implements IDet{

	@Override
	public double det(Matrix m){
		double sum = 0;

		int n = m.nDim();
		if (n != m.mDim()) {
			System.err.println("This is no sqare Matrix");
		}
		if (n == 1) {
			return m.getValue(0, 0);
		}
		for (int j = 0; j < n; j++) {
			// Entwicklung nach der 1. Zeile
			sum += (Math.pow(-1, j)) * m.removeNM(0, j).det() * m.getValue(0, j);
		}
		return sum;
	}
}
