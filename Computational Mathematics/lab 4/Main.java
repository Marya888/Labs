import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class Main {

    public static void main(String[] args) {
        double[] arr = {0,0,0,0};
        DecimalFormat df = new DecimalFormat("#.######");
//        a, b, n(c), k
        System.out.println("Введите номер фунции, которую вы бы хотели использовать");
        System.out.println("1) линейная ( y = a * x + b )");
        System.out.println("2) квадратичная( y = a * x^2 + b * x + c )");
        System.out.println("3) степенная ( y = k * (x + a)^n + b )");
        System.out.println("4) логарифмическая ( y = ln(x + a) + b )");
        System.out.println("5) показательная ( y = k * a^x + b )");

        Scanner scanner = new Scanner(System.in);
        int type = scanner.nextInt();

        System.out.println("Задайте пределы интегрирования");
        int limitA = scanner.nextInt();
        int limitB = scanner.nextInt();
        switch (type){
            case 1:
                System.out.println("Введите параметры a и b через пробел");
                arr[0] = scanner.nextDouble();
                arr[1] = scanner.nextDouble();

                System.out.println("Результат вычислений через метод прямоугольников(левых) = " + df.format(new Methods().rectangleLeftM(limitB, limitA, arr , new LineF(), null,0.000001)));
                System.out.println("Результат вычислений через метод прямоугольников(правых) = " + df.format(new Methods().rectangleRightM( limitB, limitA, arr , new LineF(), null,0.000001)));
                System.out.println("Результат вычислений через метод прямоугольников(средних) = " + df.format(new Methods().rectangleMidleM( limitB, limitA, arr , new LineF(), null,0.000001)));
                System.out.println("Результат вычислений через метод трапеций = " + df.format(new Methods().trapeziumM( limitB, limitA, arr , new LineF(),null,0.000001)));
                System.out.println("Результат вычислений через метод Симпсона = " + df.format(new Methods().simpsonM( limitB, limitA, arr , new LineF(), null,0.000001)));

                break;
            case 2:
                System.out.println("Введите параметры a, b и c через пробел");
                arr[0] = scanner.nextDouble();
                arr[1] = scanner.nextDouble();
                arr[2] = scanner.nextDouble();

                System.out.println("Результат вычислений через метод прямоугольников(левых) = " + df.format(new Methods().rectangleLeftM(limitB, limitA, arr , new QuadraticF(), new QuadraticF11(),0.000001)));
                System.out.println("Результат вычислений через метод прямоугольников(правых) = " + df.format(new Methods().rectangleRightM( limitB, limitA, arr , new QuadraticF(),new QuadraticF11(),0.000001)));
                System.out.println("Результат вычислений через метод прямоугольников(средних) = " + df.format(new Methods().rectangleMidleM( limitB, limitA, arr , new QuadraticF(),new QuadraticF11(),0.000001)));
                System.out.println("Результат вычислений через метод трапеций = " + df.format(new Methods().trapeziumM( limitB, limitA, arr , new QuadraticF(),new QuadraticF11(),0.000001)));
//                System.out.println("Результат вычислений через метод Симпсона = " + df.format(new Methods().simpsonM( limitB, limitA, arr , new QuadraticF(), new QuadraticF1111(),0.000001)));

                break;
            case 3:
                System.out.println("Введите параметры a, b, k и n через пробел");
                arr[0] = scanner.nextDouble();
                arr[1] = scanner.nextDouble();
                arr[3] = scanner.nextDouble();
                arr[2] = scanner.nextDouble();

                System.out.println("Результат вычислений через метод прямоугольников(левых) = " + df.format(new Methods().rectangleLeftM(limitB, limitA, arr , new PowerF(), new PowerF11(),0.000001)));
                System.out.println("Результат вычислений через метод прямоугольников(правых) = " + df.format(new Methods().rectangleRightM( limitB, limitA, arr , new PowerF(), new PowerF11(),0.000001)));
                System.out.println("Результат вычислений через метод прямоугольников(средних) = " + df.format(new Methods().rectangleMidleM( limitB, limitA, arr , new PowerF(), new PowerF11(),0.000001)));
                System.out.println("Результат вычислений через метод трапеций = " + df.format(new Methods().trapeziumM( limitB, limitA, arr , new PowerF(), new PowerF11(),0.000001)));
                System.out.println("Результат вычислений через метод Симпсона = " + df.format(new Methods().simpsonM( limitB, limitA, arr , new PowerF(), new PowerF1111(),0.000001)));

                break;
            case 4:
                System.out.println("Введите параметры a и b через пробел");
                arr[0] = scanner.nextDouble();
                arr[1] = scanner.nextDouble();

                System.out.println("Результат вычислений через метод прямоугольников(левых) = " + df.format(new Methods().rectangleLeftM(limitB, limitA, arr , new LogarithmicF(), new LogarithmicF11(),0.000001)));
                System.out.println("Результат вычислений через метод прямоугольников(правых) = " + df.format(new Methods().rectangleRightM( limitB, limitA, arr , new LogarithmicF(), new LogarithmicF11(),0.000001)));
                System.out.println("Результат вычислений через метод прямоугольников(средних) = " + df.format(new Methods().rectangleMidleM( limitB, limitA, arr , new LogarithmicF(), new LogarithmicF11(),0.000001)));
                System.out.println("Результат вычислений через метод трапеций = " + df.format(new Methods().trapeziumM( limitB, limitA, arr , new LogarithmicF(), new LogarithmicF11(),0.000001)));
                System.out.println("Результат вычислений через метод Симпсона = " + df.format(new Methods().simpsonM( limitB, limitA, arr , new LogarithmicF(), new LogarithmicF1111(),0.000001)));

                break;
            case 5:
                System.out.println("Введите параметры a, b и k через пробел");
                arr[0] = scanner.nextDouble();
                arr[1] = scanner.nextDouble();
                arr[3] = scanner.nextDouble();

                System.out.println("Результат вычислений через метод прямоугольников(левых) = " + df.format(new Methods().rectangleLeftM(limitB, limitA, arr , new ExponentialF(), new ExponentialF11(),0.000001)));
                System.out.println("Результат вычислений через метод прямоугольников(правых) = " + df.format(new Methods().rectangleRightM( limitB, limitA, arr , new ExponentialF(), new ExponentialF11(),0.000001)));
                System.out.println("Результат вычислений через метод прямоугольников(средних) = " + df.format(new Methods().rectangleMidleM( limitB, limitA, arr , new ExponentialF(), new ExponentialF11(),0.000001)));
                System.out.println("Результат вычислений через метод трапеций = " + df.format(new Methods().trapeziumM( limitB, limitA, arr , new LogarithmicF(), new ExponentialF11(),0.000001)));
//                System.out.println("Результат вычислений через метод Симпсона = " + df.format(new Methods().simpsonM( limitB, limitA, arr , new ExponentialF(), new ExponentialF1111(),0.000001)));

                break;
            default:
                System.out.println("Извините, но вы даун раз не можете ввести число от 1 до 5. Моя программа не для вас.");
        }


/*
2 * (x + 1)^5 + 3
= 590526

3
1 10
1 3 2 5
*/
    }
}


