import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

//http://allcalc.ru/node/676

public class App {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		double E=0;
		double a1,b1,a2,b2,a3,b3;
		a1=b1=a2=b2=a3=b3=0;
		System.out.println("Eсли вы хотите ввести данные с файла укажите - y, иначе данные будут вводиться в консоли!");
        	if(in.next().intern()=="y") {
        		while(true) {
        			String str="";
        			byte symbol=0;
        			boolean start=false;
        			try(FileInputStream fin=new FileInputStream("C:\\Users\\TechnoC\\Desktop\\lol.txt")){
	        			int i=-1;
        				while((i=fin.read())!=-1){  
        					if(i==10 || i==0) {
        						switch(symbol){
        						case 0:
        							E=new Double(str);                           			
        							break;
        						case 1:
	        						a1=new Double(str);
        							break;
        						case 2:
        							b1=new Double(str);
        							break;
        						case 3:
        							a2=new Double(str);
        							break;
	        					case 4:
        							b2=new Double(str);
        							break;
        						case 5:
        							a3=new Double(str);
        							break;
        						case 6:
        							b3=new Double(str);
        							break;	
	        					default:
        							break;
        						}
        						start=false;
        						str="";
        						symbol++;	
	        				}
        					if(start) {
        						str=str+(char)i;
        					}
        					if((char)i=='=') {
        						start=true;
        					}
        				}
        				fin.close();
        				volidet(a1,b1);
        				volidet(a2,b2);
        				volidet(a3,b3);
        				break;
        			}catch(IOException ex){     
	        			System.out.println(ex.getMessage());
        			}catch(Exception ex2) {
        				System.out.println("Проверьте корректоность данных в файле и потом нажмите на любую цифру!");
        				in.next();
        			}
        		}
        	}else {
        		while (true) {
        			try {        		
        				in = new Scanner(System.in);
	        			System.out.println("Введите E: ");
	        			E = in.nextDouble();   // 0.000001
        				System.out.println("Введите a для 1ой точки: ");
        				a1 = in.nextDouble();
        				System.out.println("Введите b для 1ой точки: ");
        				b1 = in.nextDouble();
	        			volidet(a1,b1);
        				System.out.println("Введите a для 2ой точки: ");
        				a2 = in.nextDouble();
        				System.out.println("Введите b для 2ой точки: ");
	        			b2 = in.nextDouble();
        				volidet(a2,b2);
        				System.out.println("Введите a для 3ей точки: ");
        				a3 = in.nextDouble();
        				System.out.println("Введите b для 3ей точки: ");
	        			b3 = in.nextDouble();
        				volidet(a3,b3);
        				break;
        			}catch(Exception e) {
        				System.out.println("Введите корректные данные!");
        			}
	        	}
	        }
		
        
        
        	System.out.println("Eсли вы хотите вывести результаты в файла укажите - y, иначе данные будут выводиться в консоли!");
        	if(in.next().intern()=="y") {
	        	try(FileOutputStream fos=new FileOutputStream("C:\\Users\\TechnoC\\Desktop\\result.txt")){
        			Chord Chord = new Chord(E,fos);
        			Tangent Tangent = new Tangent(E,fos);
        			Chord.chord(a1,b1);     	
	        		Chord.chord(a2,b2);		    		
        			Chord.chord(a3,b3);    	        		
        			Tangent.tangent(a1,b1);        		
        			Tangent.tangent(a2,b2);        		
	        		Tangent.tangent(a3,b3);      
        			fos.close();
            		}catch(IOException ex){   
                		System.out.println(ex.getMessage());
            		}	
        	}else {
        		Chord Chord = new Chord(E);
	    		Tangent Tangent = new Tangent(E);
    			
    			System.out.print("Первое значение x:");
	    		Chord.chord(a1,b1);    //-3, -1
    			System.out.print("Второе значение x:");
    			Chord.chord(a2,b2);		//-1, 0
	    		System.out.print("Третие значение x:");
    			Chord.chord(a3,b3);    //0, 2
    			
	    		System.out.println();
    			
    			System.out.print("Первое значение x:");
	    		Tangent.tangent(a1,b1);
    			System.out.print("Второе значение x:");
    			Tangent.tangent(a2,b2);
	    		System.out.print("Третие значение x:");
    			Tangent.tangent(a3,b3);
        	}
        
	        x1=a1;
        	x2=b3;
	        SwingUtilities.invokeLater(() -> {
        		Chart Chart = new Chart("График",x1,x2);
        		Chart.setSize(800, 400);
	        	Chart.setLocationRelativeTo(null);
        		Chart.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        		Chart.setVisible(true);
	        });
		
		
/*		
		System.out.print("Первое значение x:");
		pos(-3,-1);
		//System.out.println(count);
		System.out.print("Второе значение x:");
		pos(-1,0);
		//System.out.println(count);
		System.out.print("Третие значение x:");
		pos(0,2);
		//System.out.println(count);
*/
	}

	static double x1,x2;
	static void volidet(double a, double b) throws Exception {
		double y_a=2.335*a*a*a+3.98*a*a-4.52*a-3.11;
		double y_b=2.335*b*b*b+3.98*b*b-4.52*b-3.11;
		double y1_a=7.005*a*a+7.96*a-4.52;
		double y1_b=7.005*b*b+7.96*b-4.52;
		boolean result=false;
		if (y_a*y_b<0) {
			for(double i=a; i<=b; i=i+0.01) {
				y1_a=7.005*i*i+7.96*i-4.52;
				if(y1_a*y1_b>=0) {
					result=true;
					break;
				}
			}
		}
		if(!result) {
			System.out.println("fail");
			throw new Exception();
		}
	}
/*	
	static final double E=0.000001;	
	static int count;
	static void pos(double a,double b) {
		count=0;
		double y=E+1;
		double y2;
		if(Math.abs(a-b)<=E){
			System.out.println("не допустимые значения a и b");
			return;
		}
		double x=0;
		while((Math.abs(a-b)>E) && (Math.abs(y)>E)) {	
			count++;
			x=(a+b)/2;
			y=2.335*x*x*x+3.98*x*x-4.52*x-3.11;
			y2=2.335*a*a*a+3.98*a*a-4.52*a-3.11;
			if(y<0) {
				if(y2<=y) {
					a=x;
				}else {
					b=x;
				}
			}else if(y>0){
				if(y2>=y) {
					a=x;
				}else {
					b=x;
				}
			}else {
				break;
			}
		}
		System.out.println(x);
		System.out.println("Количество итераций: "+ count);
	}	
*/	
}




