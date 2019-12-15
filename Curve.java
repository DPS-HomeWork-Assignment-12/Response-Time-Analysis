import java.util.ArrayList;
import java.util.List;

public class Curve {
    private double x_intercept;
    private double y_intercept;
    private double slope;
    private double[] segment;
    
    Segments ds = new Segments();

/*creating a single linear curve of one segment with slope and x,y intercepts*/
    public Curve(double x_intercept, double y_intercept, double slope) 
    {
        this.x_intercept = x_intercept;
        this.y_intercept = y_intercept;
        this.slope = slope;
        
        Segments x = new Segments(this.x_intercept,this.y_intercept,this.slope);
        
        System.out.println("Segment 1 is : "+ x.toString());
        //to do to pass the values and store in segments;
        
        
        
    }
    
    public Curve(double ...seg) 
    {
        for(int i = 0;i<seg.length;i++)
        {
            Segments ds = new Segments(this.x_intercept, this.y_intercept, this.slope);
            ds.getValue();
        }
    }
    


    public double evaluateY(double x)
    {
        double slope = (double)fs.get(2) ;        
        double x1 = x;
        double y_intercept = (double)fs.get(1) - slope * (double)fs.get(0);
        return slope * x1 + y_intercept;
    }
    
    public Curve scaleX(Curve in,double scaleX)
    {
        Curve out = null;
        return out;
    }
}
