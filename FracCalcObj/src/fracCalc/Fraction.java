package fracCalc;

public class Fraction {
	int whole;
	int numer;
	int denom;
	public Fraction(int whole, int numer, int denom) {
		this.whole = whole;
		this.numer = numer;
		this.denom = denom;
	}
	public String toString() {
		String result = "";
		if(whole != 0) {
			result += whole;
			if(numer != 0) {
				result += numer;
				if(denom != 1)
					result += "/" + denom;
			}
		} else if(numer != 0) {
			result += numer;
			if(denom != 1)
				result += "/" + denom;
		} else {
			result += "0";
		}
		return result;
	}
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
