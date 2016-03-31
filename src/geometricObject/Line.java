package geometricObject;

import matrix.*;

public class Line {

	private final Vector start;
	private final Vector direction;
	public Line(Vector start, Vector direction){
		this.direction = direction;
		this.start = start;	
	}
	
	public Vector getDirection(){
		return this.direction;
	}
	
	public Vector getStart(){
		return this.start;
	}
	
	public Vector intersectionLL(Line otherL){
		//to check where two Lines f=lambda*v+s and g=mue*u+t intersect
		//f-g <=> s-v = mue*u-lambda*v
		Vector constants =(Vector) this.start.add(otherL.start.times(-1));
		this.direction.extendColumns(otherL.direction).extendColumns(constants);
		Matrix mat = Matrix.fromColumns((Vector)this.direction.times(-1), otherL.direction);
		return mat.lgsSolve(constants);
	}
}
