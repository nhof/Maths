package matrix;

import java.util.Scanner;

import matrix.det.Det1;
import matrix.scalar.Scalar1;

public class DetTool {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		MatrixContext.scalar = new Scalar1();
		MatrixContext.det = new Det1();
		
		System.out.print("n-Dimension: ");
		Scanner n = new Scanner(System.in);
		int n0 = n.nextInt();
		System.out.println(n0);
		System.out.print("m-Dimension: ");
		int m0 = n.nextInt();
		System.out.println(m0);
		double[] entries = new double[n0*m0];
		System.out.print("Your "+ n0 +"x" + m0 +" Matrix: \n");
		for(int i = 0; i<n0;i++){
			for(int j = 0; j<m0;j++){
				int entry = n.nextInt();
				System.out.println(entry);	
				entries[n0*i+j] = (double) entry;
			}
		}
		Matrix mat = new Matrix(n0,m0,entries);
		System.out.println("This is your matrix:");
		System.out.println(mat);
		System.out.println("This is the determinant:");
		System.out.println(mat.det());
	}


}
