package matrix;

import matrix.det.Det1;
import matrix.gElimination.GElimination1;
//import matrix.gElimination.GElimination2;
import matrix.inverse.Inverse1;
//import matrix.inverse.Inverse2;
import matrix.scalar.Scalar1;

public class MatrixContext {
	
	public static IScalar scalar;
	
	public static IInverse inverse;
	
	public static IDet det;
	
	public static IGElim gElim;
	
	public static Matrix matMult(Matrix m1, Matrix m2) {
		return scalar.matMult(m1, m2);
	}
	
	public static Matrix inverse(Matrix m) {
		return inverse.invert(m);
	}
	
	public static double det(Matrix m) {
		return det.det(m);
	}

	public static Matrix gElimination(Matrix m) {
		return gElim.gElimination(m);
	}
	
	public static void main(String... args) {
		scalar = new Scalar1();
		inverse = new Inverse1();
		det = new Det1();
		gElim = new GElimination1();
	}

	
}
