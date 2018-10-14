package src;

public class Main {
    public static void main(String ... args){
        System.out.println(find(0.5,0.28,1));
    }
    static private String find(double x, double y, int r){
        if(x>=0 & x<=r){
            if(y<=(double)r/2 & y>x-r){
                return ("true"+" "+x+" "+y+" "+r);
            }
        }else{
            if(y<=0 & x*x+y*y<=(double)(r*r)/4){
                return ("true"+" "+x+" "+y+" "+r);
            }
        }
        return ("false"+" "+x+" "+y+" "+r);
    }
}
