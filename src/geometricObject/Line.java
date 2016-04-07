package geometricObject;

import matrix.*;

public class Line {

	private final Vector start;
	private final Vector direction;
	public Line(Vector start, Vector direction){
		this.direction = direction;
		this.start = start;
		if(this.direction.nDim() != this.start.nDim()){
			System.err.println("Invalid construction, start and direction vector have to have the same dimension");
		}
	}
	
	public int nDim(){
		return this.getStart().nDim();
	}
	
	public Vector getDirection(){
		return this.direction;
	}
	
	public Vector getStart(){
		return this.start;
	}
	
	public boolean hasPoint(Vector v0){
		return !this.getDirection().linearIndependent(this.getStart().add(v0.times(-1)).toVector());
	}
	
	public boolean doesIntersectLL(Line otherL){
		Vector constants = this.getStart().add(otherL.getStart().times(-1)).toVector();
		return !constants.linearIndependent(this.getDirection().times(-1).toVector(), otherL.getDirection());
	}
	
	public Vector intersectionLL(Line otherL){
		//to check where two Lines f=lambda*v+s and g=mue*u+t intersect
		//f-g <=> s-v = mue*u-lambda*v
		Vector constants = this.getStart().add(otherL.getStart().times(-1)).toVector();
		this.getDirection().extendColumns(otherL.getDirection()).extendColumns(constants);
		Matrix mat = Matrix.fromColumns(this.getDirection().times(-1).toVector(), otherL.getDirection());
		return mat.lgsSolve(constants);
	}
}
