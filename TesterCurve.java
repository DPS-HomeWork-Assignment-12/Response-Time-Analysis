/********************************************************************
 *
 * To test the functions present in the curve class
 * 
 ********************************************************************/
public class TesterCurve {
	public static void main(String args[])
	{
		
		/*Giving input for first curve*/
		Curve Line1 = new Curve(0,0,1,2,2,0.25,4);
		/*Giving input for second curve*/
		Curve Line2 = new Curve(0,0,0,5,0,1,10,5,2);

		
		/*evaluateYatX function*/
		double y = Line1.evaluateYatX(20);
		System.out.println("y value is :" + y);
		
		
		/*evaluateXatY function*/
		double x = Line2.EvaluateXatY(20);
		System.out.println("x value is :" + x);

		/*evaluateSatX function*/
		double slope = Line1.evaluateSatX(0);
		System.out.println("slope value is :" + slope);
		
		
		/*scaleOnX function*/		
		Curve test1 = Curve.scaleOnX(Line1, 2);
		
		/*scaleOnY function*/
		Curve test2 = Curve.scaleOnY(Line1, 2);
		
		/*multiplybyScalar function*/
		//Curve test3 = Curve.multiplyScalar(Line1, 2);
		
		/*Addition of two curves*/
		Curve test4 = Curve.addCurve(Line1, Line2, 20);
		
		/*Multiplication of two curves*/
		Curve test5 = Curve.multiplicationOfTwoCurves(Line1, Line2, 15);
		
		/*Affine function*/
		Curve test6 = Curve.affine(Line1, 0.5, 2);
		
		/*pseudo inverse function*/
		Curve test7 = Curve.Invert(Line1);
		
		/*Ceil function*/
		Curve test8 = Curve.ceil(Line1, 20);

		/*Maximum of two Curves*/
		Curve test9 = Curve.curveMin(Line1, Line2, 20);
		
		/*Minimum of two Curves*/
		Curve test10 = Curve.curveMax(Line1, Line2, 20);

		/*Floor function*/
		Curve test11 = Curve.floor(Line1, 20);
		
		/*Min-plus Convolution*/
		Curve test12 = Curve.minConv(Line1, Line2, 20);
		
		/*Min-plus DeConvolution*/
		Curve test13 = Curve.minDConv(Line1, Line2, 20);
		
		/*Max-plus Convolution*/
		Curve test14 = Curve.maxConv(Line1, Line2, 20);
		
		/*Max-plus DeConvolution*/
		Curve test15 = Curve.maxDConv(Line2, Line1, 20);
		
		/*Printing the resultant curve*/
		System.out.println("Curve1 is: ");
		Curve.printCurve(Line1);
		System.out.println();
		System.out.println("Curve2 is: ");
		Curve.printCurve(Line2);
		System.out.println();
		System.out.println("Output Curve is: ");
		Curve.printCurve(test15);
		System.out.println();

	}

}

