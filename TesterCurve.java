public class TesterCurve {
    public static void main(String[] args)
    {
        Segments Line = new Segments(2,2,0);
        Line.getValue();
        double evalatx = Line.EvalAtX(3.5);
        System.out.println("y is : "+ evalatx);
    }
}
        
