	import java.util.ArrayList;
	import java.util.List;

	public class Curve {
		private double x_intercept;
		private double y_intercept;
		private double slope;
		List<Segments> ds = new ArrayList<>();
		//private double[] segment;
		
	/*creating a curve with x-intercepts, y-intercepts and slope for different segments*/
		public Curve(double ...seg) 
		{
			int k=1;
			int j=0;
			for(int i = 0;i<seg.length;i++)
			{
				if (k <= seg.length/3)
				{
					Segments segment = new Segments(seg[j], seg[j+1], seg[j+2]);
					//ds.add((Segments)(segment.add(seg[j], seg[j+1], seg[j+2])));
					ds.add(segment);
					k++;
				}			
				j +=3;
			}
		}
	
		public double evaluateX(double x)
		{

			double y = 0;
			System.out.println(ds.size());
			for(int i = 0;i<ds.size();i++)
			{
				
				if(x > ds.get(i).getarrayValue(0))
				{
					slope = ds.get(i).getarrayValue(2);
					y_intercept = ds.get(i).getarrayValue(1) - slope * ds.get(i).getarrayValue(0);
					y = (slope * x) + y_intercept;
				}
			}
			System.out.println("y_intercept is : " + y_intercept);
			System.out.println("slope is : "+ slope);
			return y;
		}
		
		public void scaleX(double scaleX)
		{
			List<Segments> ds_X = new ArrayList<>();
			//ds_X.add(0, ds.get(0));
			System.out.print(ds_X.size());
			//System.out.println(ds_X.get(0));
			for(int i = 0; i<ds.size();i++)
			{
				//ds_X.add(i,ds.get(i));
				ds_X.add(i, ds.get(i).scaleSegX(scaleX));
				
				//System.out.println(ds_X.get(i).getarrayValue(i));
				//System.out.println(ds_X.size());
				//System.out.println("Slope is" + slope);
			}
			for(int i = 0; i<(ds.size() - 1);i++)
			{
				slope = (evaluateX((ds.get(i+1).getarrayValue(0)) - ds_X.get(i).getarrayValue(1))/(ds_X.get(i+1).getarrayValue(0)-ds.get(i).getarrayValue(0)));
				System.out.println("Slope is" + slope);
				Segments x= new Segments(ds_X.get(i).getarrayValue(0), ds_X.get(i).getarrayValue(1), slope);
				
			}
			
			
			
			
			

			
			//return ds_X;
		}
/*getting the value of each segment*/
		public List getSegmentValue(int get) {
			
			return  ds.get(get).getValue();
			
		}
		
		public static void getCurve(List<Segments> ds) {
			
			for(int i=0;i<ds.size();i++)
			{
				for(int j=0;j<3;j++)
				{
			
					System.out.print(" " + ds.get(i).getarrayValue(j));
			
				}
			}
		}
	}


