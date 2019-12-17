import java.util.ArrayList;
import java.util.List;

public class TesterCurve {
	public static void main(String args[])
	{
		List<Double> segment_val = new ArrayList<>();
		Curve Line = new Curve(0.0,0.0,5.0,5.0,25,0);
		segment_val = Line.getSegmentValue(0);
		System.out.println(segment_val);
		double y = Line.evaluateX(2);
		System.out.println(y);
		
		Line.scaleX(2);
		//for(int i=0;i<segement_val.size();i++)
		//System.out.println("after scaling :" + segement_val.get(i));
	//	Line.getSegmentValue(1);
	//	Line.getSegmentValue(2);
	//	Line.getSegmentValue(3);
	//	Line.getSegmentValue(4);
	}

}

