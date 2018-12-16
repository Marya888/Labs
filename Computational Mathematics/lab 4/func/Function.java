public class Function {

    public double lineF(double x, double a, double b){
        return x * a + b;
    }

    public double quadraticF(double x, double a, double b, double c){
        return a * x * x + b * x + c;
    }

    public double powerF(double x, double a, double b, double n, double k){
        return k * Math.pow((x + a), n) + b;
    }

    public double logarithmicF(double x, double a, double b){
        return Math.log(x + a) + b;
    }

    public double exponentialF(double x, double a, double b, double k){
        return k * Math.pow(a, x) + b;
    }
}
