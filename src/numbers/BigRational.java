package numbers;

import static java.math.BigInteger.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Formattable;
import java.util.Formatter;
import java.util.Objects;

public class BigRational extends Number implements Comparable<BigRational>, Formattable {

	private static final long serialVersionUID = 1L;

	private BigInteger nominator;
	private BigInteger denominator;

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

	public BigRational(BigDecimal d, BigDecimal precision) {
		int i;
		for (i = 1; d.multiply(toBigDecimal(i)).remainder(BigDecimal.ONE).compareTo(precision) > 0; i++) {
		}
		nominator = d.multiply(toBigDecimal(i)).toBigInteger();
		denominator = toBigInteger(i);
		BigInteger gcd = nominator.gcd(denominator);
		denominator = denominator.divide(gcd);
		nominator = nominator.divide(gcd);
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


	public static BigDecimal toBigDecimal(int i) {
		return BigDecimal.valueOf(i);
	}

	public static BigInteger toBigInteger(int i) {
		return BigInteger.valueOf(i);
	}
	
	public static BigRational toRational(int i) {
		return new BigRational(BigInteger.valueOf(i));
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

	public BigRational invert() {
		if (this.nominator != ZERO) {
			return new BigRational(this.denominator, this.nominator);
		} else {
			return null;
		}
	}

	public BigRational abs() {
		return new BigRational(nominator.abs(), denominator);
	}

	public BigDecimal toBigDecimal() {
		BigDecimal n = new BigDecimal(nominator);
		BigDecimal d = new BigDecimal(denominator);
		return n.divide(d);
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
		BigDecimal d = new BigDecimal(new BigInteger("1333635423993"), 12);
		BigDecimal precision = new BigDecimal(new BigInteger("1"), 3);
		BigRational z = new BigRational(d,precision);
		System.out.println(z);
		
	}

	
}
