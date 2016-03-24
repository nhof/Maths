package geometricObject;

import matrix.*;

public class Line {

	private final Vector start;
	private final Vector direction;
	public Line(Vector start, Vector direction){
		this.direction = direction;
		this.start = start;	
	}
	
	public Vector intersectionLL(Line otherL){
		Vector constants =(Vector) this.start.add(otherL.start.times(-1));
		this.direction.extendColumns(otherL.direction).extendColumns(constants);
		Matrix mat = Matrix.fromColumns((Vector)this.direction.times(-1), otherL.direction);
		return mat.lgsSolve(constants);
	}
}
