
package matrix;

import java.util.*;

public class Matrix {
	private final List<List<Double>> values;
	
	public Matrix(int nDim, int mDim) {
		values = new ArrayList<>(nDim);
		for (int i = 0; i < nDim; i++) {
			List<Double> vector = new ArrayList<>(mDim);
			for (int j = 0; j < mDim; j++) {
				vector.add(0.0);
			}
			values.add(vector);
		}
	}

	public Matrix(Matrix matrix) {
		values = new ArrayList<>(matrix.nDim());
		for (int i = 0; i < matrix.nDim(); i++) {
			List<Double> vector = new ArrayList<>(matrix.mDim());
			for (int j = 0; j < matrix.mDim(); j++) {
				vector.add(matrix.getValue(i, j));
			}
			values.add(vector);
		}
	}

	public Matrix(int n, int m, double... ds) {
		this(n, m);
		if (ds.length > n * m) {
			System.err.println("There can only be " + n * m + " entries in the list.");
		}
		int i = 0;
		int j = 0;
		for (double d : ds) {
			setValue(j, i, d);
			i++;
			if (i == m) {
				i = 0;
				j++;
			}
		}
	}

	public static void printM(Matrix mat) {
		int n = mat.nDim();
		int m = mat.mDim();
		for (int i = 0; i < n; i++) {
			System.out.print(" | ");
			for (int j = 0; j < m; j++) {
				// Bis auf 4 nachkommastellen exakt darstellen
				System.out.print(Math.floor(mat.getValue(i, j) * 10000) / 10000 + " | ");
			}
			System.out.println("\n");
		}
		System.out.println("--------------------------------");
	}

	public static Matrix unitMatrix(int n, int m) {
		Matrix newM = new Matrix(n, m);
		int k = Math.min(n, m);
		for (int i = 0; i < k; i++) {
			newM.setValue(i, i, 1);
		}
		return newM;
	}
	

	public double getValue(int n, int m) {
		return this.values.get(n).get(m);
	}

	public void setValue(int n, int m, double value) {
		this.values.get(n).set(m, value);
	}

