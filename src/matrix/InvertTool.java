package matrix;

import java.util.Scanner;

public class InvertTool {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		System.out.print("n-Dimension: ");
		Scanner n = new Scanner(System.in);
		int n0 = n.nextInt();
		System.out.println(n0);
		System.out.print("m-Dimension: ");
		int m0 = n.nextInt();
		System.out.println(m0);

		String line = n.nextLine();
		String[] values = line.split(" +");
		for (String val : values) {
			Double v = Double.parseDouble(val);
		}
	}

}
