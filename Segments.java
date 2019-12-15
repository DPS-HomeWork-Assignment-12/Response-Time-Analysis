import java.util.ArrayList;

public class Segments {
    private ArrayList fs = new ArrayList<>();
    public Segments(double x_coordinate,double y_coordinate,double slope)
    {
        this.fs.add(x_coordinate);
        this.fs.add(y_coordinate);
        this.fs.add(slope);
        
    }
    public void getValue()
    {
        for(int i = 0; i < fs.size(); i++)
        {   
            System.out.println(fs.get(i)+" ");
        }  
    }
    
    public double EvalAtX(double x){
        
        double slope = (double)fs.get(2) ;        
        double x1 = x;
        double y_intercept = (double)fs.get(1) - slope * (double)fs.get(0);
        return slope * x1 + y_intercept;
    }
    
    public Segments() {
        // TODO Auto-generated constructor stub
    }

}