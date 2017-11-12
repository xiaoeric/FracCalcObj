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
        // TODO: Implement this function to produce the solution to the input
        String[] terms = input.split(" ");
        String first = terms[0];
        String operator = terms[1];
        String second = terms[2];
        
        boolean multiOp = false;
        if (terms.length > 3)
        	multiOp = true;
        
        int firstWhole = 0;
        int firstNumer = 0;
        int firstDenom = 1;
        if (first.indexOf('_') >= 0)
        {
        	firstWhole = Integer.parseInt(first.substring(0, first.indexOf('_')));
        	firstNumer = Integer.parseInt(first.substring(first.indexOf('_') + 1, first.indexOf('/')));
        	firstDenom = Integer.parseInt(first.substring(first.indexOf('/') + 1));
        }
        else if (first.indexOf('/') >= 0)
        {
        	firstNumer = Integer.parseInt(first.substring(0, first.indexOf('/')));
        	firstDenom = Integer.parseInt(first.substring(first.indexOf('/') + 1));
        }
        else
        {
        	firstWhole = Integer.parseInt(first);
        }
        
        int secondWhole = 0;
        int secondNumer = 0;
        int secondDenom = 1;
        if (second.indexOf('_') >= 0)
        {
        	secondWhole = Integer.parseInt(second.substring(0, second.indexOf('_')));
        	secondNumer = Integer.parseInt(second.substring(second.indexOf('_') + 1, second.indexOf('/')));
        	secondDenom = Integer.parseInt(second.substring(second.indexOf('/') + 1));
        }
        else if (second.indexOf('/') >= 0)
        {
        	secondNumer = Integer.parseInt(second.substring(0, second.indexOf('/')));
        	secondDenom = Integer.parseInt(second.substring(second.indexOf('/') + 1));
        }
        else
        {
        	secondWhole = Integer.parseInt(second);
        }
        
        if (firstDenom == 0 || secondDenom == 0)
        	return "Umm... why are you trying to divide by zero...?";
        
        Fraction firstFrac = new Fraction(firstWhole, firstNumer, firstDenom);
        Fraction secondFrac = new Fraction(secondWhole, secondNumer, secondDenom);
        Fraction resultFrac;
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
        
        if (multiOp)
        	result = produceAnswer(result + " " + terms[3] + " " + terms[4]);
        
        return result;
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
