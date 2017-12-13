package fracCalc;

import java.util.Scanner;

/**
 * @author Eric Xiao
 * @pseudocode
 * main(){
 * 	create scanner object
 * 	do while loop{
 * 		take user input;
 * 		if input is "quit" end loop;
 * 		pass input to produceAnswer() and print;
 * 	}
 * }
 * produceAnswer(){
 * 	create array of terms by splitting at spaces;
 * 	first term is String at first index;
 * 	operator is String at second index;
 * 	second term is String at third index;
 * 	create and initialize whole, numerator, and
 * 	denominator ints for first term with default values;
 * 	if mixed number
 * 		parse based on "_" and "/";
 * 	else if fraction
 * 		parse based on "/";
 * 	else no fraction
 * 		parse as whole number;
 * 	repeat parsing process for second term;
 * 	create return String and result numerator
 * 	and denominator ints;
 * 	switch checks operator String{
 * 		addition
 * 			convert both to improper frac and 
 * 			multiply for common denominator;
 * 			add numerators;
 * 		subtraction
 * 			convert both to improper frac and 
 * 			multiply for common denominator;
 * 			subtract numerators;
 * 		multiplication
 * 			convert both to improper frac; 
 * 			multiply numerators together;
 * 			multiply denominators together;
 * 		division
 * 			convert both to improper frac; 
 * 			multiply first numerator and second denominator;
 * 			multiply second numerator and first denominator;
 * 	}
 * 	pass result String to reduce() and reassign to result;
 * 	pass result String to reformat() and reassign to result;
 * 	return result String;
 * }
 * reduce(){
 * 	parse String around "/" into numerator and denominator ints;
 * 	if denominator is negative
 * 		make denominator positive and invert numerator;
 * 	int whole is numerator / denominator;
 * 	numerator is reassigned as remainder of numerator / denominator;
 * 	find gcf by passing numerator and denominator to gcf();
 * 	divide both numerator and denominator by gcf and reassign;
 * 	if both whole and numerator are negative
 * 		invert numerator;
 * 	return whole, numerator, and denominator as mixed number;
 * }
 * reformat(){
 * 	if input starts with "0_"
 * 		remove "0_";
 * 	if input contains but does not start with "0/"
 * 		remove "_0/" and everything following;
 * 	else if starts with "0/"
 * 		reassign input to "0";
 * 	if input ends with "/1"
 * 		remove "/1";
 * 	return input String;
 * }
 * gcf and isDivisibleBy copied from Calculate Library
 */
public class FracCalc {

    public static void main(String[] args) 
    {
        // TODO: Read the input from the user and call produceAnswer with an equation
    	Scanner console = new Scanner(System.in);
    	do
    	{
    	String input = console.nextLine();
    	if (input.equals("quit"))
    		break;
    	System.out.println(produceAnswer(input));
    	} while(true);
    }
    
    // ** IMPORTANT ** DO NOT DELETE THIS FUNCTION.  This function will be used to test your code
    // This function takes a String 'input' and produces the result
    //
    // input is a fraction string that needs to be evaluated.  For your program, this will be the user input.
    //      e.g. input ==> "1/2 + 3/4"
    //        
    // The function should return the result of the fraction after it has been calculated
    //      e.g. return ==> "1_1/4"
    public static String produceAnswer(String input)
    { 
    	//Splits user input into array
        String[] terms = input.split(" ");
        String firstString = terms[0];
        String operator = terms[1];
        String secondString = terms[2];
        
        /*
         * Activates code to handle multiple operations
         * later on if multiple operations exist
         */
        boolean multiOp = false;
        if (terms.length > 3)
        	multiOp = true;
        
        //Receives output from parse()
        Fraction firstFrac = parse(firstString);
        Fraction secondFrac = parse(secondString);
        
        /*
         * Returns an error if the input contains a fraction
         * with a denominator of 0
         */
        if (firstFrac.getDenom() == 0 || secondFrac.getDenom() == 0)
        	return "Umm... why are you trying to divide by zero...?";
        
        //Initialize array to contain result of calculation
        Fraction resultFrac = new Fraction(0, 0, 1);
        
        /*
         * Switch statement checks operator and performs
         * corresponding calculations
         */
        switch (operator)
        {
        	case "+":
        		resultFrac = firstFrac.add(secondFrac);
        		break;
        	case "-":
        		resultFrac = firstFrac.subtract(secondFrac);
        		break;
        	case "*":
        		resultFrac = firstFrac.multiply(secondFrac);
        		break;
        	case "/":
        		resultFrac = firstFrac.divide(secondFrac);
        		break;
        	default:
        		return "Hey! That's an invalid format! Did you even pass elementary school math?!";
        }
        resultFrac.reduce();
        String result = resultFrac.toString();
        
        if (multiOp) {
        	String temp = result;
        	for(int i = 4; i < terms.length; i += 2)
    		temp += " " + terms[i - 1] + " " + terms[i];
    		result = produceAnswer(temp);
        }
        return result;
    }
    
    public static Fraction parse(String input)
    {
    	//Initializes with default values
    	int whole = 0;
        int numer = 0;
        int denom = 1;
        
        
        //Parses as a mixed number if "_" exists
        if (input.indexOf('_') >= 0)
        {
        	whole = Integer.parseInt(input.substring(0, input.indexOf('_')));
        	numer = Integer.parseInt(input.substring(input.indexOf('_') + 1, input.indexOf('/')));
        	denom = Integer.parseInt(input.substring(input.indexOf('/') + 1));
        }
        /*
         * Parses as a fraction if "/" exists
         * and does not contain "_"
         */
        else if (input.indexOf('/') >= 0)
        {
        	numer = Integer.parseInt(input.substring(0, input.indexOf('/')));
        	denom = Integer.parseInt(input.substring(input.indexOf('/') + 1));
        }
        /*
         * Parses as a whole number if neither
         * "_" nor "/" exist
         */
        else
        	whole = Integer.parseInt(input);
        
        //Initializes return array with the parse results
        Fraction frac = new Fraction(whole, numer, denom);
        
        //Returns array
        return frac;
    }

    // TODO: Fill in the space below with any helper methods that you think you will need
    public static int gcf(int num1, int num2) {
		/* i is declared before the for loop because
		 * it must be returned after the loop.
		 */
		int i;
		/* i can be initialized as either of
		 * the two inputs because any number
		 * greater than the smaller of the two
		 * is inherently invalid. The for loop
		 * continues while one of the numbers
		 * is not divisible by i.
		 */
		num1 = Math.abs(num1);
		num2 = Math.abs(num2);
		for(i = num2; !(isDivisibleBy(num2, i) && isDivisibleBy(num1, i)); i--) {}
		return i;
	}
    
    public static boolean isDivisibleBy(int dividend, int divisor) {
		/* An IllegalArgumentException will be thrown when the divisor
		 * is 0 because you cannot divide by 0.
		 */
		if(divisor == 0) {
			throw new IllegalArgumentException();
		}
		if(dividend % divisor == 0) {
			return true;
		} else {
			return false;
		}
	}
}
