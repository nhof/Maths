package matrix;

public class Vector extends Matrix {

	public Vector(int nDim) {
		super(nDim, 1);
	}

	public Vector(int nDim, double... ds) {
		this(nDim);
		int n = ds.length;
		if (nDim < n) {
			System.err.println("This counld only have " + nDim + " entries.");
		}
		int i = 0;
		for (double d : ds) {
			if (i < nDim) {
				this.setCoord(i, d);
			}
			i++;
		}
	}

	public static Vector unitVector(int dim, int pos) {
		Vector v = new Vector(dim);
		if (pos < dim) {
			v.setCoord(pos, 1);
			return v;
		}
		System.err.println("There are not enough dimensions.");
		return v;

	}

	public double getCoord(int n) {
		if (n < this.nDim()) {
			return this.getValue(n, 0);
		}
		return 0;

	}

	public void setCoord(int n, double value) {
		this.setValue(n, 0, value);
	}

	public double scalarP(Vector vec) {
		int n = this.nDim();
		double sum = 0;
		for (int i = 0; i < n; i++) {
			sum += this.getCoord(i) * vec.getCoord(i);
		}
		return sum;
	}

	public double scalarP2(Vector vec) {
		return this.transposed().matMult(vec).getValue(0, 0);
	}

	public Vector crossP(Vector other) {
		int n = this.nDim();
		if(n!=3 || other.nDim()!=3){
			System.out.println("This only works for 3 dimensions!");
		}
		Vector v = new Vector(3);
		v.setCoord(0, this.getCoord(1)*other.getCoord(2)-this.getCoord(2)*other.getCoord(1));
		v.setCoord(1, this.getCoord(2)*other.getCoord(0)-this.getCoord(0)*other.getCoord(2));
		v.setCoord(2, this.getCoord(0)*other.getCoord(1)-this.getCoord(1)*other.getCoord(0));
		return v;
	}

	public double getLength() {
		int n = this.nDim();
		double sum = 0;
		for (int i = 0; i < n; i++) {
			sum += Math.pow(this.getCoord(i), 2);
		}
		return Math.sqrt(sum);
	}

	public Vector getNormal() {
		double l = this.getLength();
		if (l == 0) {
			System.err.println("this vector is the Nullvector and can't be normed");
		}
		int n = this.nDim();
		Vector v = this;
		for (int i = 0; i < n; i++) {
			v.setCoord(i, this.getCoord(i) / l);
		}
		return v;
	}

	public double angleV(Vector vec) {
		return Math.acos(this.scalarP(vec) /(this.getLength() * vec.getLength()));
	}

	public boolean linearIndependent(Vector... vectors) {
		Matrix mat = Matrix.fromColumns(this, vectors);
		return mat.rank() == vectors.length+1;
	}
}
