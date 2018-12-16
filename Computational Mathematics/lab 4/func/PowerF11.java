public class PowerF11 implements FunctionInt {
    @Override
    public double executor(double x, double[] arr) {
        return arr[3] * arr[2] * (arr[2] - 1) * Math.pow((x + arr[0]), (arr[2] - 2));
    }
}

