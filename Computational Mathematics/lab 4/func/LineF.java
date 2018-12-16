public class LineF implements FunctionInt{
    @Override
    public double executor(double x, double[] arr) {
        return x * arr[0] + arr[1];
    }
}
