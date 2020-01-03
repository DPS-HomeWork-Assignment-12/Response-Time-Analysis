	import java.util.ArrayList;
	import java.util.List;
	import java.util.Collections;
	import java.util.EventListener;
	import java.util.stream.Collectors; 

	public class Curve {
		private double x_intercept;
		private double y_intercept;
		private double slope;
		List<Segments> ds = new ArrayList<>();
		//private double[] segment;
		private double period = 0;

		public double getY_intercept() {
			return y_intercept;
		}

		public void setY_intercept(double y_intercept) {
			this.y_intercept = y_intercept;
		}

		public double getSlope() {
			return slope;
		}

		public void setSlope(double slope) {
			this.slope = slope;
		}

		public List<Segments> getDs() {
			return ds;
		}

		public void setDs(List<Segments> ds) {
			this.ds = ds;
		}

		public double getPeriod() {
			return period;
		}

		public void setPeriod(double period) {
			this.period = period;
		}

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
		/**
		 * Curve Constructor which takes an input Arraylist
		 * Heplful when creating curve objects for the Curve methods
		 * @param curvearray Arraylist containing elements of the curve 
		 * @param flag	flag to check the type of arraylist, flag is 1 for multiplication of 2 curves method
		 */
		public Curve(ArrayList<Double> curvearray, int flag)
		{
			if(flag==1)
			{
			int j=0;
				if(curvearray.size() % 2 == 0)
				{	for(int i = 1;i<=(curvearray.size()/2);i++)
					{

							Segments segment = new Segments(curvearray.get(j), curvearray.get(j+1));
							ds.add(segment);		
						j +=2;
					}
				}	
			}
			else
			{
				int j=0;
				if(curvearray.size() % 3 == 0)
				{	for(int i = 1;i<=(curvearray.size()/3);i++)
					{

							Segments segment = new Segments(curvearray.get(j), curvearray.get(j+1),curvearray.get(j+2));
							ds.add(segment);		
						j +=3;
					}
				}	

			}

		}

		/**
		 * evaluateYatX To Evaluate Y value at a given X co-ordinate of any curve
		 * @param x
		 * @return
		 */
		public double evaluateYatX(double x)
		{
			double y = 0;
			/*
			*if the curve has no periodicity, implements below code
			*/
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
			/*
			*If curve has periodicity, implements below code
			*/
			else
			{
				
				//System.out.println("number of segments: " + (ds.size()-1));
				if(x/period <= 1)//Checking if period is before all the segments start
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
				else//if the period starts after all the segments start, implements below
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

		/**
		 * Ceil method implments the ceil of the curve for a given curve upto the given timeperiod and returns the resultant curve
		 * @param curve1 
		 * @param timePeriod
		 * @return ceiledcurve
		 */
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
			Curve ceiledcurve = new Curve(ceil1,2);
			return ceiledcurve;
		}

		/**
		 * floor method implements the floor of the curve for a given curve upto the given timeperiod and returns the resultant curve
		 * @param curve1
		 * @param timePeriod
		 * @return flooredcurve
		 */
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
			Curve flooredcurve = new Curve(floor1,2);
			return flooredcurve;

		}


		/**
		 * EvaluateXatY Method returns the value of X at a given Y of the input curve
		 * @param y y co-ordinate 
		 * @return x x co-ordinate
		 */
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
				//System.out.println("Slope is" + slope);Z
			}

			for(int i = 0; i<(ds.size() - 1);i++)
			{
				slope = (evaluateYatX((ds.get(i+1).getarrayValue(0)) - ds_X.get(i).getarrayValue(1))/(ds_X.get(i+1).getarrayValue(0)-ds.get(i).getarrayValue(0)));
				System.out.println("Slope is" + slope);
				Segments x= new Segments(ds_X.get(i).getarrayValue(0), ds_X.get(i).getarrayValue(1), slope);
				
			}
			
			System.out.println(ds_X.size());
			//return ds_X;
		}

		public List<Segments> multiplyScalar(int scalar)
		{
			List<Segments> ds_X = new ArrayList<>();
			System.out.println("ds_X " + ds_X.size() + "ds_size is : " + ds.size());
			
			for(int i= 0 ; i < ds.size()-1;i++)
			{
				//ds_X.add(i, ds.get(i));
				//System.out.println("Segment is" + ds_X.get(i).getValue());
				ds_X.add(i,ds.get(i).multiplyScale(scalar));
				System.out.println(ds_X.get(i).getValue());
			}
			//ds_X.add(ds.size(), );
			System.out.println(ds_X.size());
			return ds_X;
			
			
		}

		public static List<Segments> addCurve(Curve x,Curve y) {
		
			List<Segments> da = new ArrayList<>();
			
			int flagP =0;
			int minSize =0;
			double dx=0,dx1 = 0,dx2 =0;
			double dy=0,dy1=0;
			System.out.println("x size is "+x.size());
			
			if(x.size() < y.size())
			{
				minSize = x.size();
			}
			else 
			{
				minSize = y.size();
			}
			
			System.out.println(minSize);
			for(int i=0; i< minSize - 1;i++)
			{
					if(minSize==2)
					{
						if(y.size() == 2)
						{
							if(x.ds.get(i).getarrayValue(0) < y.ds.get(i).getarrayValue(0))
							{
								dx1 = x.ds.get(i).getarrayValue(0);
							}
							else 
							{
								dx1 = y.ds.get(i).getarrayValue(0);
							}
							if(x.ds.get(i).getarrayValue(1) < y.ds.get(i).getarrayValue(1))
							{
								dy1= x.ds.get(i).getarrayValue(1);
							}
							else
							{
								dy1 = y.ds.get(i).getarrayValue(1);
							}
						}
						if(x.period > y.period)
							dx=x.period;
						else
							dx=y.period;
					}
					else if(minSize>2)
					{
						if(x.period < y.period)
						{
							flagP = 1;
						}
						if(x.ds.get(i).getarrayValue(0) < y.ds.get(i).getarrayValue(0))
						{
							dx1 = x.ds.get(i).getarrayValue(0);
						}
						else 
						{
							dx1 = y.ds.get(i).getarrayValue(0);
						}
						if(x.ds.get(i).getarrayValue(1) < y.ds.get(i).getarrayValue(1))
						{
							dy1= x.ds.get(i).getarrayValue(1);
						}
						else
						{
							dy1 = y.ds.get(i).getarrayValue(1);
						}
						
						if(flagP == 1)
						{
							dx= y.ds.get(i+1).getarrayValue(0);
						}
						else
						{
							dx= x.ds.get(i+1).getarrayValue(0);
						}
						System.out.println("dx value is :" + dx);
					}					
						if(i == 0)
						{
							double dx_first = 0;
							if(x.size() > 2)
							{
								if(x.ds.get(i+1).getarrayValue(0) < y.ds.get(i+1).getarrayValue(0))
								{
									dx_first = x.ds.get(i+1).getarrayValue(0);
								}
								else
								{
									dx_first = y.ds.get(i+1).getarrayValue(0);
								}
								System.out.print("x value of first segment is" + dx);
							}
							else
							{
								if(x.period < y.period)
									dx_first = x.period;
								else
									dx_first = y.period;
							}
							dy = x.evaluateYatX(dx_first) + y.evaluateYatX(dx_first);
							double ds = dy/dx_first;
							Segments firstSeg = new Segments(dx1,dy1,ds);
							da.add(firstSeg);
						}
						dy = x.evaluateYatX(dx) + y.evaluateYatX(dx);
						double dslope = dy/dx;					
						{
							Segments segment = new Segments(dx , dy, dslope);
							da.add(segment);
						}		
						System.out.println("y value after addition is : "+ dy);
						System.out.println("second curve x value is " + dx2);
						System.out.println("Slope of add curve is " + dslope);									
				}
			
			return da;			
		}
		
		public static List<Segments> curveMax(Curve a, Curve b)
		{
			List<Segments> dm = new ArrayList<>();
			int minSize =0;
			double dx=0,dx1_a=a.ds.get(0).getarrayValue(0),dx1_b=b.ds.get(0).getarrayValue(0);
			double dy_a=0,dy_b=0,dy1_a=a.ds.get(0).getarrayValue(0),dy1_b=b.ds.get(0).getarrayValue(0);
			System.out.println("x size is "+a.size());
			
			if(a.size() < b.size())
			{
				minSize = a.size();
			}
			else
			{
				minSize = b.size();
			}
			
			for(int i=0;i<minSize - 1;i++)
			{
				if(minSize == 2)
				{					
					if(a.period > b.period)
					{
						dx = b.period;
					}
					else
					{
						dx=a.period;
					}
					
						dy_a = a.evaluateYatX(dx);
						dy_b = b.evaluateYatX(dx);
						if(dy_a>dy_b)
						{
							Segments seg = new Segments(dx1_a,dy1_a,a.slope);
							dm.add(seg);
						}
						else
						{
							Segments seg = new Segments(dx1_b,dy1_b,b.slope);
							dm.add(seg);
						}
				}
				else 
				{					
						if(a.ds.get(i+1).getarrayValue(0) > b.ds.get(i+1).getarrayValue(0))
						{
							dx = b.ds.get(i+1).getarrayValue(0);
						}
						else
						{
							dx = a.ds.get(i+1).getarrayValue(0);
						}
						dy_a = a.evaluateYatX(dx);
						dy_b = b.evaluateYatX(dx);
						
						if(dy_a > dy_b)
						{
							Segments seg = new Segments(a.ds.get(i).getarrayValue(0),a.ds.get(i).getarrayValue(1),a.ds.get(i).getarrayValue(2));
							dm.add(seg);
						}
						else
						{
							Segments seg = new Segments(b.ds.get(i).getarrayValue(0),dy1_b = b.ds.get(i).getarrayValue(1),b.ds.get(i).getarrayValue(2));
							dm.add(seg);
						}		
				}
			}
			
			if(a.size() > minSize)
			{
				for (int i = minSize;i<a.size();i++)
				{
					dm.add(a.ds.get(i-1));
				}
			}
			System.out.println("b. size is :" + b.size() + " Minsize is : "+minSize);
			if(b.size() > minSize)
			{
				for (int i = minSize;i<b.size();i++)
				{
					dm.add(b.ds.get(i-1));
				}
			}

			
			return dm;			
		}
		
		public static List<Segments> curveMin(Curve a, Curve b)
		{
			List<Segments> dmin = new ArrayList<>();
			int minSize =0;
			double dx=0,dx1_a=a.ds.get(0).getarrayValue(0),dx1_b=b.ds.get(0).getarrayValue(0);
			double dy_a=0,dy_b=0,dy1_a=a.ds.get(0).getarrayValue(0),dy1_b=b.ds.get(0).getarrayValue(0);
			System.out.println("x size is "+a.size());
			
			if(a.size() < b.size())
			{
				minSize = a.size();
			}
			else
			{
				minSize = b.size();
			}
			
			for(int i=0;i<minSize - 1;i++)
			{
				if(minSize == 2)
				{					
					if(a.period > b.period)
					{
						dx = b.period;
					}
					else
					{
						dx=a.period;
					}
					
						dy_a = a.evaluateYatX(dx);
						dy_b = b.evaluateYatX(dx);
						if(dy_a<dy_b)
						{
							Segments seg = new Segments(dx1_a,dy1_a,a.slope);
							dmin.add(seg);
						}
						else
						{
							Segments seg = new Segments(dx1_b,dy1_b,b.slope);
							dmin.add(seg);
						}
				}
				else 
				{					
						if(a.ds.get(i+1).getarrayValue(0) > b.ds.get(i+1).getarrayValue(0))
						{
							dx = b.ds.get(i+1).getarrayValue(0);
						}
						else
						{
							dx = a.ds.get(i+1).getarrayValue(0);
						}
						dy_a = a.evaluateYatX(dx);
						dy_b = b.evaluateYatX(dx);
						
						if(dy_a < dy_b)
						{
							Segments seg = new Segments(a.ds.get(i).getarrayValue(0),a.ds.get(i).getarrayValue(1),a.ds.get(i).getarrayValue(2));
							dmin.add(seg);
						}
						else
						{
							Segments seg = new Segments(b.ds.get(i).getarrayValue(0),dy1_b = b.ds.get(i).getarrayValue(1),b.ds.get(i).getarrayValue(2));
							dmin.add(seg);
						}		
				}
			}
			if(a.size() > minSize)
			{
				for (int i = minSize;i<a.size();i++)
				{
					dmin.add(a.ds.get(i-1));
				}
			}
			System.out.println("b. size is :" + b.size() + " Minsize is : "+minSize);
			if(b.size() > minSize)
			{
				for (int i = minSize;i<b.size();i++)
				{
					dmin.add(b.ds.get(i-1));
				}
			}
			return dmin;			
		}

		public static List<Segments> minConv(Curve minc1, Curve minc2, int timeperiod)
		{	
			double capT = 0;
			double b = 0;
			minc1.evaluateYatX(timeperiod);
			minc2.evaluateYatX(timeperiod);
			List<Segments> minCf = new ArrayList<>();
			if(minc1.size() == 1 && minc2.size() == 1)
				minCf = curveMin(minc1,minc2);

			else
			{
			if(minc1.getCellValue(0,0) == 0 && minc1.getCellValue(0, 1) == 0 )
			{
				capT = minc1.getCellValue(1, 0);
				b = minc2.getCellValue(1, 1);
				ArrayList<Double> minCval1 = new ArrayList<Double>();
				for(int i = 0 ;i < minc2.size();i ++)
				{
					minCval1.add(minc2.getCellValue(i, 0));
					minCval1.add(minc2.getCellValue(i, 1));
					minCval1.add(minc2.getCellValue(i, 2));

				}

				for (int i = 0; i < minCval1.size();i=i+3)
				{
					double newX = minCval1.get(i) + capT;
					minCval1.set(i,newX );
					//for (Double num:minCval1 )
					//System.out.println(num);
				}

				Curve minCValshift = new Curve(minCval1, 0);				
				minCf = curveMin(minCValshift,minc1);

				for(int i = 0; i < minCf.size(); i++) {
					System.out.println(minCf.get(i));
				}

			}
			else if(minc2.getCellValue(0,0) == 0 && minc2.getCellValue(0, 1) == 0)
			{
				capT = minc2.getCellValue(1, 0);
				b = minc2.getCellValue(1, 1);
				ArrayList<Double> minCval1 = new ArrayList<Double>();
				for(int i = 0 ;i < minc1.size();i ++)
				{
					minCval1.add(minc1.getCellValue(i, 0));
					minCval1.add(minc1.getCellValue(i, 1));
					minCval1.add(minc1.getCellValue(i, 2));

				}

				for (int i = 0; i < minCval1.size();i=i+3)
				{
					double newX = minCval1.get(i) + capT;
					minCval1.set(i,newX );
					//for (Double num:minCval1 )
					//System.out.println(num);
				}

				Curve minCValshift = new Curve(minCval1, 0);

				//for(int i =0; i<minCValshift.size();i++){System.out.println(minCValshift.getSegmentValue(i));}

				minCf = curveMin(minCValshift,minc2);

				for(int i = 0; i < minCf.size(); i++) {
					System.out.println(minCf.get(i));
				}

			}

			// ArrayList<Double> minCval1 = new ArrayList<Double>();
			// ArrayList<Double> minCval2 = new ArrayList<Double>();

			// minCval.add(0,0.0);
			// minCval.add(1, 0.0);
			// minCval.add(2,0.0);
			// for(double t = capT; t <= timeperiod; t = t + 0.01)
			// {
			// 	if(t >= capT)
			// 	{
			// 		yValue = Math.min(minc1.evaluateYatX(t),minc2.evaluateYatX(t));
			// 		mincslope = yValue / t; 
			// 		if(mincslope == 5)
			// 	}
			// }

						
			//Curve minCr = new Curve(minCf, 0);
		}
			return minCf;


		}


		public static List<Segments> maxConv(Curve maxc1, Curve maxc2, int timeperiod)
		{
			double capT = 0;
			double b = 0;
			maxc1.evaluateYatX(timeperiod);
			maxc2.evaluateYatX(timeperiod);
			List<Segments> maxCf = new ArrayList<>();
			if(maxc1.size() == 1 && maxc2.size() == 1)
				maxCf = curveMax(maxc1,maxc2);

			else
			{
			if(maxc1.getCellValue(0,0) == 0 && maxc1.getCellValue(0, 1) == 0 )
			{
				//capT = minc1.getCellValue(1, 0);
				b = maxc2.getCellValue(1, 1);
				ArrayList<Double> maxCval1 = new ArrayList<Double>();
				for(int i = 0 ;i < maxc1.size();i ++)
				{
					maxCval1.add(maxc1.getCellValue(i, 0));
					maxCval1.add(maxc1.getCellValue(i, 1));
					maxCval1.add(maxc1.getCellValue(i, 2));

				}

				for (int i = 3; i < maxCval1.size();i=i+3)
				{
					double newX = maxCval1.get(i) - b;
					maxCval1.set(i,newX );
					//for (Double num:minCval1 )
					//System.out.println(num);
				}

				Curve maxCValshift = new Curve(maxCval1, 0);				
				maxCf = curveMax(maxCValshift,maxc2);

				for(int i = 0; i < maxCf.size(); i++) {
					System.out.println(maxCf.get(i));
				}

			}
			else if(maxc2.getCellValue(0,0) == 0 && maxc2.getCellValue(0, 1) == 0)
			{
				//capT = maxc2.getCellValue(1, 0);
				b = maxc1.getCellValue(1, 1);
				ArrayList<Double> maxCval1 = new ArrayList<Double>();
				for(int i = 0 ;i < maxc2.size();i ++)
				{
					maxCval1.add(maxc2.getCellValue(i, 0));
					maxCval1.add(maxc2.getCellValue(i, 1));
					maxCval1.add(maxc2.getCellValue(i, 2));

				}

				for (int i = 0; i < maxCval1.size();i=i+3)
				{
					double newX = maxCval1.get(i) + capT;
					maxCval1.set(i,newX );
					//for (Double num:minCval1 )
					//System.out.println(num);
				}

				Curve maxCValshift = new Curve(maxCval1, 0);

				//for(int i =0; i<minCValshift.size();i++){System.out.println(minCValshift.getSegmentValue(i));}

				maxCf = curveMax(maxCValshift,maxc1);

				for(int i = 0; i < maxCf.size(); i++) {
					System.out.println(maxCf.get(i));
				}
			}	
			else 
			{
				System.out.println("input curves must start from x = 0");
			}
		}
		return maxCf;
		}
		

		public static Curve minDConv(Curve minc1, Curve minc2, int timeperiod)
		{
			double capT = 0;
			double b = 0;
			minc1.evaluateYatX(timeperiod);
			minc2.evaluateYatX(timeperiod);
			//List<Segments> maxCf = new ArrayList<>();
			ArrayList<Double> minCval1 = new ArrayList<Double>();
			
			if(minc1.getCellValue(0,0) == 0 && minc1.getCellValue(0, 1) != 0 )
			{
				capT = minc2.getCellValue(1, 0);
				b = minc1.evaluateYatX(capT);
				
				for(int i = 0 ;i < minc1.size();i ++)
				{
					minCval1.add(minc1.getCellValue(i, 0));
					minCval1.add(minc1.getCellValue(i, 1));
					minCval1.add(minc1.getCellValue(i, 2));

				}

				for (int i = 0; i < minCval1.size();i=i+3)
				{
					//double newX = minCval1.get(i) - capT;
					if(minCval1.get(i) == 0)
					minCval1.set(i+1,b );
					else 
					{
					double newX = minCval1.get(i) - capT;
					minCval1.set(i,newX );
					}

					//for (Double num:minCval1 )
					//System.out.println(num);
				}

				//Curve minCValshift = new Curve(minCval1, 0);				
				//minCf = curveMin(minCValshift,minc1);

				//for(int i = 0; i < minCf.size(); i++) {
				//	System.out.println(minCf.get(i));
				//}

			}
			else if(minc2.getCellValue(0,0) == 0 && minc2.getCellValue(0, 1) != 0)
			{
				capT = minc1.getCellValue(1, 0);
				b = minc2.evaluateYatX(capT);
				//ArrayList<Double> minCval1 = new ArrayList<Double>();
				for(int i = 0 ;i < minc2.size();i ++)
				{
					minCval1.add(minc2.getCellValue(i, 0));
					minCval1.add(minc2.getCellValue(i, 1));
					minCval1.add(minc2.getCellValue(i, 2));

				}

				for (int i = 0; i < minCval1.size();i=i+3)
				{
					if(minCval1.get(i) == 0)
						minCval1.set(i+1,b );
					else 
					{
						double newX = minCval1.get(i) - capT;
						minCval1.set(i,newX );
					}
					//for (Double num:minCval1 )
					//System.out.println(num);
				}

				//Curve minCValshift = new Curve(minCval1, 0);

				//for(int i =0; i<minCValshift.size();i++){System.out.println(minCValshift.getSegmentValue(i));}

				//minCf = curveMin(minCValshift,minc2);

				//for(int i = 0; i < minCf.size(); i++) {
				//	System.out.println(minCf.get(i));
				//}
			}
			Curve minCValshift = new Curve(minCval1, 0);
			return minCValshift;	
		}

		public static Curve maxDConv(Curve maxc1, Curve maxc2, int timeperiod)
		{
			double capT = 0;
			double b = 0;
			maxc1.evaluateYatX(timeperiod);
			maxc2.evaluateYatX(timeperiod);
			//List<Segments> maxCf = new ArrayList<>();
			ArrayList<Double> maxCval1 = new ArrayList<Double>();
			
			if(maxc1.getCellValue(0,0) == 0 && maxc1.getCellValue(0, 1) == 0 )
			{
				capT = maxc1.getCellValue(1, 0);
				b = maxc2.evaluateYatX(capT);
				
				for(int i = 0 ;i < maxc2.size();i ++)
				{
					maxCval1.add(maxc2.getCellValue(i, 0));
					maxCval1.add(maxc2.getCellValue(i, 1));
					maxCval1.add(maxc2.getCellValue(i, 2));

				}

				for (int i = 0; i < maxCval1.size();i=i+3)
				{
					//double newX = minCval1.get(i) - capT;
					if(maxCval1.get(i) == 0)
					maxCval1.set(i+1,0-b );
					else 
					{
					double newX = maxCval1.get(i) - capT;
					maxCval1.set(i,newX );
					maxCval1.set(i+1,(maxCval1.get(i-1) * newX - b ) );
					}
				}
			}
			Curve maxCValshift = new Curve(maxCval1,0);
			return maxCValshift;
		}			

		/*gets the value of an element from the curve arraylist*/
		public double getCellValue(int Cval, int Cval1){

			return  ds.get(Cval).getarrayValue(Cval1);
		}
		
		/**
		 * size returns the size of the curve
		 * @return
		 */
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
			System.out.println();
		}

		public double getX_intercept() {
			return x_intercept;
		}

		public void setX_intercept(double x_intercept) {
			this.x_intercept = x_intercept;
		}
	}


