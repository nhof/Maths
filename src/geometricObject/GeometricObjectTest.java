package geometricObject;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import matrix.MatrixContext;
import matrix.Vector;

public class GeometricObjectTest {

	//intersection with testLine3
	//parallel to testLine2
	public static Line testLine1(){
		Vector start = new Vector(3,1,0,0);
		Vector dir = new Vector(3,0,1,0);
		return new Line(start, dir);
	}
	
	public static Line testLine2(){
		Vector start = new Vector(3,1,2,3);
		Vector dir = new Vector(3,0,1,0);
		return new Line(start, dir);
	}
	
	public static Line testLine3(){
		Vector start = new Vector(3,2,3,0);
		Vector dir = new Vector(3,1,2,0);
		return new Line(start, dir);
	}
	
	@Before
	public void setup() {
		MatrixContext.main();
	}
	
	@Test
	public void nDimTest(){
		assertEquals(3, testLine2().nDim());
	}
	
	@Test
	public void getStartTest(){
		Vector start = new Vector(3,1,0,0);
		assertTrue("not same", start.equals(testLine1().getStart()) );
	}
	
	@Test
	public void getDirectionTest(){
		Vector direction = new Vector(3,0,1,0);
		assertTrue("not same", direction.equals(testLine1().getDirection()));
	}
	
	@Test
	public void onLineTest1(){
		Vector p = new Vector(3,1,5,0);
		assertTrue("not same", testLine1().hasPoint(p));
	}
	
	@Test
	public void onLineTest2(){
		Vector p = new Vector(3,2,5,0);
		assertFalse("not same", testLine1().hasPoint(p));
	}
	
	//TODO: continue testing!
//	@Test
//	public void doesIntersectLLTest1(){
//		assertTrue("not same", testLine1().doesIntersectLL(testLine3()));
//	}
//	
//	@Test
//	public void doesIntersectLLTest2(){
//		assertFalse("not same",testLine1().doesIntersectLL(testLine2()));
//	}
}
