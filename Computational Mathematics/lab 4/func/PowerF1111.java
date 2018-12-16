public class PowerF1111 implements FunctionInt {
    @Override
    public double executor(double x, double[] arr) {
        return arr[3] * arr[2] * (arr[2] - 1) * (arr[2] - 2) * (arr[2] - 3) * Math.pow((x + arr[0]), (arr[2] - 4));
    }
}

