package matrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import matrix.det.Det1;
import matrix.gElimination.GElimination1;
import matrix.inverse.Inverse1;
import matrix.scalar.Scalar1;

public class SquareMatrixTools {
	private static Scanner n = new Scanner(System.in);
	private static List<Matrix> storage = new ArrayList<Matrix>();

	public static void help() {
		System.out.println("" + "'add': Returns the sum of this and another matrix\n"
				+ "'adjunct': Returns the adjunct of your matrix\n" + "'det': Displays the determinant of your matrix\n"
				+ "'displayLast': Displays the last entered new matrix\n"
				// + "'solveLES': Displays the solution vector of the
				// matrix with a given vector"
				+ "'invert': Returns the inverse of your matrix\n" + "'last': Returns the last entered new matrix\n"
				+ "'mult': Returns the product of this and another matrix.\n" + "'new': To enter a new matrix\n"
				+ "'newRand': Get a new random matrix to work with\n" + "'random': To get a random matrix\n"
				+ "'then': Uses the result or the last computation\n"
				+ "'this': Displays the matrix you are working on\n"
				+ "'transpose': Returns the transposed of your matrix\n" + "'end': To end this programm");
	}

	public static void inv(Matrix mat) {
		if (mat.det() <= 1.0E-10) {
			System.out.println("This matrix has no inverse. Determinant of your matrix is 0.");
		} else {
			System.out.println("This is the inverse:");
			System.out.println(mat.invert());
		}
	}

	public static Matrix mul(Matrix mat) {
		System.out.println("From which side do you want to multiply? ('left' or 'right')");
		String side = n.next();
		if ("left".equals(side) || "right".equals(side)) {

			int n0 = mat.nDim();
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
				return mat.matMult(other);
			} else if ("left".equals(side)) {
				System.out.println(other.matMult(mat));
				return other.matMult(mat);
			}
		} else {
			System.out.println("No valid form of multiplication stated.");
		}
		return mat;
	}

	public static Matrix add(Matrix mat) {
		int n0 = mat.nDim();
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
		return mat.add(otherAdd);

	}

	public static void main(String[] args) {
		MatrixContext.scalar = new Scalar1();
		MatrixContext.inverse = new Inverse1();
		MatrixContext.det = new Det1();
		MatrixContext.gElim = new GElimination1();
		Matrix mat = new Matrix(0, 0);
		Matrix compose = new Matrix(0, 0);
		Matrix last = new Matrix(0, 0);
		for (String txt = "start"; !"end".equals(txt); txt = n.next()) {
			switch (txt) {
			case "start":
				System.out.println(
						"Welcome to the MATSE-tool for square matrices\n\n" + " Type 'new' to enter a new matrix.\n"
								+ " Type 'newrand' to get a random matrix to work with.\n"
								+ " Type 'help' for more informations.\n");
				break;
			case "new":
				System.out.println("Please enter the dimension of your matrix.");
				n = new Scanner(System.in);
				int n0 = n.nextInt();
				double[] entries = new double[n0 * n0];
				System.out.print("Please state " + n0 * n0 + " entries.\n");
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
				System.out.print("Which operation do you want to execute?");
				break;

			case "newrand":
				System.out.println("Please enter the dimension of your Matrix.");
				int ndim = n.nextInt();
				System.out.println("Please enter the number of values your random numbers should take");
				int num = n.nextInt();
				mat = Matrix.randMatrix(ndim, ndim, num);
				last = new Matrix(mat);
				compose = new Matrix(mat);
				System.out.println("Here is a random matrix\n" + compose);
				break;

			case "add":
				compose = add(mat);
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
				help();
				break;

			case "invert":
				inv(mat);
				compose = mat.invert();
				break;

			case "last":
				mat = last;
				System.out.println(mat);
				break;

			case "displayLast":
				System.out.println(last);
				break;

			case "mult":
				compose = mul(mat);
				break;

			case "power":
				System.out.println("Enter an integer as potence:");
				int pow = n.nextInt();
				System.out.println(mat.matPow(pow));
				compose = mat.matPow(pow);
				break;
				
			case "random":
				System.out.println("Please enter the number of values your random numbers should take");
				int randcase = n.nextInt();
				compose = Matrix.randMatrix(mat.nDim(), mat.nDim(), randcase);
				System.out.println("Here is a random matrix\n" + compose);
				break;

			case "save":
				storage.add(compose);
				System.out.println("The last matrix is stored in the position " + (storage.size() - 1));
				break;
				
			case "show":
				System.out.println("Enter a number smaller than or equal to " + (storage.size() - 1));
				int pos = n.nextInt();
				if (storage.size() > pos) {
					System.out.println("The matirx saved in position " + pos);
					System.out.println(storage.get(pos));
				} else {
					System.out.println("The maximal value to enter is " + (storage.size() - 1));
				}
				break;
				
			case "showAll":
				System.out.println("This are all the matrices you have stored");
				if(storage.size()==0){
					System.out.println("There are no stored matrices");
				}
				for(int i =0; i< storage.size(); i++){
					System.out.println("Position " + i + ":\n"+ storage.get(i));
				}
				break;
				
			case "load":
				System.out.println("Enter a number smaller than or equal to " + (storage.size() - 1));
				int pos0 = n.nextInt();
				if (storage.size() > pos0) {
					System.out.println("The matirx saved in position " + pos0 + " is:");
					compose = storage.get(pos0);
					System.out.println(storage.get(pos0));
				} else {
					System.out.println("The maximal value to enter is " + (storage.size() - 1));
				}
				break;
				
			case "stochastic":
				System.out.println("The stochastic version of you matrix is:\n"
						+ mat.makeStochastic());
				compose = mat.makeStochastic();
				break;
			case "this":
				System.out.println("This is the matrix you are working on: ");
				System.out.println(mat);
				break;
				
			case "transpose":
				System.out.println("This is the transposed:");
				System.out.println(mat.transposed());
				compose = mat.transposed();
				break;
				
			default:
				System.out.println("Wrong input, type 'help' for a list of commands.");
				break;
			}
		}
	}
}
