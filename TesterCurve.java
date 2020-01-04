/********************************************************************
 *
 * To test the functions present in the curve class
 * 
 ********************************************************************/
public class TesterCurve {
	public static void main(String args[])
	{
		
		/*Giving input for first curve*/
		Curve Line1 = new Curve(0,1,0.25,10,3.5,1);
		/*Giving input for second curve*/
		Curve Line2 = new Curve(0,0,0,5,0,1);

		
		/*evaluateYatX function*/
		double y = Line1.evaluateYatX(25);
		System.out.println("y value is :" + y);
		
		/*evaluateXatY function*/
		double x = Line2.EvaluateXatY(20);
		System.out.println("x value is :" + x);
		
		/*scaleOnX function*/		
		Curve test1 = Curve.scaleOnX(Line1, 1.5);
		
		/*scaleOnY function*/
		Curve test2 = Curve.scaleOnY(Line1, 2);
		
		/*multiplybyScalar function*/
		Curve test3 = Curve.multiplyScalar(Line1, 2);
		
		/*Addition of two curves*/
		Curve test4 = Curve.addCurve(Line1, Line2, 20);
		
		/*Multiplication of two curves*/
		Curve test5 = Curve.multiplicationOfTwoCurves(Line1, Line2, 15);
		
		/*Affine function*/
		Curve test6 = Curve.affine(Line1, 0.5, 2);
		
		/*pseudo inverse function*/
		Curve test7 = Curve.Invert(Line2);
		
		/*Ceil function*/
		Curve test8 = Curve.ceil(Line1, 20);
		
		/*Floor function*/
		Curve test9 = Curve.floor(Line2, 10);
		
		/*Min-plus Convolution*/
		Curve test10 = Curve.minConv(Line1, Line2, 20);
		
		/*Min-plus DeConvolution*/
		Curve test11 = Curve.minDConv(Line1, Line2, 20);
		
		/*Max-plus Convolution*/
		Curve test12 = Curve.maxConv(Line1, Line2, 20);
		
		/*Max-plus DeConvolution*/
		Curve test13 = Curve.maxDConv(Line1, Line2, 20);
		
		/*Printing the resultant curve*/
		Curve.printCurve(test10);	
	}

}

