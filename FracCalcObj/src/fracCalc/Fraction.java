package fracCalc;

/**
 * @author Eric Xiao
 * @date 12/19/2017
 * @class APCS 2
 * @description
 * Allows FracCalc to create objects to represent
 * mixed numbers
 */
public class Fraction {
	//Defining fields
	private int whole;
	private int numer;
	private int denom;
	//Constructor initializes fields
	public Fraction(int whole, int numer, int denom) {
		this.whole = whole;
		this.numer = numer;
		this.denom = denom;
	}
	/*
	 * Converts Fraction object to a String; eliminates
	 * extraneous elements
	 */
	public String toString() {
		String input = whole + "_" + numer + "/" + denom;

    	if (input.startsWith("0_"))
        	input = input.substring(2);
    	
    	if (input.indexOf("_0/") > 0)
    		input = input.substring(0, input.indexOf("_0/"));
    	else if (input.indexOf("0/") == 0)
    		input = "0";
    	
    	if (input.endsWith("/1"))
    		input = input.substring(0, input.length() - 2);
    	
    	return input;
	}
	//Returns the denominator of the fraction
	public int getDenom() {
		return denom;
	}
	//Adds Fraction object to this Fraction object
	public Fraction add(Fraction frac) {
		int resultNumer;
        int resultDenom;
		if (this.whole < 0)
			resultNumer = (this.whole * this.denom - this.numer) * frac.denom;
		else
			resultNumer = (this.whole * this.denom + this.numer) * frac.denom;
		
		if (frac.whole < 0)
			resultNumer += (frac.whole * frac.denom - frac.numer) * this.denom;
		else
			resultNumer += (frac.whole * frac.denom + frac.numer) * this.denom;
		
		resultDenom = this.denom * frac.denom;
		Fraction result = new Fraction(0, resultNumer, resultDenom);
		return result;
	}
	//Subtracts this Fraction object by another Fraction object
	public Fraction subtract(Fraction frac) {
		int resultNumer;
        int resultDenom;
		if (this.whole < 0)
			resultNumer = (this.whole * this.denom - this.numer) * frac.denom;
		else
			resultNumer = (this.whole * this.denom + this.numer) * frac.denom;
		
		if (frac.whole < 0)
			resultNumer -= (frac.whole * frac.denom - frac.numer) * this.denom;
		else
			resultNumer -= (frac.whole * frac.denom + frac.numer) * this.denom;
		
		resultDenom = this.denom * frac.denom;
		Fraction result = new Fraction(0, resultNumer, resultDenom);
		return result;
	}
	//Multiplies this Fraction object by another Fraction object
	public Fraction multiply(Fraction frac) {
		int resultNumer;
        int resultDenom;
		if (this.whole < 0)
			resultNumer = this.whole * this.denom - this.numer;
		else
			resultNumer = this.whole * this.denom + this.numer;
		
		if (frac.whole < 0)
			resultNumer *= frac.whole * frac.denom - frac.numer;
		else
			resultNumer *= frac.whole * frac.denom + frac.numer;
		
		resultDenom = this.denom * frac.denom;
		Fraction result = new Fraction(0, resultNumer, resultDenom);
		return result;
	}
	//Divides this Fraction object by another Fraction object
	public Fraction divide(Fraction frac) {
		int resultNumer;
        int resultDenom;
		if (this.whole < 0)
			resultNumer = (this.whole * this.denom - this.numer) * frac.denom;
		else
			resultNumer = (this.whole * this.denom + this.numer) * frac.denom;
		
		if (frac.whole < 0)
			resultDenom = (frac.whole * frac.denom - frac.numer) * this.denom;
		else
			resultDenom = (frac.whole * frac.denom + frac.numer) * this.denom;
		Fraction result = new Fraction(0, resultNumer, resultDenom);
		return result;
	}
	//Simplifies this Fraction object
	public void reduce() {
		if (denom < 0)
    	{
    		numer *= -1;
    		denom *= -1;
    	}
    	
    	whole += numer / denom;
    	numer %= denom;
    	
    	int gcf = FracCalc.gcf(numer, denom);
    	numer /= gcf;
    	denom /= gcf;
    	
    	if (whole < 0 && numer < 0)
    		numer *= -1;
	}
}
