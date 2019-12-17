import java.util.Scanner;
import java.util.ArrayList;
public class TesterCurve {
    public static void main(String[] args)
    {
		Scanner input1 = new Scanner(System.in);//input scanner
		ArrayList<Double> co_ordinates = new ArrayList<Double>();//1D Arraylist to store input elements
		System.out.println("Enter the co-ordinates: ");

		String [] tokens = input1.nextLine().split("\\s");//store input numbers in string tokens

		for (int i = 0; i < tokens.length; i++)
		co_ordinates.add(Double.parseDouble(tokens[i])); // add input numbers to the Array
		
		input1.close();//close scanner object

		System.out.println(co_ordinates);

		Curve curve1 = new Curve(co_ordinates); // create curve object curve1
		double yval = curve1.Evaluate_Y_at_X(2.5);//calling evaluate Y at X method, input x value is directly passed. Need to add scanner
		System.out.println("Y value is: "+ yval);

    }
}