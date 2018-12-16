public class ExponentialF11 implements FunctionInt {
    @Override
    public double executor(double x, double[] arr) {
        return arr[3] * Math.pow(arr[0], x);
    }
}
