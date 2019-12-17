import java.util.ArrayList;

public class Curve {
	ArrayList<ArrayList<Double>> curve = new ArrayList<ArrayList<Double>>();
	//private ArrayList<Double> segment = new ArrayList<Double>();
	public Curve(ArrayList<Double> co_ordinates)
	{	
		int pairs = co_ordinates.size()/2;
		int j = 0;
		for (int i = 0; i < pairs; i++)
		{
			curve.add(new ArrayList<Double>());	
			curve.get(i).add(0,co_ordinates.get(j));
			curve.get(i).add(1,co_ordinates.get(j+1));
			if (j < (co_ordinates.size() - 2))
			{
				curve.get(i).add(2,((co_ordinates.get(j+1) - co_ordinates.get(j+3))/(co_ordinates.get(j) - co_ordinates.get(j+2))));
			}
			
			j = j+2;		
		}
		System.out.println(curve.size());
		System.out.println(curve);
	
			
	}

	public double Evaluate_Y_at_X(double x)
	{
		double y = 0;
		for(int i = 0; i < curve.size()-1; i++)
		{
			if(x>curve.get(i).get(0) && x <= curve.get(i+1).get(0))
			{
				double slope = curve.get(i).get(2);
				double yIntercept = curve.get(i).get(1) - slope * curve.get(i).get(0);
				y =  (slope * x) + yIntercept;

			}


		}
		
		return y;
	}

}
