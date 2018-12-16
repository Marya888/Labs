public class LogarithmicF implements FunctionInt {
    @Override
    public double executor(double x, double[] arr) {
        return Math.log(x + arr[0]) + arr[1];
    }
}
