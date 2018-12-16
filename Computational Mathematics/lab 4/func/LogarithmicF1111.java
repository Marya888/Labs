public class LogarithmicF1111 implements FunctionInt {
    @Override
    public double executor(double x, double[] arr) {
        return -6/(Math.pow((x + arr[0]), 4));
    }
}
