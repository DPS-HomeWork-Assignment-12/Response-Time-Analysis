import java.util.ArrayList;

public class Curve {
	ArrayList<ArrayList<Double>> curve = new ArrayList<ArrayList<Double>>(); //created 2D arraylist
	public Curve(ArrayList<Double> co_ordinates)//constructor which takes parameters of type Arraylist<double>
	{	
		int pairs = co_ordinates.size()/2; //Calculate the number of x,y co-ordinate pairs in the the arraylist
		int j = 0; //initialize counter to iterate through the co_ordinates ArrayList
		
		/*
		*creating a 2D arraylist by storing x value and y value in each row 
		*
		*
		*/
		for (int i = 0; i < pairs; i++)
		{
			curve.add(new ArrayList<Double>());	//to intialze an array and add each row´s elements to this array, without this , cannot add elements to the curve arraylist !!
			curve.get(i).add(0,co_ordinates.get(j));//x Co-ordinates
			curve.get(i).add(1,co_ordinates.get(j+1));//y Co-ordinates
			if (j < (co_ordinates.size() - 2))
			{
				curve.get(i).add(2,((co_ordinates.get(j+1) - co_ordinates.get(j+3))/(co_ordinates.get(j) - co_ordinates.get(j+2)))); //slope
			}
			
			j = j+2;		
		}
		System.out.println(curve.size());
		System.out.println(curve);
	
			
	}


	/**
	 * To evaluate the value of Y at any X value
	 * @param x
	 * @return y
	 */
	public double Evaluate_Y_at_X(double x)
	{
		double y = 0;
		for(int i = 0; i < curve.size()-1; i++)
		{
			if(x>curve.get(i).get(0) && x <= curve.get(i+1).get(0))//To check the segment in which the input x value belongs to
			{
				double slope = curve.get(i).get(2); // calculate slope of that segment
				double yIntercept = curve.get(i).get(1) - slope * curve.get(i).get(0);//calculate y-intercept of that segment
				y =  (slope * x) + yIntercept;//calculate Y value at that point x

			}


		}
		
		return y;
	}

	public ArrayList<Double> PseudoInverse(){


	}

}
