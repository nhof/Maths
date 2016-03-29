package matrix;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

	public static Matrix ex3x3_indep() {
		Matrix ex = new Matrix(3, 3);
		ex.setValue(0, 0, 0);
		ex.setValue(0, 1, 1);
		ex.setValue(0, 2, 2);
		ex.setValue(1, 0, 3);
		ex.setValue(1, 1, 4);
		ex.setValue(1, 2, 5);
		ex.setValue(2, 0, 6);
		ex.setValue(2, 1, 6);
		ex.setValue(2, 2, 8);
		return ex;
	}

	public static Matrix ex3x3_dep() {
		Matrix ex = new Matrix(3, 3);
		ex.setValue(0, 0, 2);
		ex.setValue(0, 1, 2);
		ex.setValue(0, 2, 2);
		ex.setValue(1, 0, 1);
		ex.setValue(1, 1, 1);
		ex.setValue(1, 2, 1);
		ex.setValue(2, 0, 0);
		ex.setValue(2, 1, 1);
		ex.setValue(2, 2, 2);
		return ex;
	}

	public static Matrix jemal() {
		Matrix ex = new Matrix(3, 3);
		ex.setValue(0, 0, 2);
		ex.setValue(0, 1, -1);
		ex.setValue(0, 2, 3);
		ex.setValue(1, 0, 7);
		ex.setValue(1, 1, 3);
		ex.setValue(1, 2, 0);
		ex.setValue(2, 0, -1);
		ex.setValue(2, 1, 2);
		ex.setValue(2, 2, -4);
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
		assertTrue("should not be same", m1.equals(m0) == false);
		assertTrue("not same", m1.nearly(m1, 0.1));
	}

	@Test
	public void invertTest() {
		Matrix m0 = new Matrix(3, 3, 2, -1, 3, 7, 3, 0, -1, 2, -4);
		Matrix m1 = new Matrix(3, 3, 12, -2, 9, -28, 5, -21, -17, 3, -13);
		System.out.println(m0.invert());
		System.out.println(m1);
		assertTrue("not same", m1.nearly(m0.invert(), 0.1));
	}

	@Test
	public void invertingExample() {
		Matrix m0 = new Matrix(4, 4, 0, 0, 1, 0, 0, -1, 0, -3, 1, 2, 0, 6, 0, 0, 0, 1);
		System.out.println(m0.invert());
	}
}
