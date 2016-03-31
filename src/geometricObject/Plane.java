package geometricObject;

import java.util.ArrayList;

import matrix.Matrix;
import matrix.Vector;

public class Plane {

	private final Vector start;
	private final ArrayList<Vector> directions;

	public Plane(Vector start, ArrayList<Vector> directions) {
		this.directions = directions;
		this.start = start;
	}

	public Vector getStart() {
		return this.start;
	}

	public ArrayList<Vector> getDirections() {
		return this.directions;
	}

	public Vector getNormal() {
		Vector v0 = this.getDirections().get(0);
		Vector vs = this.getDirections().remove(0);
		return v0.crossP(vs);
	}

	public Line IntersectionPP(Plane p2) {
		Vector n1 = this.getNormal();
		Vector n2 = p2.getNormal();
		Vector dir = n1.crossP(n2);
		if (dir.getLength() == 0) {
			System.err.println("The planes are either identical or parallel");
		}
		Matrix mat;
		if (p2.getDirections().get(0).linearIndependent(this.directions.get(0), this.directions.get(1))) {
			mat = Matrix.fromColumns(p2.getDirections().get(0), this.getDirections().get(0),
					this.getDirections().get(1));
		} else {
			mat = Matrix.fromColumns(p2.getDirections().get(1), this.getDirections().get(0),
					this.getDirections().get(1));
		}
		Vector s = mat.lgsSolve((Vector)this.start.add(p2.start.times(-1)));
		return new Line(s,dir);
	}

	public Vector IntersectionPL(Line line){
		Vector v1 = line.getDirection();
		if(!v1.linearIndependent(this.directions.get(0), this.directions.get(1))){
			System.err.println("The line is parallel to or lies in the plane!");
		}
		 return Matrix.fromColumns(v1, this.getDirections().get(0),this.getDirections().get(1)).lgsSolve((Vector)this.start.add(line.getStart().times(-1)));
	}
}
