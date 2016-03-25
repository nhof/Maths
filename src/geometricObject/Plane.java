package geometricObject;

import java.util.ArrayList;

import matrix.Vector;

public class Plane {

	private final Vector start;
	private final ArrayList<Vector> directions ;
	
	public Plane(Vector start, ArrayList<Vector> directions){
		this.directions = directions;
		this.start = start;	
	}
	
	public Vector getStart(){
		return this.start;
	}
	
	public ArrayList<Vector> getDirections(){
		return this.directions;
	}
	
	public Vector getNormal(){
		Vector v0 = this.getDirections().get(0);
		Vector vs= this.getDirections().remove(0);
		return v0.crossP(vs);
	}
	
}
