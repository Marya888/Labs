public class PowerF implements FunctionInt {
    @Override
    public double executor(double x, double[] arr) {
        return arr[3] * Math.pow((x + arr[0]), arr[2]) + arr[1];
    }
}