public class QuadraticF implements FunctionInt {
    @Override
    public double executor(double x, double[] arr) {
        return arr[0] * x * x + arr[1] * x + arr[2];
    }
}