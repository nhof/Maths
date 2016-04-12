
package matrix;

import static java.lang.Math.pow;

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
			if (i < m && j < n) {
				setValue(j, i, d);
			}
			i++;
			if (i == m) {
				i = 0;
				j++;
			}
		}
	}

	public static Matrix fromRows(Vector v1, Vector... vectors) {
		Matrix mat = fromColumns(v1, vectors);
		mat = mat.transposed();
		return mat;
	}

	public static Matrix fromColumns(Vector v1, Vector... vectors) {
		int n = v1.nDim();
		Matrix mat = new Matrix(v1);
		for (Vector vec : vectors) {
			if (vec.nDim() == n) {
				mat = mat.extendColumns(vec);
			}
		}
		return mat;
	}

	public static Matrix unitMatrix(int n, int m) {
		Matrix newM = new Matrix(n, m);
		int k = Math.min(n, m);
		for (int i = 0; i < k; i++) {
			newM.setValue(i, i, 1);
		}
		return newM;
	}

	public static Matrix randMatrix(int n, int m, int range) {
		Matrix newM = new Matrix(n, m);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				newM.setValue(i, j, (int) (Math.random() * range));
			}
		}
		return newM;
	}

	public Matrix makeStochastic() {
		int n = this.nDim();
		int m = this.mDim();
		Matrix mat0 = new Matrix(n, m);
		for (int i = 0; i < n; i++) {
			double sum = 0;
			for (int j = 0; j < m; j++) {
				sum += this.getValue(i, j);
			}
			for (int j = 0; j < m; j++) {
				mat0.setValue(i, j, this.getValue(i, j) / sum);
			}
		}
		return mat0;
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int n = nDim();
		int m = mDim();
		for (int i = 0; i < n; i++) {
			sb.append(" | ");
			for (int j = 0; j < m; j++) {
				// Bis auf 4 nachkommastellen exakt darstellen
				sb.append(String.format("%+6.2f", getValue(i, j)));
				// sb.append(Math.floor(getValue(i, j) * 10000) / 10000);
				sb.append(" | ");
			}
			sb.append("\n");
		}
		sb.append("--------------------------------");
		return sb.toString();
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
			System.err.println("Those Matrices don't have compatible size!");
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
	
	public Matrix matPow(int power){
		if(power == 0){
			return unitMatrix(this.nDim(), this.nDim());
		}
		else{
			return this.matMult(this.matPow(power-1));
		}
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
		if (m != ds.length) {
			System.err.println("This row can only have " + m + " entries.");
		}
		int i = 0;
		for (double d : ds) {
			if (i < m) {
				this.setValue(n, i, d);
				i++;
			}
		}
		return this;
	}

	public Matrix setColumn(int m, double... ds) {
		int n = this.mDim();
		if (n != ds.length) {
			System.err.println("This column can only have " + n + " entries.");
		}
		int i = 0;
		for (double d : ds) {
			if (i < n) {
				this.setValue(i, m, d);
				i++;
			}
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

	public double det() {
		return MatrixContext.det(this);
	}

	public Matrix gElimination() {
		return MatrixContext.gElimination(this);
	}

	public int rank() {
		int n = Math.min(this.nDim(), this.mDim());
		Matrix diagonal = this.gElimination();
		int rank = 0;
		for (int i = 0; i < n; i++) {
			if (diagonal.getValue(i, i) != 0) {
				rank++;
			}
		}
		return rank;
	}

	public Matrix invert() {
		return MatrixContext.inverse(this);
	}

	public Vector lgsSolve(Vector v) {
		int n = this.nDim();
		if (v.nDim() != n) {
			System.err.println("The solving vector does not match this matrix!");
		}
		Matrix solver = this.extendColumns(v);
		return solver.gElimination().returnColumns(n, n).toVector();
	}

	public Matrix adjunct() {
		Matrix m1 = new Matrix(this);
		int n = this.nDim();
		int m = this.mDim();
		Matrix m0 = new Matrix(n, m);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				m0.setValue(i, j, m1.removeNM(i, j).det() * pow(-1, i + j));
				m1 = this;
			}
		}
		return m0;
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

	public Vector toVector() {
		int n = this.nDim();
		Vector v = new Vector(n);
		for (int i = 0; i < n; i++) {
			v.setCoord(i, this.getValue(i, 0));
		}
		return v;
	}
}