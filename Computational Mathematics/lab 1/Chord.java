import java.io.FileOutputStream;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Chord {
	
	Chord(double e){
		this.E = e;
	}
	
	Chord(double e, FileOutputStream out){
		this.E = e;
		resout=true;
		this.out=out;
	}
	
	DecimalFormat df = new DecimalFormat("#0.000");
	FileOutputStream out;
	boolean resout=false;
	double E=0.000001;
	double y_b;
	double y_a;
	double y_ab;
	int count;
	void chord(double a, double b) {
		count=0;
		y_b=2.335*b*b*b+3.98*b*b-4.52*b-3.11;
		y_a=2.335*a*a*a+3.98*a*a-4.52*a-3.11;
		if (((y_a>y_b) && (14.01*((a+b)/2)+7.96>=0))  || ((y_a<y_b) &&(14.01*((a+b)/2)+7.96<=0))) {
			double c=a;
			a=b;
			b=c;
		}
		while((Math.abs(b-a)>E) && (Math.abs(y_a)>E)) {
			count++;
			y_b=2.335*b*b*b+3.98*b*b-4.52*b-3.11;
			y_a=2.335*a*a*a+3.98*a*a-4.52*a-3.11;
			a=(a*y_b-b*y_a)/(y_b-y_a);
			y_a=2.335*a*a*a+3.98*a*a-4.52*a-3.11;
		}
		//System.out.printf("%.3f", a);		
		df.setRoundingMode(RoundingMode.DOWN);
		
		if (resout) {
			try {
				byte[] buffer =("x: "+ df.format(a)+"	").getBytes();	        
	            out.write(buffer, 0, buffer.length);	         
	            buffer = ("Количество итераций: "+ count+"	").getBytes();	        
	            out.write(buffer, 0, buffer.length);
			}catch(IOException ex){	              
	            System.out.println(ex.getMessage());
			}
		}else {
			System.out.println(df.format(a));
			System.out.println("Количество итераций: "+ count);
		}
	}
}



