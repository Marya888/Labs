public class Methods {
    public double rectangleLeftM(double EndX, double startX, double[] arr , FunctionInt funcf,FunctionInt funcs, double R){
        double res = 0;
        int n = findNForRectangle(startX, EndX, arr, funcs, R);
        System.out.println("Количество n = "+n);
        double h = (EndX - startX) / n;
        for(int i = 0; i<n; i++){
//            System.out.println("111="+func.executor(startX, arr));
            res += h * funcf.executor(startX, arr);
            startX += h;
        }
        return res;
    }

    public double rectangleRightM(double EndX, double startX, double[] arr , FunctionInt funcf,FunctionInt funcs, double R){
        double res = 0;
        int n = findNForRectangle(startX, EndX, arr, funcs, R);
        System.out.println("Количество n = "+n);
        double h = (EndX - startX) / n;
        startX += h;
        for(int i = 0; i<n; i++){
//            System.out.println("222="+func.executor(startX, arr));
            res += h * funcf.executor(startX, arr);
            startX += h;
        }
        return res;
    }

    public double rectangleMidleM(double EndX, double startX, double[] arr , FunctionInt funcf,FunctionInt funcs, double R){
        double res = 0;
        int n = findNForRectangle(startX, EndX, arr, funcs, R);
        System.out.println("Количество n = "+n);
        double h = (EndX - startX) / n;
        startX = startX+h/2;
        for(int i = 0; i<n; i++){
//            System.out.println("333="+func.executor(startX, arr));
            res += h * funcf.executor(startX, arr);
            startX += h;
        }
        return res;
    }

    public double trapeziumM(double EndX, double startX, double[] arr , FunctionInt funcf,FunctionInt funcs, double R){
        int n = findNForTrapezium(startX, EndX, arr, funcs, R);
        System.out.println("Количество n = "+n);
        double h = (EndX - startX) / n;
        double res = (funcf.executor(startX, arr) + funcf.executor(startX + h * n, arr)) / 2;
        startX += h;
        for(int i = 0; i<n-1; i++){
//            System.out.println("444="+func.executor(startX, arr));
            res += funcf.executor(startX, arr);
            startX += h;
        }
        res *= h;
        return res;
    }

    public double simpsonM(double EndX, double startX, double[] arr , FunctionInt funcf,FunctionInt funcs, double R){
        int n = findNForSimpso(startX, EndX, arr, funcs, R);
        System.out.println("Количество n = "+n);
        double h = (EndX - startX) / n;
        double res = (funcf.executor(startX, arr) + funcf.executor(startX + h * n, arr)) ;
        startX += h;
        for(int i = 0; i<n-1; i++){
//            System.out.println("555="+func.executor(startX, arr));
            if(i%2==0)
                res += 4 * funcf.executor(startX, arr);
            else
                res += 2 * funcf.executor(startX, arr);
            startX += h;
        }
        res *= h / 3;
        return res;
    }

    private double maxVal(double StartX, double EndX, double[] arr, FunctionInt func){
        double max= 0;
        for(double i = StartX; i<=EndX; i+=0.01){
            if(max < func.executor(i, arr))
                max = func.executor(i, arr);
        }
        return max;
    }

    private int findNForRectangle(double StartX, double EndX, double[] arr, FunctionInt func, double R){
//        System.out.println("max="+maxVal(StartX, EndX, arr, new QuadraticF1()));
        int n = (int) Math.sqrt((maxVal(StartX, EndX, arr, func) * Math.pow((EndX - StartX), 3)) / (24 * R));
        if(n<=4)
            n = 4;
        return n;
    }

    private int findNForTrapezium(double StartX, double EndX, double[] arr, FunctionInt func, double R){
        int n = (int) Math.sqrt((maxVal(StartX, EndX, arr, func) * Math.pow((EndX - StartX), 3)) / (12 * R));
        if(n<=4)
            n = 4;
        return n;
    }

    private int findNForSimpso(double StartX, double EndX, double[] arr, FunctionInt func, double R){
        int n = (int) Math.sqrt((maxVal(StartX, EndX, arr, func) * Math.pow((EndX - StartX), 5)) / (2880 * R));
        if(n<=4)
            n = 4;
        return n;
    }
}

