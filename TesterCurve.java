import java.util.ArrayList;
import java.util.List;

public class TesterCurve {
	public static void main(String args[])
	{
		List<Double> segment_val = new ArrayList<>();
		Curve Line = new Curve(0,0,1,2,2,0.25,5);
		//System.out.println(segment_val);
		//double y = Line.evaluateYatX(38);
		//System.out.println("Y evaluated at x is "+y);
		for(int i =0; i<Line.size();i++){
			System.out.println(Line.getSegmentValue(i));
		}
		Curve Line1 = new Curve(0,5,1,10,15,2,15);
		for(int i =0; i<Line1.size();i++){
			System.out.println(Line1.getSegmentValue(i));
		}
		
		Curve mul1 = new Curve(Curve.multiplicationOfTwoCurves(Line,Line1));
		for(int i =0; i<mul1.size();i++){
			System.out.println(mul1.getSegmentValue(i));
		}
		//Line.scaleX(2);
		//for(int i=0;i<segement_val.size();i++)
		//System.out.println("after scaling :" + segement_val.get(i));
	//	Line.getSegmentValue(1);
	//	Line.getSegmentValue(2);
	//	Line.getSegmentValue(3);
	//	Line.getSegmentValue(4);
	}

}

