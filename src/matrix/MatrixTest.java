package matrix;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MatrixTest {

	public static Matrix ex3x3_simple() {
		Matrix ex = new Matrix(3, 3);
		ex.setValue(0, 0, 1);
		ex.setValue(1, 1, 2);
		ex.setValue(2, 2, 4);
		return ex;
	}

	@Before
	public void setup() {
		MatrixContext.main();
	}

	@Test
	public void equalsTest() {
		Matrix other = new Matrix(ex3x3_simple());
		assertEquals("equals", ex3x3_simple(), other);
	}

	@Test
	public void setValueTest() {
		Matrix mat = new Matrix(3, 3);
		mat.setValue(1, 2, 2);
		assertEquals("not same", 2, mat.getValue(1, 2), 0.0);
	}

	@Test
	public void addTest() {
		Matrix m0 = new Matrix(3, 3, 1, 2, 3, 4, 5, 6, 7, 8, 9);
		Matrix m1 = new Matrix(3, 3, 8, 7, 6, 5, 4, 3, 2, 1, 0);
		Matrix m2 = new Matrix(3, 3, 9, 9, 9, 9, 9, 9, 9, 9, 9);
		assertTrue("not same", m2.equals(m0.add(m1)));
	}

	@Test
	public void multTest() {
		Matrix m0 = new Matrix(3, 2, 1, 1, 1, 1, 1, 1);
		Matrix m1 = new Matrix(2, 3, 1, 1, 1, 1, 1, 1);
		Matrix m2 = new Matrix(3, 3, 2, 2, 2, 2, 2, 2, 2, 2, 2);
		assertTrue("not same", m2.equals(m0.matMult(m1)));
	}

	@Test
	public void transposeTest() {
		Matrix m0 = new Matrix(3, 3, 1, 2, 3, 4, 5, 6, 7, 8, 9);
		Matrix m1 = new Matrix(3, 3, 1, 4, 7, 2, 5, 8, 3, 6, 9);
		assertTrue("not same", m1.equals(m0.transposed()));
	}

	@Test
	public void removeColumnTest() {
		Matrix m0 = new Matrix(3, 3, 1, 2, 3, 4, 5, 6, 7, 8, 9);
		Matrix m1 = new Matrix(3, 2, 1, 3, 4, 6, 7, 9);
		assertTrue("not same", m1.equals(m0.removeColumn(1)));
	}

	@Test
	public void removeRowTest() {
		Matrix m0 = new Matrix(3, 3, 1, 2, 3, 4, 5, 6, 7, 8, 9);
		Matrix m1 = new Matrix(2, 3, 1, 2, 3, 7, 8, 9);
		assertTrue("not same", m1.equals(m0.removeRow(1)));
	}

	@Test
	public void removeNMTest() {
		Matrix m0 = new Matrix(4, 4, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
		Matrix m1 = new Matrix(3, 3, 6, 7, 8, 10, 11, 12, 14, 15, 16);
		assertTrue("not same", m1.equals(m0.removeNM(0, 0)));
	}

	@Test
	public void detTest() {
		Matrix m0 = new Matrix(3, 3, 0, 1, 2, 3, 4, 5, 6, 7, 8);
		assertEquals("not same", 0, m0.det(), 0.0);
	}

	@Test
	public void extendColumnsTest() {
		Matrix m0 = new Matrix(2, 2, 1, 2, 3, 4);
		Matrix m1 = new Matrix(2, 4, 1, 2, 1, 2, 3, 4, 3, 4);
		assertTrue("not same", m1.equals(m0.extendColumns(m0)));
	}

	@Test
	public void extendRowsTest() {
		Matrix m0 = new Matrix(2, 2, 1, 2, 3, 4);
		Matrix m1 = new Matrix(4, 2, 1, 2, 3, 4, 1, 2, 3, 4);
		assertTrue("not same", m1.equals(m0.extendRows(m0)));
	}

	@Test
	public void multiplyRowTest() {
		Matrix m0 = new Matrix(3, 3, 0, 1, 2, 3, 4, 5, 6, 7, 8);
		Matrix m1 = new Matrix(3, 3, 0, 1, 2, 6, 8, 10, 6, 7, 8);
		assertTrue("not same", m1.equals(m0.multiplyRow(1, 2)));
	}

	@Test
	public void addRowTest() {
		Matrix m0 = new Matrix(3, 3, 0, 1, 2, 3, 4, 5, 6, 7, 8);
		Matrix m1 = new Matrix(3, 3, 0, 1, 2, 3, 5, 7, 6, 7, 8);
		assertTrue("not same", m1.equals(m0.addRow(0, 1)));
	}

	@Test
	public void addTimesRowTest() {
		Matrix m0 = new Matrix(3, 3, 0, 1, 2, 3, 4, 5, 6, 7, 8);
		Matrix m1 = new Matrix(3, 3, 0, 1, 2, 3, 3, 3, 6, 7, 8);
		assertTrue("not same", m1.equals(m0.addTimesRow(0, 1, -1)));
	}

	@Test
	public void switchRowsTest() {
		Matrix m0 = new Matrix(3, 3, 0, 1, 2, 3, 4, 5, 6, 7, 8);
		Matrix m1 = new Matrix(3, 3, 3, 4, 5, 0, 1, 2, 6, 7, 8);
		assertTrue("not same", m1.equals(m0.switchRows(0, 1)));
	}

	@Test
	public void returnRowsTest() {
		Matrix m0 = new Matrix(3, 3, 0, 1, 2, 3, 4, 5, 6, 7, 8);
		Matrix m1 = new Matrix(1, 3, 6, 7, 8);
		assertTrue("not same", m1.equals(m0.returnRows(2, 2)));
	}

	@Test
	public void returnColTest() {
		Matrix m0 = new Matrix(3, 3, 0, 1, 2, 3, 4, 5, 6, 7, 8);
		Matrix m1 = new Matrix(3, 1, 2, 5, 8);
		assertTrue("not same", m1.equals(m0.returnColumns(2, 2)));
	}

	@Test
	public void nearlyTest() {
		Matrix m0 = new Matrix(3, 3, 0, 1, 2, 3, 4, 5, 6, 7, 8);
		Matrix m1 = new Matrix(3, 3, 0, 1.001, 2.001, 3, 4, 5, 6, 7, 8);
		assertFalse("should not be same", m1.equals(m0));
		assertTrue("not same", m1.nearly(m1, 0.1));
	}

	@Test
	public void invertTest() {
		Matrix m0 = new Matrix(3, 3, 2, -1, 3, 7, 3, 0, -1, 2, -4);
		Matrix m1 = new Matrix(3, 3, 12, -2, 9, -28, 5, -21, -17, 3, -13);
		// System.out.println(m0.invert());
		// System.out.println(m1);
		assertTrue("not same", m1.nearly(m0.invert(), 0.1));
	}

	@Test
	public void timesTest() {
		Matrix m0 = new Matrix(3, 3, 2, -1, 3, 7, 3, 0, -1, 2, -4);
		Matrix m1 = new Matrix(3, 3, 4, -2, 6, 14, 6, 0, -2, 4, -8);
		assertTrue("not same", m1.equals(m0.times(2)));
	}

	@Test
	public void gElimTest() {
		Matrix m0 = new Matrix(3, 3, 1, 2, 3, 4, 5, 6, 7, 9, 10);
		Matrix m1 = Matrix.unitMatrix(3, 3);
		assertTrue("not same", m1.nearly(m0.gElimination(), 0.1));
	}

	@Test
	public void gElimTest2() {
		Matrix m0 = new Matrix(3, 3, 0, 2, 3, 4, 5, 6, 7, 8, 9);
		Matrix m1 = Matrix.unitMatrix(3, 3);
		assertTrue("not same", m1.nearly(m0.gElimination(), 0.1));
	}

	@Test
	public void gElimTest3() {
		Matrix m0 = new Matrix(3, 3, 1, -3, 1, 2, -6, 2, 0, 0, 5);
		Matrix m1 = new Matrix(3, 3, 1, -3, 0, 0, 0, 0, 0, 0, 1);
		assertTrue("not same", m1.nearly(m0.gElimination(), 0.1));
	}

	@Test
	public void setColumnTest() {
		Matrix m0 = new Matrix(3, 3);
		Matrix m1 = new Matrix(3, 3, 0, 1, 0, 0, 2, 0, 0, 3, 0);
		assertTrue("not same", m1.equals(m0.setColumn(1, 1, 2, 3, 0)));
		assertTrue("not same", m1.equals(m0.setColumn(1, 1, 2, 3)));
	}

	@Test
	public void setRowTest() {
		Matrix m0 = new Matrix(3, 3);
		Matrix m1 = new Matrix(3, 3, 0, 0, 0, 1, 2, 3, 0, 0, 0);
		assertTrue("not same", m1.equals(m0.setRow(1, 1, 2, 3, 0)));
		assertTrue("not same", m1.equals(m0.setRow(1, 1, 2, 3)));
	}

	@Test
	public void toVectorTest() {
		Matrix m0 = new Matrix(3, 3, 1, 2, 3, 4, 5, 6, 7, 8, 9);
		Vector v1 = new Vector(3, 1, 4, 7);
		assertTrue("not same", v1.equals(m0.toVector()));
	}

	@Test
	public void vecConstTest() {
		Vector v = new Vector(3);
		Vector v0 = new Vector(3, 0, 0, 0);
		assertTrue("not same", v.equals(v0));
	}

	@Test
	public void unitVectorTest() {
		Vector v0 = new Vector(3, 1, 0, 0);
		Vector v1 = Vector.unitVector(3, 0);
		assertTrue("not same", v0.equals(v1));
	}

	@Test
	public void getCoordTest() {
		Vector v0 = new Vector(3, 1, 2, 3);
		assertEquals(1, v0.getCoord(0), 0.1);
	}

	@Test
	public void getCoordTest2() {
		Vector v0 = new Vector(3, 1, 2, 3);
		assertEquals(0, v0.getCoord(3), 0.1);
	}

	@Test
	public void scalarPTest() {
		Vector v0 = new Vector(3, 1, 2, 2);
		Vector v1 = new Vector(3, 2, 1, -2);
		assertEquals(0, v0.scalarP(v1), 0.1);
	}

	@Test
	public void scalarP2Test() {
		Vector v0 = new Vector(3, 1, 2, 2);
		Vector v1 = new Vector(3, 2, 1, -2);
		assertEquals(0, v0.scalarP2(v1), 0.1);
	}

	@Test
	public void getLengthTest() {
		Vector v0 = new Vector(3, 2, 3, 6);
		assertEquals(7.0, v0.getLength(), 0.1);
	}

	@Test
	public void crossPTest() {
		Vector v0 = new Vector(3, 1, 2, 2);
		Vector v1 = new Vector(3, 2, 1, -2);
		Vector n = new Vector(3, -6, 6, -3);
		assertTrue("not same", n.equals(v0.crossP(v1)));
	}

	@Test
	public void getNormalTest() {
		Vector v0 = new Vector(3, 1, 2, 2);
		Vector n = new Vector(3, 1.0 / 3.0, 2.0 / 3.0, 2.0 / 3.0);
		assertTrue("not same", n.nearly(v0.getNormal(), 0.1));
	}

	@Test
	public void angleTest() {
		Vector v0 = new Vector(3, 1, 0, 0);
		Vector v1 = new Vector(3, 0, 1, 0);
		assertEquals(Math.PI / 2, v0.angleV(v1), 0.1);
	}

	@Test
	public void rankTest() {
		Matrix m0 = new Matrix(3, 3, 1, -3, 1, 2, -6, 2, 0, 0, 5);
		assertEquals(2, m0.rank());
	}

	@Test
	public void lgsSolve() {
		Matrix m0 = new Matrix(2, 2, 1, 1, 1, 2);
		Vector v0 = new Vector(2, 1, 2);
		Vector sol = new Vector(2, 0, 1);
		assertTrue("not same", sol.equals(m0.lgsSolve(v0)));
	}

	@Test
	public void fromColumnsTest() {
		Vector v0 = new Vector(3, 1, 0, 0);
		Vector v1 = new Vector(3, 1, 1, 2);
		Matrix m0 = new Matrix(3, 2, 1, 1, 0, 1, 0, 2);
		assertTrue("not same", m0.equals(Matrix.fromColumns(v0, v1)));
	}

	@Test
	public void fromRowsTest() {
		Vector v0 = new Vector(3, 1, 0, 0);
		Vector v1 = new Vector(3, 1, 1, 2);
		Matrix m0 = new Matrix(2, 3, 1, 0, 0, 1, 1, 2);
		assertTrue("not same", m0.equals(Matrix.fromRows(v0, v1)));
	}

	@Test
	public void linearIndepTest1() {
		Vector v0 = new Vector(3, 1, 0, 0);
		Vector v1 = new Vector(3, 1, 1, 1);
		Vector v2 = new Vector(3, 3, 2, 2);
		assertFalse("not same", v0.linearIndependent(v1, v2));
	}

	@Test
	public void linearIndepTest2() {
		Vector v0 = new Vector(3, 1, 0, 0);
		Vector v1 = new Vector(3, 1, 1, 1);
		Vector v2 = new Vector(3, 3, 2, 4);
		assertTrue("not same", v0.linearIndependent(v1, v2));
	}

	@Test
	public void adjunctTest() {
		Matrix m0 = new Matrix(3, 3, 1, 0, 0, 0, 2, 3, 0, 3, 4);
		Matrix m1 = new Matrix(3, 3, -1, 0, 0, 0, 4, -3, 0, -3, 2);
		assertTrue("not same", m1.nearly(m0.adjunct(), 0.1));
	}

	@Test
	public void randomTest() {
		Matrix m0 = Matrix.randMatrix(3, 3, 3);
		System.out.println(m0);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
			assertTrue("not smaller", m0.getValue(i, j)<=2);
			}
		}
	}

}
