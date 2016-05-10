package number;

import java.math.BigDecimal;

public class BigDecimalC extends Number {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private BigDecimal re;
	private BigDecimal im;
	
	public BigDecimalC(BigDecimal re, BigDecimal im){
		this.re = re;
		this.im = im;
	}
	
	public BigDecimalC add(BigDecimalC other){
		return new BigDecimalC(re.add(other.re), im.add(other.im));
	}
	
	public BigDecimalC multiply(BigDecimalC other){
		return new BigDecimalC(re.multiply(other.re).add((im.multiply(other.im).multiply(BigDecimal.valueOf(-1)))),re.multiply(other.im).multiply(im.multiply(other.re)));
	}
	
	public BigDecimalC conjugate(){
		return new BigDecimalC(re,im.multiply(BigDecimal.valueOf(-1)));
	}

	@Override
	public int intValue() {
		return re.intValue();
	}

	@Override
	public long longValue() {
		return re.longValue();
	}

	@Override
	public float floatValue() {
		return re.floatValue();
	}

	@Override
	public double doubleValue() {
		return re.doubleValue();
	}
	
	
	public int imintValue() {
		return im.intValue();
	}

	public long imLongValue() {
		return im.longValue();
	}

	public float imFloatValue() {
		return im.floatValue();
	}

	public double imDoubleValue() {
		return im.doubleValue();
	}	
	
}
