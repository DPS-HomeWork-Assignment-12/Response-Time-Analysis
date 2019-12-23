
import java.util.ArrayList;
import java.util.List;

public class Segments extends Curve {
	/* creating a list array*/
	List<Double> fs = new ArrayList<>();
	/*storing the values in segment*/
	public Segments(double x_intercept,double y_intercept,double slope)
	{
		this.fs.add(x_intercept);
		this.fs.add(1, y_intercept);
		this.fs.add(2, slope);
	}

	public Segments() {
		// TODO Auto-generated constructor stub
	}

	/*to get the values of the requested segment*/
	public List getValue()
	{

			return fs;
		
		//System.out.println("getting the value");
	}
	
	public double getarrayValue(int i)
	{
		return fs.get(i);

	}


	public void setarrayValue(int i, double x)
	{
		fs.set(i, x);

	}	

	

	public Segments endElement(int k) {
		// TODO Auto-generated method stub
		return null;
	}

	public Segments scaleSegX(double x) {
		
		Segments fs_X = new Segments(fs.get(0) * x, fs.get(1), fs.get(2));
		return fs_X;
		// TODO Auto-generated method stub
		
	}
	

}

