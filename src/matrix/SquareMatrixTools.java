package matrix;

import java.util.Scanner;

import matrix.det.Det1;
import matrix.gElimination.GElimination1;
import matrix.inverse.Inverse1;
import matrix.scalar.Scalar1;

public class SquareMatrixTools {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		MatrixContext.scalar = new Scalar1();
		MatrixContext.inverse = new Inverse1();
		MatrixContext.det = new Det1();
		MatrixContext.gElim = new GElimination1();
		Matrix mat = new Matrix(0, 0);
		Matrix compose = new Matrix(0, 0);
		Matrix last = new Matrix(0, 0);
		Scanner n = new Scanner(System.in);

		for (String txt = "new"; !"end".equals(txt); txt = n.next()) {
			switch (txt) {
			case "new":
				System.out.println("Please enter the dimension of your Matrix.");
				n = new Scanner(System.in);
				int n0 = n.nextInt();
				double[] entries = new double[n0 * n0];
				System.out.print("Please state " + n0 * n0 + " enrties.\n");
				for (int i = 0; i < n0; i++) {
					for (int j = 0; j < n0; j++) {
						double entry = n.nextDouble();
						entries[n0 * i + j] = entry;
					}
				}
				mat = new Matrix(n0, n0, entries);
				last = mat;
				compose = mat;
				System.out.println("This is your matrix:");
				System.out.println(mat);
				System.out.print("Which operation do you want to execute? Type 'help' for more informations.\n");
				break;

			case "adjunct":
				System.out.println("This is the adjunct:");
				System.out.println(mat.adjunct());
				compose = mat.invert();
				break;

			case "then":
				mat = compose;
				break;

			case "det":
				System.out.println("This is the determinant:");
				System.out.println(mat.det());
				break;

			case "help":
				System.out.println(""
						+ "'add': Returns the sum of this and another matrix\n"
						+ "'adjunct': Returns the adjunct of your matrix\n"
						+ "'det': Displays the determinant of your matrix\n"
						+ "'displayLast': Displays the last entered new matrix\n"
						//+ "'solveLES': Displays the solution vector of the matrix with a given vector"
						+ "'invert': Returns the inverse of your matrix\n"
						+ "'last': Returns the last entered new matrix\n"
						+ "'mult': Returns the product of this and another matrix.\n"
						+ "'new': To enter a new matrix\n"
						+ "'then': Uses the result or the last computation\n"
						+ "'this': Displays the matrix you are working on"
						+ "'transpose': Returns the transposed of your matrix\n"
						+ "'end': To end this programm");
				break;

			case "invert":
				if (mat.det() == 0) {
					System.out.println("This matrix has no inverse. Determinant of your matrix is 0.");
				} else {
					System.out.println("This is the inverse:");
					System.out.println(mat.invert());
					compose = mat.invert();
				}
				break;

			case "last":
				mat = last;
				System.out.println(mat);
				break;

			case "displayLast":
				System.out.println(last);
				break;

			case "mult":
				System.out.println("From which side do you want to multiply? ('left' or 'right')");
				String side = n.next();
				n0 = mat.nDim();
				double[] entries1 = new double[n0 * n0];
				System.out.print("Please state " + n0 * n0 + " enrties. For the matrix to multiply with\n");
				for (int i = 0; i < n0; i++) {
					for (int j = 0; j < n0; j++) {
						double entry = n.nextDouble();
						entries1[n0 * i + j] = entry;
					}
				}
				Matrix other = new Matrix(n0, n0, entries1);
				if ("right".equals(side)) {
					System.out.println(mat.matMult(other));
					compose = mat.matMult(other);
				} else if ("left".equals(side)) {
					System.out.println(other.matMult(mat));
					compose = other.matMult(mat);
				} else {
					System.out.println("No valid form of multiplication stated.");
				}
				break;
			
			case "this":
				System.out.println("This is the matrix you are working on:");
				System.out.println(mat);
				break;
			case "transpose":
				System.out.println("This is the transposed:");
				System.out.println(mat.transposed());
				compose = mat.transposed();
				break;

//			case "solveLES":
//				int ndim = mat.nDim();
//				System.out.println("Please enter " + ndim + "values for the solution vector");
//				Vector solver = new Vector(ndim);
//				for (int i = 0; i < ndim; i++) {
//					solver.setCoord(i, n.nextDouble());
//				}
//				System.out.println("This is the solution of the linear equation system");
//				System.out.println(mat.lgsSolve(solver));
//				break;
				
			case "add":
				n0 = mat.nDim();
				double[] entries2 = new double[n0 * n0];
				System.out.print("Please state " + n0 * n0 + " enrties. For the matrix you want to add\n");
				for (int i = 0; i < n0; i++) {
					for (int j = 0; j < n0; j++) {
						double entry = n.nextDouble();
						entries2[n0 * i + j] = entry;
					}
				}
				Matrix otherAdd = new Matrix(n0, n0, entries2);
					System.out.println(mat.add(otherAdd));
					compose = mat.add(otherAdd);
				break;

			default:
				System.out.println("Wrong input, type 'help' for a list of commands.");
				break;
			}
		}
	}
}
