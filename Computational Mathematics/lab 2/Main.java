import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

import static java.lang.Math.*;

public class Main {
    public static void main(String ... args){
        int size = 0;
        FileInputStream fin = null;
        boolean in_console;
        int number = -1;


        Scanner scanner = new Scanner(System.in);
        System.out.println("Eсли вы хотите ввести данные с файла укажите - y, иначе данные будут вводиться в консоли!");
        if (scanner.next().intern() == "y") {
            in_console = false;
            try {
                fin = new FileInputStream("C:\\Users\\TechnoC\\Desktop\\in.txt");
                while ((number = fin.read()) != 13) {
                    size = size * 10 + new Integer((char) number + "");
                }
                fin.read();
            } catch (FileNotFoundException e) {
                System.out.println("Fail not found");
            } catch (IOException e) {
                System.out.println("Error in stream");
            }
        } else {
            in_console = true;
            System.out.println("Enter count point x and y");
            size = scanner.nextInt();
            System.out.println("Enter point x and y");
        }

        List<Double> list_x = new ArrayList(size);
        List<Double> list_y = new ArrayList(size);
        Map<String, Double> map = new HashMap(size);

        filling(list_x, in_console, size, scanner, fin);
        filling(list_y, in_console, size, scanner, fin);
        foundValues(list_x, list_y, map,0);

        Chart chart = new Chart("График",list_x, list_y, map);
        chart.setSize(1000, 500);
        chart.setLocationRelativeTo(null);
        chart.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        chart.setVisible(true);

        System.out.println("Eсли вы хотите вывести данные в файл укажите - y, иначе данные будут выводиться в консоли!");
        if(scanner.next().intern() == "y"){
            out(false,list_x, list_y, size, map, chart);
        }else {
            out(true, list_x, list_y, size, map, chart);
        }
        chart.start();
    }
    static void filling(List list, boolean console, int size, Scanner scanner, FileInputStream in){
        int number;
        String value = "";
        if (console) {
            for (int i = 0; i < size; i++) {
                list.add(scanner.nextDouble());
            }
        }else {
            try{
                while((number = in.read()) != 13){
                    if(number==32){
                        list.add(new Double(value));
                        value="";
                        continue;
                    }
                    value=value+(char)number;
                }
                list.add(new Double(value));
                in.read();
            }catch (IOException e) {
                System.out.println("Error in stream");
            }
        }
    }
    static void foundValues(List<Double> list_x,List<Double> list_y, Map map, int type_of_equation){
        double x=0,y=0,xy=0,x2=0,x3=0,x4=0,x2y=0;
        for(int i=0; i<list_x.size(); i++){
            if(type_of_equation==3){
                x=x+list_x.get(i);
                x2=x2+list_x.get(i)*list_x.get(i);
                y=y+log(list_y.get(i));
                xy=xy+list_x.get(i)*log(list_y.get(i));
                continue;
            }else if(type_of_equation==4){
                x=x+log(list_x.get(i));
                x2=x2+log(list_x.get(i))*log(list_x.get(i));
                y=y+list_y.get(i);
                xy=xy+log(list_x.get(i))*list_y.get(i);
                continue;
            }else if(type_of_equation==5){
                x=x+log(list_x.get(i));
                x2=x2+log(list_x.get(i))*log(list_x.get(i));
                y=y+log(list_y.get(i));
                xy=xy+log(list_x.get(i))*log(list_y.get(i));
                continue;
            }
            x=x+list_x.get(i);
            x2=x2+list_x.get(i)*list_x.get(i);
            x3=x3+list_x.get(i)*list_x.get(i)*list_x.get(i);
            x4=x4+list_x.get(i)*list_x.get(i)*list_x.get(i)*list_x.get(i);
            y=y+list_y.get(i);
            xy=xy+list_x.get(i)*list_y.get(i);
            x2y=x2y+list_x.get(i)*list_x.get(i)*list_y.get(i);
        }
        map.put("x",x);
        map.put("x2",x2);
        map.put("x3",x3);
        map.put("x4",x4);
        map.put("y",y);
        map.put("xy",xy);
        map.put("x2y",x2y);
    }
    static void linearApproximation(Map<String, Double> map, int n){
        map.put("a", ((map.get("xy") * n - map.get("x") * map.get("y")) / (map.get("x2") * n - map.get("x") * map.get("x"))));
        map.put("b",((map.get("x2") * map.get("y") - map.get("x") * map.get("xy")) / (map.get("x2") * n - map.get("x") * map.get("x"))));
    }
    static void quadraticApproximation(Map<String, Double> map, int n){
        map.put("D",
                (
                        + n * ((double)map.get("x2") * (double)map.get("x4") - (double)map.get("x3") *(double)map.get("x3"))
                        - (double)map.get("x") * ((double)map.get("x") * (double)map.get("x4") - (double)map.get("x3") *(double)map.get("x2"))
                        + (double)map.get("x2") * ((double)map.get("x") * (double)map.get("x3") - (double)map.get("x2") *(double)map.get("x2"))
                )
        );

        map.put("a",
                ((
                        +(double)map.get("y") * ((double)map.get("x2") * (double)map.get("x4") - (double)map.get("x3") *(double)map.get("x3"))
                        - (double)map.get("x") * ((double)map.get("xy") * (double)map.get("x4") - (double)map.get("x3") *(double)map.get("x2y"))
                        + (double)map.get("x2") * ((double)map.get("xy") * (double)map.get("x3") - (double)map.get("x2") *(double)map.get("x2y"))
                        )/(double)map.get("D"))
        );

        map.put("b",
                ((
                        +n * ((double)map.get("xy") * (double)map.get("x4") - (double)map.get("x3") *(double)map.get("x2y"))
                        - (double)map.get("y") * ((double)map.get("x") * (double)map.get("x4") - (double)map.get("x3") *(double)map.get("x2"))
                        + (double)map.get("x2") * ((double)map.get("x") * (double)map.get("x2y") - (double)map.get("x2") *(double)map.get("xy"))
                )/(double)map.get("D"))
        );

        map.put("c",
                ((
                        + n * ((double)map.get("x2") * (double)map.get("x2y") - (double)map.get("x3") *(double)map.get("xy"))
                        - (double)map.get("x") * ((double)map.get("x") * (double)map.get("x2y") - (double)map.get("x2") *(double)map.get("xy"))
                        + (double)map.get("y") * ((double)map.get("x") * (double)map.get("x3") - (double)map.get("x2") *(double)map.get("x2"))
                )/(double)map.get("D"))
        );
    }
//    type_of_equation==1    linear funk
//    type_of_equation==2    polynomial funk
//    type_of_equation==3    exponential funk
//    type_of_equation==4    logarithmic funk
//    type_of_equation==5    power funk
    static void deviation(Map<String, Double> map, List<Double> list_x, List<Double> list_y, int type_of_equation, int size, String name){
        double deviation=0;
        for(int i=0; i<size; i++){
            if(type_of_equation==1){
//                System.out.println(map.get("a")*list_x.get(i)+map.get("b"));
                deviation=deviation+((map.get("a")*list_x.get(i)+map.get("b"))-list_y.get(i))*((map.get("a")*list_x.get(i)+map.get("b"))-list_y.get(i));
            }else if(type_of_equation==2){
//                System.out.println(map.get("a")+list_x.get(i)*map.get("b")+map.get("c")*list_x.get(i)*list_x.get(i));
                deviation=deviation+pow(((map.get("a")+list_x.get(i)*map.get("b")+map.get("c")*list_x.get(i)*list_x.get(i))-list_y.get(i)),2);
            }else if(type_of_equation==3){

//                System.out.println(pow(Math.E,map.get("b"))*pow(E,(map.get("a")*list_x.get(i))));
                deviation=deviation+pow(((pow(Math.E,map.get("b"))*pow(E,(map.get("a")*list_x.get(i))))-list_y.get(i)),2);
            }else if(type_of_equation==4){

//                System.out.println(map.get("b")+map.get("a")*log(list_x.get(i)));
                deviation=deviation+pow(((map.get("b")+map.get("a")*log(list_x.get(i)))-list_y.get(i)),2);
            }else if(type_of_equation==5){
//                System.out.println(pow(Math.E,map.get("b"))*(pow(list_x.get(i),map.get("a"))));
                deviation=deviation+pow(((pow(Math.E,map.get("b"))*(pow(list_x.get(i),map.get("a"))))-list_y.get(i)),2);
            }
        }
        map.put(name, deviation);
    }
    static void rms(Map<String, Double> map, int size,int type_of_equation){
        if(type_of_equation==1) {
            map.put("rms_deviation_for_linear", sqrt(map.get("deviation_for_linear") / size));
        }else if(type_of_equation==2) {
            map.put("rms_deviation_for_polynomial", sqrt(map.get("deviation_for_polynomial") / size));
        }else if(type_of_equation==3) {
            map.put("rms_deviation_for_exponential", sqrt(map.get("deviation_for_exponential") / size));
        }else if(type_of_equation==4) {
            map.put("rms_deviation_for_logarithmic", sqrt(map.get("deviation_for_logarithmic") / size));
        }else if(type_of_equation==5) {
            map.put("rms_deviation_for_power", sqrt(map.get("deviation_for_power") / size));
        }
    }
    static void out(boolean res,List<Double> list_x, List<Double> list_y, int size, Map<String, Double> map, Chart chart) {
        String result="";
        DecimalFormat df = new DecimalFormat("#.#####");
        if (res)
            System.out.println("Для линейной функции (y=a*x+b):");
        linearApproximation(map, size);
        deviation(map, list_x, list_y, 1, size, "deviation_for_linear");
        rms(map, size, 1);
        if (res)
            System.out.println("a=" + df.format(map.get("a")) + ", b=" + df.format(map.get("b")) + ", S=" + df.format(map.get("deviation_for_linear")) + ", δ=" + df.format(map.get("rms_deviation_for_linear")));
        map.put("a_for_linear", map.get("a"));
        map.put("b_for_linear", map.get("b"));
        chart.fill(1);

        if (res)
            System.out.println("Для полинома 2 степени (y=a+b*x+c*x^2):");
        quadraticApproximation(map, size);
        deviation(map, list_x, list_y, 2, size, "deviation_for_polynomial");
        rms(map, size, 2);
        if (res)
            System.out.println("a=" + df.format(map.get("a")) + ", b=" + df.format(map.get("b")) + ", с=" + df.format(map.get("c")) + ", S=" + df.format(map.get("deviation_for_polynomial")) + ", δ=" + df.format(map.get("rms_deviation_for_polynomial")));
        map.put("a_for_polynomial", map.get("a"));
        map.put("b_for_polynomial", map.get("b"));
        map.put("c_for_polynomial", map.get("c"));
        chart.fill(2);

        if (res)
            System.out.println("Для экспоненциальной функции (y=a*e^(b*x)):");
        foundValues(list_x, list_y, map, 3);
        linearApproximation(map, size);
        deviation(map, list_x, list_y, 3, size, "deviation_for_exponential");
        rms(map, size, 3);
        if (res)
            System.out.println("a=" + df.format(pow(Math.E, map.get("b"))) + ", b=" + df.format(map.get("a")) + ", S=" + df.format(map.get("deviation_for_exponential"))+ ", δ=" + df.format(map.get("rms_deviation_for_exponential")));
        map.put("a_for_exponential", pow(Math.E, map.get("b")));
        map.put("b_for_exponential", map.get("a"));
        chart.fill(3);

        if (res)
            System.out.println("Для логарифмической функции (y=a+b*lnx):");
        foundValues(list_x, list_y, map, 4);
        linearApproximation(map, size);
        deviation(map, list_x, list_y, 4, size, "deviation_for_logarithmic");
        rms(map, size, 4);
        if (res)
            System.out.println("a=" + df.format(map.get("b")) + ", b=" + df.format(map.get("a")) + ", S=" +df.format(map.get("deviation_for_logarithmic")) + ", δ=" + df.format(map.get("rms_deviation_for_logarithmic")));
        map.put("a_for_logarithmic", map.get("b"));
        map.put("b_for_logarithmic", map.get("a"));
        chart.fill(4);

        if (res)
            System.out.println("Для степенной функции (y=a*x^b):");
        foundValues(list_x, list_y, map, 5);
        linearApproximation(map, size);
        deviation(map, list_x, list_y, 5, size, "deviation_for_power");
        rms(map, size, 5);
        if (res)
            System.out.println("a=" + df.format(pow(Math.E, map.get("b"))) + ", b=" + df.format(map.get("a")) + ", S=" + df.format(map.get("deviation_for_power")) + ", δ=" + df.format(map.get("rms_deviation_for_power")));
        map.put("a_for_power", pow(Math.E, map.get("b")));
        map.put("b_for_power", map.get("a"));
        chart.fill(5);
        double results=  max(max(max(max(map.get("rms_deviation_for_exponential"),
                map.get("rms_deviation_for_logarithmic")),
                map.get("rms_deviation_for_polynomial")),
                map.get("rms_deviation_for_linear")),
                map.get("rms_deviation_for_power"));
        if(results==map.get("rms_deviation_for_exponential")){
            result="y=a*e^(b*x)";
        }else if(results==map.get("rms_deviation_for_liner")){
            result="y=a*x+b";
        }else if(results==map.get("rms_deviation_for_polynomial")){
            result="y=a+b*x+c*x^2";
        }else if(results==map.get("rms_deviation_for_logarithmic")){
            result="y=a+b*lnx";
        }else if(results==map.get("rms_deviation_for_power")){
            result="y=a*x^b";
        }

        if (res)
            System.out.println("Наилучшим считается уравнение - "+result);
        if (!res) {
            try (FileOutputStream out = new FileOutputStream("C:\\Users\\TechnoC\\Desktop\\result.txt")) {
                byte[] buffer = (
                            "Для линейной функции (y=a*x+b):\r\n" +
                                    "a=" + df.format(map.get("a_for_linear")) + ", b=" + df.format(map.get("b_for_linear")) + ", S=" +df.format(map.get("deviation_for_linear")) + ", δ=" + df.format(map.get("rms_deviation_for_linear")) + "\r\n" +
                                    "Для полинома 2 степени (y=a+b*x+c*x^2):\r\n" +
                                    "a=" + df.format(map.get("a_for_polynomial")) + ", b=" + df.format(map.get("b_for_polynomial")) + ", с=" + df.format(map.get("c_for_polynomial")) + ", S=" + df.format(map.get("deviation_for_polynomial")) + ", δ=" + df.format(map.get("rms_deviation_for_polynomial")) + "\r\n" +
                                    "Для экспоненциальной функции (y=a*e^(b*x)):\r\n" +
                                    "a=" + df.format(map.get("a_for_exponential")) + ", b=" + df.format(map.get("b_for_exponential")) + ", S=" + df.format(map.get("deviation_for_exponential")) + ", δ=" + df.format(map.get("rms_deviation_for_exponential")) + "\r\n" +
                                    "Для логарифмической функции (y=a+b*lnx):\r\n" +
                                    "a=" + df.format(map.get("a_for_logarithmic")) + ", b=" + df.format(map.get("b_for_logarithmic")) + ", S=" + df.format(map.get("deviation_for_logarithmic")) + ", δ=" + df.format(map.get("rms_deviation_for_logarithmic")) + "\r\n" +
                                    "Для степенной функции (y=a*x^b):\r\n" +
                                    "a=" + df.format(map.get("a_for_power")) + ", b=" + df.format(map.get("b_for_power")) + ", S=" + df.format(map.get("deviation_for_power")) + ", δ=" + df.format(map.get("rms_deviation_for_power")) + "\r\n"+"Наилучшим считается уравнение - "+result).getBytes();
                out.write(buffer, 0, buffer.length);
            }catch(IOException ex){
                System.out.println(ex.getMessage());
            }

        }

    }
//13
//        1 1.1 1.2 1.3 1.4 1.5 1.6 1.7 1.8 1.9 2 2.1 2.2
//        5.11 5.36 5.64 5.82 6.04 6.24 6.43 6.58 6.74 6.88 7.06 7.19 7.33

//13
//        1 1,1 1,2 1,3 1,4 1,5 1,6 1,7 1,8 1,9 2 2,1 2,2
//        5,11 5,36 5,64 5,82 6,04 6,24 6,43 6,58 6,74 6,88 7,06 7,19 7,33


//5
//        0 1 2 4 5
//        2.1 2.4 2.6 2.8 3


//7
//        1.1 2.3 3.7 4.5 5.4 6.8 7.5
//        3.5 4.1 5.2 6.9 8.3 14.8 21.2

//  8
//    1.2 2.9 4.1 5.5 6.7 7.8 9.2 10.3
//    7.4 9.5 11.1 12.9 14.6 17.3 18.2 20.7
}

