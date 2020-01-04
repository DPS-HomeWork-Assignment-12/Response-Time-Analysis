import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Curve class consists basic operations which can be performed on curves along with minplus and maxplus operations
 * 
 * @version 1.0
 */
		public class Curve {
		private double x_intercept;
		private double y_intercept;//refers to the 'c' in straight line equation: y = m*x + c
		private double slope;//refers to 'm' in in straight line equation: y = m*x + c

		List<Segments> ds = new ArrayList<>();//List which stores the segments of the curve
		//private double[] segment;
		private double period = 0; //Flag which represents if the curve is periodic or not. O means not periodic.
		
		/*
		*getter and setter method for getting the X_intercept value and setting the X_intercept value.
		*/
		public double getX_intercept() {
			return x_intercept;
		}

		public void setX_intercept(double x_intercept) {
			this.x_intercept = x_intercept;
		}

		/*
		*getter and setter method for getting the y_intercept value and setting the y_intercept value.
		*/
		public double getY_intercept() {
			return y_intercept;
		}

		public void setY_intercept(double y_intercept) {
			this.y_intercept = y_intercept;
		}

		/*
		*getter and setter method for getting the slope value and setting the slope value.
		*/
		public double getSlope()
		{

			return slope;
		}

		public void setSlope(double slope) {
			this.slope = slope;
		}

		/*
		*getter and setter method for getting the ds arraylist value and setting the ds arraylist value.
		*/
		public List<Segments> getDs() {
			return ds;
		}

		public void setDs(List<Segments> ds) {
			this.ds = ds;
		}

		/*
		*getter and setter method for getting the period value and setting the period value.
		*/
		public double getPeriod() {
			return period;
		}

		public void setPeriod(double period) {
			this.period = period;
		}

		/*creating a curve with x-intercepts, y-intercepts and slope for different segments*/
		/**
		 * Curve Constructor which takes input values of any number
		 * To create curve objects for the Curve methods
		 * @param ...seg input numbers containing elements of the curve 
		 */
		public Curve(double ...seg) //...seg for taking any number of inputs
		{
			int j=0; 

			/*
			*Checking whether periodicity value is provided in the input or not. 
			*Each segment consists of starting points(x co-oridnate and y co-ordinate) and slope.
			*If there is no reminder when divided by 3, then the periodicity value is not provided in the input.
			*/
			if(seg.length % 3 == 0) 
			{	for(int i = 1;i<=(seg.length/3);i++)
				{

						Segments segment = new Segments(seg[j], seg[j+1], seg[j+2]); //store the 3 values in the 'segment' object by initializing constructor of Segments class
						//ds.add((Segments)(segment.add(seg[j], seg[j+1], seg[j+2])));
						ds.add(segment);	// now add the segment object to the 'ds' arraylist	
					j +=3; //increment counter by 3 to get the next set of 3 values.
				}
			}

			/*
			*Checking whether periodicity value is provided in the input or not. 
			*Each segment consists of starting points(x co-oridnate and y co-ordinate) and slope.
			*If there is reminder  of 1 when divided by 3, then the periodicity value is provided in the input.
			* This periodicity value is the last value from the inputs and it represents the units on X axis for which the curve repeats itself.
			*/
			else if (seg.length % 3 == 1)
			{
				period = seg[seg.length-1]; //Store the periodicity value in the variable 'period' of type double.
				//System.out.println("period is "+ period);
				for(int i = 1;i<=((seg.length/3)+1);i++)
				{
						/*
						*Checking if periodicity is greater than the starting points of any segment
						*/
						if(period > seg[j]) //if greater proceed as usual
						{
							Segments segment = new Segments(seg[j], seg[j+1], seg[j+2]);//store the 3 values in the 'segment' object by initializing constructor of Segments class
							//ds.add((Segments)(segment.add(seg[j], seg[j+1], seg[j+2])));
							ds.add(segment);// now add the segment object to the 'ds' arraylist	
						}
						else //if not greater then period variable refers to repeatibility of the curve again for every 'period' units.
						{	
							// the y co-ordiate and slope values are assessed based on the 'period' value and then stored in the segment object.
							Segments segment = new Segments(period,(seg[1]+(seg[j-1]*period) + (seg[j-2] -seg[j-1]*seg[j-3])),seg[2]);
							ds.add(segment);// now add the segment object to the 'ds' arraylist	
							i = (seg.length/3)+1;
						}
						j +=3;//increment counter by 3 to get the next set of 3 values.
			
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
			if(flag==1)//if the arraylist is obtained from the multiplication of two curves,flag is set to 1
			{
			int j=0;
				/*
				*Multiplication of two curves results in either parabolic, hyperbolic or elliptical curves and hence slope cannot be calculated.
				*Therefore only x co-oridnates and y co-ordinates are stored in the input Arraylist.
				*/
				if(curvearray.size() % 2 == 0)
				{	for(int i = 1;i<=(curvearray.size()/2);i++)//loop through the arraylist
					{

						Segments segment = new Segments(curvearray.get(j), curvearray.get(j+1));//store the 2 values in the 'segment' object by initializing constructor of Segments class
						ds.add(segment);	// now add the segment object to the 'ds' arraylist		
						j +=2; //increment counter by 2 to get the next set of co-ordinates.
					}
				}	
			}
			else //if flag is not equal to 1 then it is an general curve.
			{
				int j=0;
				if(curvearray.size() % 3 == 0)
				{	for(int i = 1;i<=(curvearray.size()/3);i++)//loop through the arraylist
					{

						Segments segment = new Segments(curvearray.get(j), curvearray.get(j+1),curvearray.get(j+2));//store the 3 values in the 'segment' object by initializing constructor of Segments class
						ds.add(segment);	// now add the segment object to the 'ds' arraylist		
						j +=3;//increment counter by 3 to get the next set of co-ordinates and slope values.
					}
				}	

			}

		}

		/**
		 * evaluateYatX To Evaluate Y value at a given X co-ordinate of any curve
		 * @param x Co-ordinate
		 * @return Y value at the given x co-ordinate on the curve
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
				for(int i = 0;i<ds.size();i++)//looping through the curve arraylist
				{
					
					if(x >= ds.get(i).getarrayValue(0))//if condition to identify the segment the x value belongs to
					{
						slope = ds.get(i).getarrayValue(2); //get the slope value from the corresponding segment
						y_intercept = ds.get(i).getarrayValue(1) - slope * ds.get(i).getarrayValue(0);//calculate y_intercept value, which equals (y - slope * x).
						y = (slope * x) + y_intercept;//calculate y value which equals (slope * x + y_intercept)
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
						
						if(x >= ds.get(i).getarrayValue(0))//if condition to identify the segment the x value belongs to
						{
							slope = ds.get(i).getarrayValue(2); //get the slope value from the corresponding segment
							y_intercept = ds.get(i).getarrayValue(1) - slope * ds.get(i).getarrayValue(0);//calculate y_intercept value, which equals (y - slope * x).
							y = (slope * x) + y_intercept;//calculate y value which equals (slope * x + y_intercept)
						}
					}
					//System.out.println("y_intercept is : " + y_intercept);
					//System.out.println("slope is : "+ slope);
				}
				else//if the period starts after all the segments start, implements below
				{
					double xVal = 0; 
					int numSeg = ds.size() - 1; //stores number of segments in the curve arraylist
					/*
					* A do while loop to add the new values to the curve arraylist till it reaches the x value 
					*/
					do
					{
						int size=ds.size();
						//System.out.println("size is " +size);
						xVal = ds.get(size-1).getarrayValue(0)+ds.get(size-numSeg).getarrayValue(0)-ds.get(size-numSeg-1).getarrayValue(0); //Calculates the x value of the next segment to be stored in the arraylist
						double slopeVal = ds.get(size-numSeg).getarrayValue(2);//Calculates the slope value of the next segment to be stored in the arraylist
						double yVal = ds.get(size-1).getarrayValue(1)+ds.get(size-numSeg).getarrayValue(1)-ds.get(size-numSeg-1).getarrayValue(1);//Calculates the y value of the next segment to be stored in the arraylist
						Segments segment = new Segments(xVal, yVal, slopeVal); // Add the above 3 values to the segment object
						ds.add(segment);// add the segement to the curve arraylist

					}while(xVal <= x); // repeat this till X value is less than or equal to the input x value.

					for(int i = 0;i<ds.size();i++)
					{
						
						if(x >= ds.get(i).getarrayValue(0))//if condition to identify the segment the x value belongs to
						{
							slope = ds.get(i).getarrayValue(2);//get the slope value from the corresponding segment
							y_intercept = ds.get(i).getarrayValue(1) - slope * ds.get(i).getarrayValue(0);//calculate y_intercept value, which equals (y - slope * x).
							y = (slope * x) + y_intercept;//calculate y value which equals (slope * x + y_intercept)
						}
					}
					//System.out.println("y_intercept is : " + y_intercept);
					//System.out.println("slope is : "+ slope);
				}
			}
			return y;
		}
		
		/**
		 * evaluateSatX To Evaluate slope value at a given X co-ordinate of any curve
		 * @param x Co-ordinate
		 * @return slope value at the given x co-ordinate on the curve
		 */
		public double evaluateSatX(double x)
		{
			double y = evaluateYatX(x);//Calculating Y value at the given X value.
			for(int i = 0;i<ds.size();i++)
			{
				if(y >= ds.get(i).getarrayValue(1))//if condition to identify the segment the y value belongs to
				{
					slope = ds.get(i).getarrayValue(2); //get the slope value from the corresponding segment
					
				}
				//System.out.println("x value: "+ x + " y value: "+ y + " slope: "+ slope);
			}
			return slope;	 

		}

		/**
		 * multiplicationOfTwoCurves Multiplies two curve objects and returns the resultant curve object
		 * @param c1 First Curve
		 * @param c2 Second Curve
		 * @param timePeriod Time period until which the two curves are multiplied
		 * @return c3 Resultant Curve which consists of only x and y co-ordinates without slope value
		 */
		public static  Curve multiplicationOfTwoCurves(Curve c1, Curve c2, double timePeriod)
		{
			c1.evaluateYatX(timePeriod); 	// Using evaluateYatX method to expand the arraylist till timeperiod
			c2.evaluateYatX(timePeriod); 	// Using evaluateYatX method to expand the arraylist till timeperiod
			ArrayList<Double> al1 = new ArrayList<>(); // new arraylist to store the the x co-ordinates
			/*
			*for loop to get the x-co-ordinates from the first curve and store them in an arraylist
			*/
			for (int i = 0; i < c1.size(); i++)
				al1.add(c1.getCellValue(i,0));

			/*
			*for loop to get the x-co-ordinates from the second curve and store them in an arraylist
			*/
			for (int i = 0; i < c2.size(); i++)
				al1.add(c2.getCellValue(i,0));

			al1.add(IntersectionOfCurves(c1,c2,timePeriod));//checking and adding intersection point of two curves.
			Collections.sort(al1);//sort the elements in the arraylist in ascending order
			List<Double> al2 = al1.stream().distinct().collect(Collectors.toList()); //remove duplicate elements in the arraylist
			ArrayList<Double> al3 = new ArrayList<>(); //new arraylist to store resultant values of multplication of the curves

			for(double xval : al2 )//loop through each element of arraylist holding unique x values
			{
				al3.add(xval);// add the x value to the new arraylist

				/*
				*add the resultant y value to the new arraylist by 
				*multiplying the corresponding y values at the given x value of both curves
				*/
				al3.add(c1.evaluateYatX(xval) * c2.evaluateYatX(xval));
				
			}
			Curve c3 = new Curve(al3,1); // new curve object created with the input as the final resultant arraylist
			return c3; 

		}

		/**
		 * Ceil method implments the ceil of the curve for a given curve upto the given timeperiod and returns the resultant curve
		 * @param curve1 Input curve
		 * @param timePeriod Timeperiod until which the curve must be ceiled
		 * @return ceiledcurve
		 */
		public static Curve ceil(Curve curve1, int timePeriod)
		{
			ArrayList<Double> ceil1 = new ArrayList<>(); // To store the values of the ceiled curve
			double slope = 0; //ceiled curve is divided into segments with zero slope.
			curve1.evaluateYatX(timePeriod);  // expanding the curve through the evaluateYatX method till X is timePeriod
			for (int i =0; i<timePeriod;i++) //Y value is evaluated in increments of 1 on x axis till it reaches timeperiod
			{
			if(curve1.EvaluateXatY(i) <= timePeriod) // condition to check if the X value at given Y value is less than the time period
				{
					ceil1.add(curve1.EvaluateXatY(i));//add the x value evaluated at the given y value of the segment
					ceil1.add((double)i+1);//add the y value which is the starting point of the segment
					ceil1.add(slope);// add slope value which is zero for each segment
				}	
			}
			Curve ceiledcurve = new Curve(ceil1,2); //create the curve object with input as the ceil1 arraylist containing x,y and slope values of each segment
			return ceiledcurve;
		}

		/**
		 * floor method implements the floor of the curve for a given curve upto the given timeperiod and returns the resultant curve
		 * @param curve1 Input curve
		 * @param timePeriod imeperiod until which the curve must floored
		 * @return flooredcurve
		 */
		public static Curve floor(Curve curve1, int timePeriod)
		{
			ArrayList<Double> floor1 = new ArrayList<>(); // To store the values of the floored curve
			double slope = 0;//floored curve is divided into segments with zero slope.
			curve1.evaluateYatX(timePeriod); // expanding the curve through the evaluateYatX method till X is timePeriod
			for (int i =0; i<timePeriod;i++) //Y value is evaluated in increments of 1 on x axis till it reaches timeperiod
			{
			if(curve1.EvaluateXatY(i) <= timePeriod)// condition to check if the X value at given Y value is less than the time period
				{
					floor1.add(curve1.EvaluateXatY(i));//add the x value evaluated at the given y value of the segment
					floor1.add((double)i); //add the y value which is the starting point of the segment
					floor1.add(slope); // add slope value which is zero for each segment
				}	
			}
			Curve flooredcurve = new Curve(floor1,2); //create the curve object with input as the floor1 arraylist containing x,y and slope values of each segment
			return flooredcurve;
		}


		/**
		 * EvaluateXatY Method returns the value of X at a given Y of the input curve
		 * @param y y co-ordinate at which the x value is required
		 * @return x x co-ordinate at the given y co-ordinate
		 */
		public double EvaluateXatY(double y)
		{
			double x = 0; //intializing variable
			for(int i = 0;i<ds.size();i++)//looping through the curve arraylist
				{
					if(y > ds.get(i).getarrayValue(1))//if condition to identify the segment the y value belongs to
					{
						slope = ds.get(i).getarrayValue(2); //get the slope value from the corresponding segment
						y_intercept = ds.get(i).getarrayValue(1) - slope * ds.get(i).getarrayValue(0);//calculate y_intercept value, which equals (y - slope * x).
						x = (y - y_intercept)/(slope);//calculate x value 
					}
				}	
			return x;

		}
		
		/**
		 * scaleOnX To scale the curve on X axis by a scaling factor.
		 * @param curve1 Input Curve
		 * @param scalingFactor Factor by which the X co-ordinates are scaled.
		 * @return Scaled Curve on X axis
		 */
		public  static Curve scaleOnX(Curve curve1, double scalingFactor)
		{
			Curve scaledCurve;//Initialize Curve object to store the result curve
			double slope1;//Initialize variable to store slope values
			ArrayList<Double> ScaleOnX = new ArrayList<>(); //Initialize an array list for easy computation.
			
			/*
			 * For loop to store all values of the input curve into the arraylist
			 */
			for (int i = 0; i < curve1.size(); i++)
			{
			ScaleOnX.add(curve1.getCellValue(i,0));
			ScaleOnX.add(curve1.getCellValue(i,1));
			ScaleOnX.add(curve1.getCellValue(i,2));
			}

			/*
			 * Multiply all the x values by the scaling factor
			 */
			for(int i =0; i <ScaleOnX.size(); i = i+3)
			{
				ScaleOnX.set(i, ScaleOnX.get(i) * scalingFactor );
			}

			/*
			 * For computing the new slope values and storing them
			 */
			for(int i= 2; i<ScaleOnX.size(); i = i+3 )
			{
				if(i<=ScaleOnX.size()-4)//condition to check if it is not the last segment
				{
					/*
					 * slope is calculated using mathematical approach of 'dy/dx'.
					 */
					slope1 = (ScaleOnX.get(i+2) - ScaleOnX.get(i-1) )/(ScaleOnX.get(i+1) - ScaleOnX.get(i-2));
					ScaleOnX.set(i, slope1);
				}
				else //If last segement, compute slope by below process
				{
					/*
					 * slope is calculated by computing the next x and y values at an increment of 0.01 and then use dy/dx.
					 */
					slope1 = (ScaleOnX.get(i-1) - curve1.evaluateYatX(curve1.getCellValue(curve1.size()-1, 0)+0.01))/(ScaleOnX.get(i-2) - (curve1.getCellValue(curve1.size()-1, 0)+0.01)*scalingFactor);
					ScaleOnX.set(i, slope1);
				}
			}
			scaledCurve = new Curve(ScaleOnX, 2);//Constructor to store the elements in the curve object
			return scaledCurve;
		}

		/**
		 * scaleOnY To scale the curve on Y axis by a scaling factor.
		 * @param curve1 Input Curve
		 * @param scalingFactor Factor by which the Y co-ordinates are scaled.
		 * @return Scaled Curve on Y axis
		 */
		public  static Curve scaleOnY(Curve curve1, double scalingFactor)
		{
			Curve scaledCurve;//Initialize Curve object to store the result curve
			double slope1;//Initialize variable to store slope values
			ArrayList<Double> ScaleOnY = new ArrayList<>(); //Initialize an array list for  computation.
			
			/*
			 * For loop to store all values of the input curve into the arraylist
			 */
			for (int i = 0; i < curve1.size(); i++)
			{
			ScaleOnY.add(curve1.getCellValue(i,0));
			ScaleOnY.add(curve1.getCellValue(i,1));
			ScaleOnY.add(curve1.getCellValue(i,2));
			}

			/*
			 * Multiply all the y values by the scaling factor
			 */
			for(int i =1; i <ScaleOnY.size(); i = i+3)
			{
				ScaleOnY.set(i, ScaleOnY.get(i) * scalingFactor );
			}

			/*
			 * For computing the new slope values and storing them
			 */
			for(int i= 2; i<ScaleOnY.size(); i = i+3 )
			{
				if(i<=ScaleOnY.size()-4)//condition to check if it is not the last segment
				{
					/*
					 * slope is calculated using mathematical approach of 'dy/dx'.
					 */
					slope1 = (ScaleOnY.get(i-1) - ScaleOnY.get(i+2))/(ScaleOnY.get(i-2) - ScaleOnY.get(i+1));
					ScaleOnY.set(i, slope1);
				}
				else //If last segement, compute slope by below process
				{
					/*
					 * slope is calculated by computing the next x and y values at an increment of 0.01 and then use dy/dx.
					 */
					slope1 = ((curve1.evaluateYatX(curve1.getCellValue(curve1.size()-1, 0)+0.01))*scalingFactor - ScaleOnY.get(i-1))/((curve1.getCellValue(curve1.size()-1, 0)+0.01)- ScaleOnY.get(i-2));
					ScaleOnY.set(i, slope1);
				}
			}
			scaledCurve = new Curve(ScaleOnY, 2); //Constructor to store the elements in the curve object
			return scaledCurve;
		}
		

		/**
		 * Invert To compute the inverse of the given curve.
		 * @param curve1 Input Curve
		 * @return Inverted curve
		 */
		public static Curve Invert(Curve curve1)
		{
			Curve Inverted;//Initialize Curve object to store the result curve
			double slope1;//Initialize variable to store slope values
			ArrayList<Double> Invert = new ArrayList<>();  //Initialize an array list for computation.
			
			/*
			 * If condition to check if the inverted curve should
			 * start at x and y co-ordinates (0,0)
			 */
			if(curve1.getCellValue(0, 1)!=0)
			{
				Invert.add(0,0.0);
				Invert.add(0,0.0);
				Invert.add(0,0.0);
			}

			/*
			 * Store the values in the Arraylist by inverting x and y values
			 */
			for (int i = 0; i < curve1.size(); i++)
			{
				Invert.add(curve1.getCellValue(i,1));
				Invert.add(curve1.getCellValue(i,0));
				Invert.add(curve1.getCellValue(i,2));
			}

			/*
			 * For computing the new slope values and storing them
			 */
			for(int i= 2; i<Invert.size(); i = i+3 )
			{
				if(i<=Invert.size()-4)//condition to check if it is not the last segment
				{
					/*
					 * slope is calculated using mathematical approach of 'dy/dx'.
					 */
					slope1 = (Invert.get(i+2) - Invert.get(i-1))/(Invert.get(i+1) - Invert.get(i-2));
					Invert.set(i, slope1);
				}
				else//If last segement, compute slope by below process
				{
					/*
					 * slope is calculated by computing the next x and y values at an increment of 0.01 and then use dy/dx.
					 */
					slope1 = ( Invert.get(i-1) - (curve1.getCellValue(curve1.size()-1, 0)+0.01))/(Invert.get(i-2) - (curve1.evaluateYatX(curve1.getCellValue(curve1.size()-1, 0)+0.01)));
					Invert.set(i, slope1);
				}
			}
			Inverted = new Curve(Invert, 2);//Constructor to store the elements in the curve object
			return Inverted;
		}

		public static Curve affine(Curve curve1, double scalingFactor, double x_offset)
		{
			Curve affine1;
			affine1= scaleOnX(curve1, scalingFactor);
			for(int i =0; i<affine1.size();i++){
				System.out.println(affine1.getSegmentValue(i));
			 }
			Curve affine2;
			affine2 = shiftOnX(affine1,x_offset);
			return affine2;
		}

		public static Curve shiftOnX(Curve curve1, double x_offset)
		{
			
			ArrayList<Double> Shifter = new ArrayList<>();
			Curve shifted;
			if(x_offset>0)
			{
				Shifter.add(0.0);
				Shifter.add(0.0);
				Shifter.add(0.0);
				for (int i = 0; i < curve1.size(); i++)
				{
					if(curve1.getCellValue(i,1) ==0 && curve1.getCellValue(i,2)==0)
					{

					}
					else 
					{
						Shifter.add(curve1.getCellValue(i,0) + x_offset);
						Shifter.add(curve1.getCellValue(i,1));
						Shifter.add(curve1.getCellValue(i,2));
					}	
				}								
			}
			else
			{
				for (int i = 0; i < curve1.size(); i++)
				{
					if(curve1.getCellValue(i,0) + x_offset < 0)
					{
						Shifter.add(0.0);
						Shifter.add(curve1.evaluateYatX(Math.abs(x_offset)));
						Shifter.add(curve1.getCellValue(i,2));
					}
					else
					{
					Shifter.add(curve1.getCellValue(i,0) + x_offset);
					Shifter.add(curve1.getCellValue(i,1));
					Shifter.add(curve1.getCellValue(i,2));
					}
				}			
			}
			shifted = new Curve(Shifter,2);
			return shifted;
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


		public static  Curve addCurve(Curve c1, Curve c2, double timePeriod)
		{
			c1.evaluateYatX(timePeriod); 	// Using evaluateYatX method to expand the arraylist till timeperiod
			c2.evaluateYatX(timePeriod);	// Using evaluateYatX method to expand the arraylist till timeperiod
			ArrayList<Double> al1 = new ArrayList<>(); // new arraylist to store the the x co-ordinates
			/*
			*for loop to get the x-co-ordinates from the first curve and store them in an arraylist
			*/
			for (int i = 0; i < c1.size(); i++)
				al1.add(c1.getCellValue(i,0));

			/*
			*for loop to get the x-co-ordinates from the second curve and store them in an arraylist
			*/
			for (int i = 0; i < c2.size(); i++)
				al1.add(c2.getCellValue(i,0));

			

			Collections.sort(al1);//sort the elements in the arraylist in ascending order
			List<Double> al2 = al1.stream().distinct().collect(Collectors.toList()); //remove duplicate elements in the arraylist
			ArrayList<Double> al3 = new ArrayList<>(); //new arraylist to store resultant values of multplication of the curves

			for(double xval : al2 )//loop through each element of arraylist holding unique x values
			//for (int i = 0; i )
			{
				al3.add(xval);// add the x value to the new arraylist

				/*
				*add the resultant y value to the new arraylist by 
				*multiplying the corresponding y values at the given x value of both curves
				*/
				al3.add(c1.evaluateYatX(xval) + c2.evaluateYatX(xval));
				al3.add(c1.evaluateSatX(xval)+ c2.evaluateSatX(xval));
				
			}
			Curve c3 = new Curve(al3,2); // new curve object created with the input as the final resultant arraylist
			return c3; 
		}		
		
		public static  Curve curveMax(Curve c1, Curve c2, double timePeriod)
		{
			c1.evaluateYatX(timePeriod); 	// Using evaluateYatX method to expand the arraylist till timeperiod
			c2.evaluateYatX(timePeriod);	// Using evaluateYatX method to expand the arraylist till timeperiod
			ArrayList<Double> al1 = new ArrayList<>(); // new arraylist to store the the x co-ordinates
			/*
			*for loop to get the x-co-ordinates from the first curve and store them in an arraylist
			*/
			for (int i = 0; i < c1.size(); i++)
				al1.add(c1.getCellValue(i,0));

			/*
			*for loop to get the x-co-ordinates from the second curve and store them in an arraylist
			*/
			for (int i = 0; i < c2.size(); i++)
				al1.add(c2.getCellValue(i,0));

			al1.add(IntersectionOfCurves(c1,c2,timePeriod));	

		
			Collections.sort(al1);//sort the elements in the arraylist in ascending order
			List<Double> al2 = al1.stream().distinct().collect(Collectors.toList()); //remove duplicate elements in the arraylist
			ArrayList<Double> al3 = new ArrayList<>(); //new arraylist to store resultant values of multplication of the curves

			for(double xval : al2 )//loop through each element of arraylist holding unique x values
			//for (int i = 0; i )
			{
				al3.add(xval);// add the x value to the new arraylist

				/*
				*add the resultant y value to the new arraylist by 
				*multiplying the corresponding y values at the given x value of both curves
				*/
				if(c1.evaluateYatX(xval) > c2.evaluateYatX(xval) )
				{
					al3.add(c1.evaluateYatX(xval));
					al3.add(c1.evaluateSatX(xval));

				}
				
				else
				{
					al3.add(c2.evaluateYatX(xval));
					al3.add(c2.evaluateSatX(xval));

				}
				
			}
			Curve c3 = new Curve(al3,2); // new curve object created with the input as the final resultant arraylist
			return c3; 
		}		
		
		public static double IntersectionOfCurves(Curve curve1, Curve curve2, double timeperiod)
		{
			double Ipoint = 0;
			final double THRESHOLD = .01;
			for(double i = 0; i<timeperiod; i = i+ 0.01 )
			{
				if(Math.abs(curve1.evaluateYatX(i) - curve2.evaluateYatX(i)) < THRESHOLD)
				{
					Ipoint = i;
				}
			}
			DecimalFormat newFormat = new DecimalFormat("##########.###");
			double twoDecimal =  Double.valueOf(newFormat.format(Ipoint));
			return twoDecimal;
		}

		public static  Curve curveMin(Curve c1, Curve c2, double timePeriod)
		{
			c1.evaluateYatX(timePeriod); 	// Using evaluateYatX method to expand the arraylist till timeperiod
			c2.evaluateYatX(timePeriod);	// Using evaluateYatX method to expand the arraylist till timeperiod
			ArrayList<Double> al1 = new ArrayList<>(); // new arraylist to store the the x co-ordinates
			/*
			*for loop to get the x-co-ordinates from the first curve and store them in an arraylist
			*/
			for (int i = 0; i < c1.size(); i++)
				al1.add(c1.getCellValue(i,0));

			/*
			*for loop to get the x-co-ordinates from the second curve and store them in an arraylist
			*/
			for (int i = 0; i < c2.size(); i++)
				al1.add(c2.getCellValue(i,0));

			al1.add(IntersectionOfCurves(c1,c2,timePeriod));
			Collections.sort(al1);//sort the elements in the arraylist in ascending order
			List<Double> al2 = al1.stream().distinct().collect(Collectors.toList()); //remove duplicate elements in the arraylist
			ArrayList<Double> al3 = new ArrayList<>(); //new arraylist to store resultant values of multplication of the curves

			for(double xval : al2 )//loop through each element of arraylist holding unique x values
			//for (int i = 0; i )
			{
				al3.add(xval);// add the x value to the new arraylist

				/*
				*add the resultant y value to the new arraylist by 
				*multiplying the corresponding y values at the given x value of both curves
				*/
				if(c1.evaluateYatX(xval) <= c2.evaluateYatX(xval) )
				{
					al3.add(c1.evaluateYatX(xval));
					al3.add(c1.evaluateSatX(xval));

				}
				
				else
				{
					al3.add(c2.evaluateYatX(xval));
					al3.add(c2.evaluateSatX(xval));

				}
				
			}
			Curve c3 = new Curve(al3,2); // new curve object created with the input as the final resultant arraylist
			return c3; 
		}		

		public static Curve minConv(Curve minc1, Curve minc2, int timeperiod)
		{	
			double capT = 0;
			//double b = 0;
			minc1.evaluateYatX(timeperiod);
			minc2.evaluateYatX(timeperiod);
			Curve minCf;
			if(minc1.size() == 1 && minc2.size() == 1)
				minCf = curveMin(minc1,minc2,timeperiod);

			else
			{
			if(minc1.getCellValue(0,0) == 0 && minc1.getCellValue(0, 1) == 0 )
			{
				capT = minc1.getCellValue(1, 0);
				//b = minc2.getCellValue(1, 1);
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
				minCf = curveMin(minCValshift,minc1,timeperiod);

			}
			else if(minc2.getCellValue(0,0) == 0 && minc2.getCellValue(0, 1) == 0)
			{
				capT = minc2.getCellValue(1, 0);
				//b = minc2.getCellValue(1, 1);
				Curve minCval1;
				minCval1 = shiftOnX(minc1, capT);
				for(int i =0; i<minCval1.size();i++){
					System.out.println(minCval1.getSegmentValue(i));
				 }

/* 				ArrayList<Double> minCval1 = new ArrayList<Double>();
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
				} */

				//Curve minCValshift = new Curve(minCval1, 0);

				//for(int i =0; i<minCValshift.size();i++){System.out.println(minCValshift.getSegmentValue(i));}

				minCf = curveMin(minCval1,minc2,timeperiod);

			}
			else 
			minCf = null;
		}
			return minCf;


		}


		public static Curve maxConv(Curve maxc1, Curve maxc2, int timeperiod)
		{
			//double capT = 0;
			double b = 0;
			maxc1.evaluateYatX(timeperiod);
			maxc2.evaluateYatX(timeperiod);
			Curve maxCf;
			if(maxc1.size() == 1 && maxc2.size() == 1)
			{
			maxCf = Curve.curveMax(maxc1,maxc2,timeperiod);
			}
			else
			{
				if(maxc1.getCellValue(0,0) == 0 && maxc1.getCellValue(0, 1) == 0 )
				{
					//capT = minc1.getCellValue(1, 0);
					b = maxc2.getCellValue(0, 1);
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
					for(int i =0; i<maxCValshift.size();i++){
						System.out.println(maxCValshift.getSegmentValue(i));
					 }
								
					maxCf = Curve.curveMax(maxCValshift,maxc2,timeperiod);

				}
				else if(maxc2.getCellValue(0,0) == 0 && maxc2.getCellValue(0, 1) == 0)
				{
					//capT = maxc2.getCellValue(1, 0);
					b = maxc1.getCellValue(0, 1);
					ArrayList<Double> maxCval1 = new ArrayList<Double>();
					for(int i = 0 ;i < maxc2.size();i ++)
					{
						maxCval1.add(maxc2.getCellValue(i, 0));
						maxCval1.add(maxc2.getCellValue(i, 1));
						maxCval1.add(maxc2.getCellValue(i, 2));

					}

					for (int i = 0; i < maxCval1.size();i=i+3)
					{
						double newX = maxCval1.get(i) - b;
						maxCval1.set(i,newX );
						//for (Double num:minCval1 )
						//System.out.println(num);
					}

					Curve maxCValshift = new Curve(maxCval1, 0);
					for(int i =0; i<maxCValshift.size();i++){
						System.out.println(maxCValshift.getSegmentValue(i));
					 }
						
					//for(int i =0; i<minCValshift.size();i++){System.out.println(minCValshift.getSegmentValue(i));}

					maxCf = Curve.curveMax(maxCValshift,maxc1,timeperiod);

				}	
				else 
				{
					System.out.println("input curves must start from x = 0");
					maxCf = null;
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
			else if(maxc2.getCellValue(0,0) == 0 && maxc2.getCellValue(0, 1) == 0)
			{	
				capT = maxc2.getCellValue(1, 0);
				b = maxc1.evaluateYatX(capT);
				
				for(int i = 0 ;i < maxc1.size();i ++)
				{
					maxCval1.add(maxc1.getCellValue(i, 0));
					maxCval1.add(maxc1.getCellValue(i, 1));
					maxCval1.add(maxc1.getCellValue(i, 2));

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

		/**
		 * getCellValue gets the value of an element from the curve arraylist
		 * @param Cval row value
		 * @param Cval1 column value
		 * @return value of the element at the corresponding row and column
		 */
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
		/**
		 * getSegmentValue Getting the value of each segment
		 * @param get input row value
		 * @return output elements of the row
		 */
		public List<Double> getSegmentValue(int get) {
			
			return  ds.get(get).getValue();
			
		}
		
		/**
		 * printCurve Method to print the output in the format of a curve
		 * @param ds input arraylist consisting of the curve elements
		 */
		public static void printCurve(List<Segments> ds) {
			
			for(int i=0;i<ds.size();i++)
			{
				for(int j=0;j<3;j++)
				{
					System.out.print(" " + ds.get(i).getarrayValue(j));
				}
			}
			System.out.println();
		}

		
		
	}