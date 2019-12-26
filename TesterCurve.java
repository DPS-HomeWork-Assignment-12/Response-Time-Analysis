import java.util.ArrayList;
import java.util.List;

public class TesterCurve {
	@SuppressWarnings("unchecked")
	public static void main(String args[])
	{
		List<Segments> segment_val = new ArrayList<>();
		Curve Line1 = new Curve(0,0,1,2,2,0.25,5);
		//System.out.println(segment_val);
		//double y = Line.evaluateYatX(38);
		//System.out.println("Y evaluated at x is "+y);
		//for(int i =0; i<Line1.size();i++){
		//	System.out.println(Line1.getSegmentValue(i));
		//}
		Curve Line2 = new Curve(0,5,1,10,15,2,15);
		
		//for(int i =0; i<Line2.size();i++){
		//	System.out.println(Line2.getSegmentValue(i));
		//}


		segment_val = Curve.addCurve(Line1, Line2);
		//segment_val = Curve.curveMin(Line1, Line2);
		Curve.getCurve(segment_val);
		
		Curve test;
		test = Curve.floor(Line1,20);
		for(int i =0; i<test.size();i++){
			System.out.println(test.getSegmentValue(i));
		}
		
		Curve test1;
		test1 = Curve.ceil(Line1,20);
		for(int i =0; i<test1.size();i++){
			System.out.println(test1.getSegmentValue(i));
		}




		//Curve mul1;
		//mul1 = Curve.multiplicationOfTwoCurves(Line,Line1,20);
		//for(int i =0; i<mul1.size();i++){
		//	System.out.println(mul1.getSegmentValue(i));
		//}

		//double y1 = Line.evaluateYatX(15);
		//System.out.println("Y1 evaluated at x is "+y1);

		//double y = Line1.evaluateYatX(12);
		//System.out.println("Y evaluated at x is "+y);

		//for(int i =0; i<Line1.size();i++){
		//	System.out.println(Line1.getSegmentValue(i));
		//}
		//for(int i =0; i<Line.size();i++){
		//	System.out.println(Line.getSegmentValue(i));
		//}

		//Line.scaleX(2);
		//for(int i=0;i<segement_val.size();i++);
		//System.out.println("after scaling :" + segement_val.get(i));


	//	Line.getSegmentValue(1);
	//	Line.getSegmentValue(2);
	//	Line.getSegmentValue(3);
	//	Line.getSegmentValue(4);
	}

}

