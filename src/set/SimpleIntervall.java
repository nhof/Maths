package set;

import org.junit.internal.runners.statements.ExpectException;

import number.BigDecimalC;

public class SimpleIntervall implements Set{

	private Number left;
	private Number right;
	
	public SimpleIntervall(Number left, Number right){
		if(left instanceof BigDecimalC){
			throw new ClassFormatError("Don't enter complex values");
		}
	
	}

	@Override
	public double getCardinality() {
		return 0;
	}

	@Override
	public double getDimension() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
}
