import java.util.ArrayList;
import java.util.List;

public class TesterCurve {
	public static void main(String args[])
	{
		List<Double> segment_val = new ArrayList<>();
		Curve Line = new Curve(0,0,1,2,2,0.25,12);
		System.out.println(segment_val);
		double y = Line.evaluateYatX(38);
		System.out.println("Y evaluated at x is "+y);
		for(int i =0; i<Line.size();i++){
			System.out.println(Line.getSegmentValue(i));
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