	public Matrix add(Matrix otherM) {
		int n = this.nDim();
		int m = this.mDim();
		Matrix newM = new Matrix(n, m);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				newM.setValue(i, j, this.getValue(i, j) + otherM.getValue(i, j));
			}
		}
		return newM;
	}

	public Matrix times(double factor) {
		int n = this.nDim();
		int m = this.mDim();
		Matrix newM = new Matrix(n, m);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				newM.setValue(i, j, factor * this.getValue(i, j));
			}
		}
		return newM;
	}

	public int nDim() {
		return this.values.size();
	}

	public int mDim() {
		return this.values.get(0).size();
	}

	public Matrix matMult(Matrix otherM) {
		int n = this.nDim();
		int m = this.mDim();
		int mo = otherM.values.size();
		if (m != mo) {
			System.err.println("Those Matrices don't have the same size!");
		}
		int p = otherM.values.get(0).size();
		Matrix newM = new Matrix(n, p);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < p; j++) {
				double sum = 0;
				for (int k = 0; k < m; k++) {
					sum += this.getValue(i, k) * otherM.getValue(k, j);
				}
				newM.setValue(i, j, sum);
			}
		}
		return newM;
	}

	public Matrix transposed() {
		int n = this.nDim();
		int m = this.mDim();
		Matrix newM = new Matrix(m, n);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				newM.setValue(j, i, this.getValue(i, j));
			}
		}
		return newM;
	}

	public Matrix removeRow(int n0) {
		int n = this.mDim();
		if (n0 > n) {
			System.err.println("The Matrix has not enought rows or coulmns");
		}
		Matrix newM = new Matrix(this);
		newM.values.remove(n0);
		return newM;
	}

	public Matrix removeColumn(int m0) {
		int n = this.nDim();
		int m = this.mDim();
		Matrix newM = new Matrix(this);
		if (m0 > m) {
			System.err.println("The Matrix has not enought rows or coulmns");
		}
		for (int i = 0; i < n; i++) {
			newM.values.get(i).remove(m0);
		}
		return newM;
	}

	public Matrix removeNM(int n, int m) {
		Matrix newM = this.removeRow(n).removeColumn(m);
		return newM;
	}

	public Matrix scalMult(double scalar) {
		int n = this.nDim();
		int m = this.mDim();
		Matrix newM = new Matrix(this);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				newM.setValue(i, j, scalar * this.getValue(i, j));
			}
		}
		return newM;
	}

	public double det() {
		double sum = 0;

		int n = this.nDim();
		if (n != this.mDim()) {
			System.err.println("This is no sqare Matrix");
		}
		if (n == 1) {
			return this.getValue(0, 0);
		}
		for (int j = 0; j < n; j++) {
			// Entwicklung nach der 1. Zeile
			sum += (Math.pow(-1, j)) * this.removeNM(0, j).det() * this.getValue(0, j);
		}
		return sum;
	}

	public Matrix extendColumns(Matrix other) {
		int n = this.nDim();
		int m0 = other.mDim();
		Matrix newM = new Matrix(this);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m0; j++) {
				newM.values.get(i).add(other.values.get(i).get(j));
			}
		}
		return newM;
	}

	public Matrix extendRows(Matrix other) {
		int n0 = other.nDim();
		Matrix newM = new Matrix(this);
		for (int i = 0; i < n0; i++) {
			newM.values.add(other.values.get(i));
		}
		return newM;
	}


	public Matrix multiplyRow(int n, double factor) {
		int m = this.mDim();
		Matrix newM = new Matrix(this);
		for (int i = 0; i < m; i++) {
			newM.setValue(n, i, newM.getValue(n, i) * factor);
		}
		return newM;
	}

	public Matrix addRow(int n, int toN) {
		int m = this.mDim();
		Matrix newM = new Matrix(this);
		for (int i = 0; i < m; i++) {
			newM.setValue(toN, i, newM.getValue(n, i) + newM.getValue(toN, i));
		}
		return newM;
	}

	public Matrix setRow(int n, double... ds) {
		int m = this.mDim();
		if (m > ds.length) {
			System.err.println("This row can only have " + m + " entries.");
		}
		int i = 0;
		for (double d : ds) {
			this.setValue(n, i, d);
			i++;
		}
		return this;
	}

	public Matrix setColumn(int m, double... ds) {
		int n = this.mDim();
		if (n > ds.length) {
			System.err.println("This column can only have " + n + " entries.");
		}
		int i = 0;
		for (double d : ds) {
			this.setValue(i, m, d);
			i++;
		}
		return this;
	}

	public Matrix addTimesRow(int n, int toN, double factor) {
		int m = this.mDim();
		Matrix newM = new Matrix(this);
		for (int i = 0; i < m; i++) {
			newM.setValue(toN, i, newM.getValue(toN, i) + factor * newM.getValue(n, i));
		}
		return newM;
	}

	public Matrix switchRows(int fromN, int toN) {
		int m = this.mDim();
		Matrix newM = new Matrix(this);
		for (int i = 0; i < m; i++) {
			newM.setValue(fromN, i, this.getValue(toN, i));
			newM.setValue(toN, i, this.getValue(fromN, i));
		}
		return newM;
	}

	public Matrix returnRows(int fromN, int toN) {
		Matrix nil = new Matrix(0, 0);
		for (int j = fromN; j < toN + 1; j++) {
			nil.values.add(this.values.get(j));
		}
		return nil;
	}

	public Matrix returnColumns(int fromM, int toM) {
		Matrix nil = new Matrix(0, 0);
		int n = this.nDim();
		for (int i = 0; i < n; i++) {
			List<Double> vector = new ArrayList<>(n);
			for (int j = fromM; j < toM + 1; j++) {
				vector.add(this.getValue(i, j));
			}
			nil.values.add(vector);
		}
		return nil;
	}

	public Matrix gElimination(Matrix solver) {
		int n0 = solver.nDim();
		int m0 = solver.mDim();
		int n = Math.min(n0, m0);
		for (int j = 0; j < n; j++) {
			int diag = j + 1;
			while (solver.getValue(j, j) == 0) {
				solver = solver.switchRows(diag, j);
				diag++;
			}
			solver = solver.multiplyRow(j, 1 / solver.getValue(j, j));
			for (int i = 0; i < n0; i++) {
				if (i != j) {
					solver = solver.addTimesRow(j, i, -solver.getValue(i, j));
				}
			}
		}
		return solver;
	}

	public Matrix invert() {
		return MatrixContext.inverse(this);
		// int n = this.nDim();
		// if (this.det() == 0) {
		// System.err.println("This Matrix can't be inverted");
		// }
		// Matrix solver = this.extendColumns(unitMatrix(n, n));
		// return solver.gElimination(solver).returnColumns(n, 2 * n - 1);
	}

	public Vector lgsSolve(Vector v) {
		int n = this.nDim();
		if (v.nDim() != n) {
			System.err.println("The solving vector does not match this matrix!");
		}
		Matrix solver = this.extendColumns(v);
		return (Vector) solver.gElimination(solver).returnColumns(n, n);
	}

	public static Matrix fromRows(Vector v1, Vector... vectors) {
		int n = v1.nDim();
		Matrix mat = new Matrix(v1);
		for (Vector vec : vectors) {
			if (vec.nDim() == n) {
				mat.extendRows(vec);
			}
		}
		return mat;
	}

	public static Matrix fromColumns(Vector v1, Vector... vectors) {
		int n = v1.nDim();
		Matrix mat = new Matrix(v1);
		for (Vector vec : vectors) {
			if (vec.nDim() == n) {
				mat.extendColumns(vec);
			}
		}
		return mat;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int n = nDim();
		int m = mDim();
		for (int i = 0; i < n; i++) {
			sb.append(" | ");
			for (int j = 0; j < m; j++) {
				// Bis auf 4 nachkommastellen exakt darstellen
				sb.append(Math.floor(getValue(i, j) * 10000) / 10000);
				sb.append(" | ");
			}
			sb.append("\n");
		}
		sb.append("--------------------------------");
		return sb.toString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Matrix mat = (Matrix) obj;
		if (!Objects.equals(mat.values, values))
			return false;
		return true;
	}

	public boolean nearly(Matrix other, double accuracy) {
		int n = this.nDim();
		int m = this.mDim();
		if (n != other.nDim() || m != other.mDim()) {
			return false;
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (this.getValue(i, j) > other.getValue(i, j) + accuracy
						|| this.getValue(i, j) < other.getValue(i, j) - accuracy) {
					return false;
				}
				;
			}
		}
		return true;
	}
}