import java.util.ArrayList;
import java.util.List;

public class TesterCurve {
	@SuppressWarnings("unchecked")
	public static void main(String args[])
	{
		//List<>
		List<Segments> segment_val = new ArrayList<>();
		Curve Line1 = new Curve(0,0,1,5);
		//System.out.println(segment_val);
		//double y = Line.evaluateYatX(10);
		//System.out.println("Y evaluated at x is "+y);
		//for(int i =0; i<Line.size();i++){
			//System.out.println(Line.getSegmentValue(i));
		//}
		
		
		//Line.multiplyScalar(5);
		//segment_val = Line.multiplyScalar(5);
		//Line.getCurve(segment_val);
		
		Curve Line2 =  new Curve(0,0,2,10,20,5,20);
		segment_val = Curve.addCurve(Line1, Line2);
		//segment_val = Curve.curveMin(Line1, Line2);
		Curve.getCurve(segment_val);
		
		
		//Line.scaleX(2);
		//for(int i=0;i<segment_val.size();i++)
		//System.out.println("after scaling :" + segment_val.get(i));
	//	Line.getSegmentValue(1);
	//	Line.getSegmentValue(2);
	//	Line.getSegmentValue(3);
	//	Line.getSegmentValue(4);
	}

}

