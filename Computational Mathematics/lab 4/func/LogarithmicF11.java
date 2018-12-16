public class LogarithmicF11 implements FunctionInt {
    @Override
    public double executor(double x, double[] arr) {
        return -1/(Math.pow((x + arr[0]), 2));
    }
}
