	import java.util.ArrayList;
	import java.util.List;

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
					int numSeg= ds.size() -1;
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
				//	System.out.println("y_intercept is : " + y_intercept);
				//	System.out.println("slope is : "+ slope);
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
		
		
		public List multiplyScalar(int scalar)
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
		
		public static List addCurve(Curve x,Curve y) {
		
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
		
		public static List curveMax(Curve a, Curve b)
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
		
		public static List curveMin(Curve a, Curve b)
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

		public double getX_intercept() {
			return x_intercept;
		}

		public void setX_intercept(double x_intercept) {
			this.x_intercept = x_intercept;
		}
		
		
	}


