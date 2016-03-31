package matrix;

public class Vector extends Matrix {

	public Vector(int nDim) {
		super(nDim, 1);
	}
	
	public Vector unitVector(int dim, int pos){
		Vector v = new Vector(dim);
		v.setCoord(pos, 1);
		return v;
	}
	
	public double getCoord(int n){
		return this.getValue(n, 0);
	}
	
	public void setCoord(int n, double value){
		this.setValue(n, 0, value);
	}
	
	public double skalarP(Vector vec){
		int n = this.nDim();
		double sum = 0;
		for(int i = 0; i <n; i++){
			sum += this.getCoord(i)*vec.getCoord(n);
		}
		return sum;
	}
	
	public double skalarP2(Vector vec){
		return this.transposed().matMult(vec).getValue(0, 0);
	}
	
	public Vector crossP(Vector...vectors){
		int n = this.nDim();
		if(vectors.length != n-2){
			System.err.println("This Method requires " + n+-2 + " Arguments with dimension "+ n);
		}
		Matrix mat = fromRows(this,vectors);
		Vector v = new Vector(n); 
		for(int i = 0;i<n;i++){
			v.setCoord(i,(Math.pow(-1, i))*mat.removeRow(i).det() );
		}
		return v;
	}
	
	public double getLength(){
		int n = this.nDim();
		double sum = 0;
		for(int i = 0;i<n;i++){
			sum += Math.pow(this.getCoord(i),2);
		}
		return Math.sqrt(sum);
	}
	
	public Vector getNormal(){
		double l = this.getLength();
		if(l == 0){System.err.println("this vector is the Nullvector and can't be normed");}
		int n = this.nDim();
		Vector v = this;
		for(int i = 0; i<n ; i++){
			v.setCoord(i, this.getCoord(i)/l);
		}
		return v;
	}
	
	public double angleV(Vector vec){
		double x = this.skalarP(vec)/(this.getLength()*vec.getLength());
		return Math.acos(x);
	}
	
	public boolean linearIndependent(Vector...vectors){
		Matrix mat = Matrix.fromColumns(this, vectors);
		int n = Math.min(mat.nDim(), mat.mDim());
		return mat.rank()==n;
	}
}
