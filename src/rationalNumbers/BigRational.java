package rationalNumbers;

import static java.math.BigInteger.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Formattable;
import java.util.Formatter;
import java.util.Objects;

public class BigRational extends Number implements Comparable<BigRational>, Formattable {

	private static final long serialVersionUID = 1L;

	private final BigInteger nominator;
	private final BigInteger denominator;

	private static BigRational HALF = new BigRational(ONE, ONE.add(ONE));

	public BigRational(BigInteger nominator, BigInteger denominator) {
		Objects.requireNonNull(nominator, "The nominator has to be given.");
		if (denominator == null || denominator.signum() <= 0) {
			throw new IllegalArgumentException("The denominator has to be a positive number.");
		}
		BigInteger gcd = nominator.gcd(denominator);
		this.denominator = denominator.divide(gcd);
		this.nominator = nominator.divide(gcd);
	}

	public BigRational(BigInteger nominator) {
		this(nominator, ONE);
	}

	public BigRational(BigDecimal deci) {
		this(deci.unscaledValue().multiply(TEN.pow(Math.max(0, -deci.scale()))), TEN.pow(Math.max(deci.scale(), 0)));

	}

	public BigRational(String s1, String s2) {
		this(new BigInteger(s1), new BigInteger(s2));
	}

	public static BigRational intervall2n(BigDecimal d0, BigRational first, BigRational second, int precision) {
		BigRational d = new BigRational(d0);
		if (first.compareTo(second) > 0) {
			BigRational third = first;
			first = second;
			second = third;
		}
		if (precision < 0) {
			throw new IllegalArgumentException("The precision has to be a positive integer.");
		}
		if (d.compareTo(first) < 0 && d.compareTo(second) > 0) {
			throw new IllegalArgumentException("Your argument does not lie in checking range.");
		}
		System.out.println(d.subtract(first).abs());
		System.out.println(new BigRational(ONE, TEN.pow(precision)));
		while (d.subtract(first).abs().compareTo(new BigRational(ONE, TEN.pow(precision))) > 0) {
			BigRational half = second.subtract(first).multiply(HALF);
			if (d.compareTo(first.add(half)) > -1 && d.compareTo(second) < 1) {
				first = first.add(half);
			} else {
				second = first.add(half);
			}
		}
		return first;
	}
	
	
	public static BigRational nearly(BigDecimal d0, int precision){
		while(true){
			//z=d*n%1<genauigkeit
			//then n := Nenner
		}
	}

	public int compareTo(BigRational other) {
		return (nominator.multiply(other.denominator)).compareTo(denominator.multiply(other.nominator));
	}

	public BigRational multiply(BigRational other) {
		return new BigRational(nominator.multiply(other.nominator), denominator.multiply(other.denominator));
	}

	public BigRational divide(BigRational other) {
		return new BigRational(nominator.multiply(other.denominator), denominator.multiply(other.nominator));
	}

	public BigRational add(BigRational other) {
		return new BigRational((nominator.multiply(other.denominator)).add(other.nominator.multiply(denominator)),
				denominator.multiply(other.denominator));
	}

	public BigRational subtract(BigRational other) {
		return new BigRational((nominator.multiply(other.denominator)).subtract(other.nominator.multiply(denominator)),
				denominator.multiply(other.denominator));
	}

	public BigRational abs() {
		return new BigRational(nominator.abs(), denominator);
	}

	@Override
	public int intValue() {
		return nominator.divide(denominator).intValue();
	}

	@Override
	public long longValue() {
		return nominator.divide(denominator).longValue();
	}

	@Override
	public float floatValue() {
		return nominator.floatValue() / denominator.floatValue();
	}

	@Override
	public double doubleValue() {
		return nominator.doubleValue() / denominator.doubleValue();
	}

	@Override
	public String toString() {
		return nominator.toString() + "/" + denominator.toString();
	}

	@Override
	public void formatTo(Formatter formatter, int flags, int width, int precision) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		BigDecimal d = new BigDecimal(new BigInteger("1313121"), 3);
		BigDecimal d0 = new BigDecimal(new BigInteger("1"), 0);
		BigRational e = new BigRational("0", "1");
		BigRational g = new BigRational("1", "1");
		System.out.println(d);
		BigRational r = intervall2n(d, e, g, 3);
		System.out.println(r);
		System.out.println(d.remainder(d0)); 
	}

}
