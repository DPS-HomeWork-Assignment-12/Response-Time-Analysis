import java.util.Scanner;
import java.util.ArrayList;
public class TesterCurve {
    public static void main(String[] args)
    {
		Scanner input1 = new Scanner(System.in);
		ArrayList<Double> co_ordinates = new ArrayList<Double>();
		System.out.println("Enter the co-ordinates: ");

		String [] tokens = input1.nextLine().split("\\s");

		for (int i = 0; i < tokens.length; i++)
		co_ordinates.add(Double.parseDouble(tokens[i]));
		
		input1.close();

		//for(int j  = 0; j < co_ordinates.size(); j++){
		System.out.println(co_ordinates);
		//}

		Curve curve1 = new Curve(co_ordinates);
		double yval = curve1.Evaluate_Y_at_X(2.5);
		System.out.println("Y value is: "+ yval);

    }
}