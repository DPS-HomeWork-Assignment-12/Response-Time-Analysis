	import java.util.ArrayList;
	import java.util.List;
	import java.util.Collections;
	import java.util.stream.Collectors; 

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
							Segments segment = new Segments(period,(seg[1]+(seg[j-1]*period) + (seg[j-2] -seg[j-1]*seg[j-3])),seg[2]);
							ds.add(segment);
							i = (seg.length/3)+1;
						}
					j +=3;
			
				}	
			}	

		}

		public Curve(ArrayList<Double> curve, int flag)
		{
			if(flag==1)
			{
			int j=0;
				if(curve.size() % 2 == 0)
				{	for(int i = 1;i<=(curve.size()/2);i++)
					{

							Segments segment = new Segments(curve.get(j), curve.get(j+1));
							ds.add(segment);		
						j +=2;
					}
				}	
			}
			else
			{
				int j=0;
				if(curve.size() % 3 == 0)
				{	for(int i = 1;i<=(curve.size()/3);i++)
					{

							Segments segment = new Segments(curve.get(j), curve.get(j+1),curve.get(j+2));
							ds.add(segment);		
						j +=3;
					}
				}	

			}

		}

		
		public double evaluateYatX(double x)
		{
			double y = 0;
			
			if(period == 0)
			{							
				//System.out.println("number of segments: " + ds.size());
				for(int i = 0;i<ds.size();i++)
				{
					
					if(x >= ds.get(i).getarrayValue(0))
					{
						slope = ds.get(i).getarrayValue(2);
						y_intercept = ds.get(i).getarrayValue(1) - slope * ds.get(i).getarrayValue(0);
						y = (slope * x) + y_intercept;
					}
				}
				//System.out.println("y_intercept is : " + y_intercept);
				//System.out.println("slope is : "+ slope);
			}
			else
			{
				
				//System.out.println("number of segments: " + (ds.size()-1));
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
					//System.out.println("y_intercept is : " + y_intercept);
					//System.out.println("slope is : "+ slope);
				}
				else
				{
					double xVal = 0;
					//for(int i = 0;xVal>=x;i++)
					int numSeg = ds.size() -1;
					do
					{
						int size=ds.size();
						//System.out.println("size is " +size);
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
					//System.out.println("y_intercept is : " + y_intercept);
					//System.out.println("slope is : "+ slope);
				}
			}
			return y;
		}
			


		/**
		 * multiplicationOfTwoCurves Multiplies two curve objects and returns the resultant curve object
		 * @param c1 First Curve
		 * @param c2 Second Curve
		 * @param timePeriod Time period until which the two curves are multiplied
		 * @return c3 Resultant Curve
		 */
		public static  Curve multiplicationOfTwoCurves(Curve c1, Curve c2, double timePeriod)
		{
			c1.evaluateYatX(timePeriod);
			c2.evaluateYatX(timePeriod);
			ArrayList<Double> al1 = new ArrayList<>();
			for (int i = 0; i < c1.size(); i++)
				al1.add(c1.getCellValue(i,0));
			for (int i = 0; i < c2.size(); i++)
				al1.add(c2.getCellValue(i,0));
			Collections.sort(al1);
			List<Double> al2 = al1.stream().distinct().collect(Collectors.toList()); 
			ArrayList<Double> al3 = new ArrayList<>();
			//for(int i = 0; i < al2.size(); i++)
			//{
			//	al3.add(al2.get(i));
			//	al3.add(c4.getCellValue(i,1) * c5.getCellValue(i,1));
			//}
			for(double xval : al2 )
			{
				al3.add(xval);
				al3.add(c1.evaluateYatX(xval) * c2.evaluateYatX(xval));
				//c4 = c1;
				//c5 = c2;
			}
			Curve c3 = new Curve(al3,1);
			return c3; 

		}

		public static Curve ceil(Curve curve1, int timePeriod)
		{
			ArrayList<Double> ceil1 = new ArrayList<>();
			double slope = 0;
			curve1.evaluateYatX(timePeriod);
			for (int i =0; i<timePeriod;i++)
			{
			//double xceilval = curve1.EvaluateXatY(i);
			if(curve1.EvaluateXatY(i) <= timePeriod)
				{
					ceil1.add(curve1.EvaluateXatY(i));
					ceil1.add((double)i+1);
					ceil1.add(slope);
				}	
			}
			Curve c4 = new Curve(ceil1,2);
			return c4;
		}

		public static Curve floor(Curve curve1, int timePeriod)
		{
			ArrayList<Double> floor1 = new ArrayList<>();
			double slope = 0;
			curve1.evaluateYatX(timePeriod);
			for (int i =0; i<timePeriod;i++)
			{
			if(curve1.EvaluateXatY(i) <= timePeriod)
				{
					floor1.add(curve1.EvaluateXatY(i));
					floor1.add((double)i);
					floor1.add(slope);
				}	
			}
			Curve c4 = new Curve(floor1,2);
			return c4;

		}



		public double EvaluateXatY(double y)//x = -m*y +c
		{
			double x = 0;
			//if(period == 0)
			//{
			for(int i = 0;i<ds.size();i++)
				{
					
					if(y > ds.get(i).getarrayValue(1))
					{
						slope = ds.get(i).getarrayValue(2);
						y_intercept = ds.get(i).getarrayValue(1) - slope * ds.get(i).getarrayValue(0);
						x = (y - y_intercept)/(slope);
					}
				}	
			//}
			return x;

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
				slope = (evaluateYatX((ds.get(i+1).getarrayValue(0)) - ds_X.get(i).getarrayValue(1))/(ds_X.get(i+1).getarrayValue(0)-ds.get(i).getarrayValue(0)));
				System.out.println("Slope is" + slope);
				Segments x= new Segments(ds_X.get(i).getarrayValue(0), ds_X.get(i).getarrayValue(1), slope);
				
			}
			
			
			
			
			

			
			//return ds_X;
		}

		public double getCellValue(int Cval, int Cval1){

			return  ds.get(Cval).getarrayValue(Cval1);
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


