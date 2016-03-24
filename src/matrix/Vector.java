package matrix;

public class Vector extends Matrix {

	public Vector(int nDim) {
		super(nDim, 0);
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
		return this.transposed().multiplication(vec).getValue(0, 0);
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
	
	public double lengthV(){
		int n = this.nDim();
		double sum = 0;
		for(int i = 0;i<n;i++){
			sum += Math.pow(this.getCoord(i),2);
		}
		return Math.sqrt(sum);
	}
	
	public double angleV(Vector vec){
		double x = this.skalarP(vec)/(this.lengthV()*vec.lengthV());
		return Math.acos(x);
	}
}
