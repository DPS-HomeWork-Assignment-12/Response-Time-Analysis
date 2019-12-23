	import java.util.ArrayList;
	import java.util.List;

	public class Curve {
		private double x_intercept;
		private double y_intercept;
		private double slope;
		List<Segments> ds = new ArrayList<>();
		//private double[] segment;
		private double period = 0;
	/*creating a curve with x-intercepts, y-intercepts and slope for different segments*/
		public Curve(double ...seg) 
		{
			//int k=1;
			int j=0;
			if(seg.length % 3 == 0)
			{	for(int i = 1;i<=(seg.length/3);i++)
				{

						Segments segment = new Segments(seg[j], seg[j+1], seg[j+2]);
						//ds.add((Segments)(segment.add(seg[j], seg[j+1], seg[j+2])));
						ds.add(segment);		
					j +=3;
				}
			}
			else if (seg.length % 3 == 1)
			{
				period = seg[seg.length-1];
				System.out.println("period is "+ period);
				for(int i = 1;i<=((seg.length/3)+1);i++)
				{
						if(period > seg[j])
						{
						Segments segment = new Segments(seg[j], seg[j+1], seg[j+2]);
						//ds.add((Segments)(segment.add(seg[j], seg[j+1], seg[j+2])));
						ds.add(segment);
						}
						else
						{
							Segments segment = new Segments(period,((seg[j-1]*period) + (seg[j-2] -seg[j-1]*seg[j-3])),seg[2]);
							ds.add(segment);
							i = (seg.length/3)+1;
						}
					j +=3;
			
				}	
			}	

		}
	
		public double evaluateYatX(double x)
		{
			double y = 0;
			if(period == 0)
			{							
				System.out.println("number of segments: " + ds.size());
				for(int i = 0;i<ds.size();i++)
				{
					
					if(x >= ds.get(i).getarrayValue(0))
					{
						slope = ds.get(i).getarrayValue(2);
						y_intercept = ds.get(i).getarrayValue(1) - slope * ds.get(i).getarrayValue(0);
						y = (slope * x) + y_intercept;
					}
				}
				System.out.println("y_intercept is : " + y_intercept);
				System.out.println("slope is : "+ slope);
			}
			else
			{
				
				System.out.println("number of segments: " + (ds.size()-1));
				if(x/period <= 1)
				{
					for(int i = 0;i<ds.size();i++)
					{
						
						if(x >= ds.get(i).getarrayValue(0))
						{
							slope = ds.get(i).getarrayValue(2);
							y_intercept = ds.get(i).getarrayValue(1) - slope * ds.get(i).getarrayValue(0);
							y = (slope * x) + y_intercept;
						}
					}
					System.out.println("y_intercept is : " + y_intercept);
					System.out.println("slope is : "+ slope);
				}
				else
				{
					double xVal = 0;
					//for(int i = 0;xVal>=x;i++)
					int numSeg= ds.size() -1;
					do
					{
						int size=ds.size();
						System.out.println("size is " +size);
						xVal = ds.get(size-1).getarrayValue(0)+ds.get(size-numSeg).getarrayValue(0)-ds.get(size-numSeg-1).getarrayValue(0);
						double slopeVal = ds.get(size-numSeg).getarrayValue(2);
						double yVal = ds.get(size-1).getarrayValue(1)+ds.get(size-numSeg).getarrayValue(1)-ds.get(size-numSeg-1).getarrayValue(1);
						Segments segment = new Segments(xVal, yVal, slopeVal);
						ds.add(segment);

					}while(xVal <= x);

					for(int i = 0;i<ds.size();i++)
					{
						
						if(x >= ds.get(i).getarrayValue(0))
						{
							slope = ds.get(i).getarrayValue(2);
							y_intercept = ds.get(i).getarrayValue(1) - slope * ds.get(i).getarrayValue(0);
							y = (slope * x) + y_intercept;
						}
					}
					System.out.println("y_intercept is : " + y_intercept);
					System.out.println("slope is : "+ slope);
				}
			}
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

		public int size()
		{
			return ds.size();

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


