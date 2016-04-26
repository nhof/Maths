package numbers;

import java.math.BigDecimal;

public class BigDecimalC {
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
}
