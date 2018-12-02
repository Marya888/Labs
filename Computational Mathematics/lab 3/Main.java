import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String name_method = "";
        FileInputStream fin = null;
        boolean in_console;
        boolean flag_end = false;
        int number = -1;
        int size;

        List<Double> list_x_for_2_2 = new ArrayList();
        List<Double> list_y_for_2_2 = new ArrayList();
        double x_for_2_2 = 0;
        List<Double> list_x_for_2_3 = new ArrayList();
        List<Double> list_y_for_2_3 = new ArrayList();
        double x_for_2_3_1 = 0;
        double x_for_2_3_2 = 0;
        List<Double> list_x_for_2_4 = new ArrayList();
        List<Double> list_y_for_2_4 = new ArrayList();
        double x_for_2_4 = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Eсли вы хотите ввести данные с файла укажите - y, иначе данные будут вводиться в консоли!");
        if (scanner.next().intern() == "y") {
            in_console = false;
            try {
                fin = new FileInputStream("C:\\Users\\TechnoC\\Desktop\\in.txt");
                while(!flag_end){
                    while ((number = fin.read()) != 13) {
                        name_method = name_method + (char)number;
                    }
                    fin.read();
                    if(name_method.intern() == "Lagrange"){
                        String value="";
                        while((number = fin.read()) != 13){
                            value=value+(char)number;
                        }
                        x_for_2_2 = new Double(value);
                        filling(0, list_x_for_2_2, in_console, scanner, fin);
                        filling(0, list_y_for_2_2, in_console, scanner, fin);
                        name_method="";
                    }else if(name_method.intern() == "Newton, h==h"){
                        String value="";
                        while((number = fin.read()) != 13){
                            if(number==32){
                                x_for_2_3_1 = new Double(value);
                                value="";
                                continue;
                            }
                            value=value+(char)number;
                        }
                        x_for_2_3_2 = new Double(value);
                        filling(0, list_x_for_2_3, in_console, scanner, fin);
                        filling(0, list_y_for_2_3, in_console, scanner, fin);
                        name_method="";
                    }else if(name_method.intern() == "Newton, h!=h"){
                       String value="";
                        while((number = fin.read()) != 13){
                            value=value+(char)number;
                        }
                        x_for_2_4 = new Double(value);
                        filling(0, list_x_for_2_4, in_console, scanner, fin);
                        filling(0, list_y_for_2_4, in_console, scanner, fin);
                        name_method="";
                        flag_end = true;
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Fail not found");
            } catch (IOException e) {
                System.out.println("Error in stream");
            }
        } else {
            in_console = true;
            try {
                System.out.println("Enter x for method Lagrange's");
                x_for_2_2 = scanner.nextDouble();
                System.out.println("Enter count dot");
                size = scanner.nextInt();
                filling(size, list_x_for_2_2, in_console, scanner, fin);
                filling(size, list_y_for_2_2, in_console, scanner, fin);
                System.out.println("Enter x for Newton's first interpolation formula");
                x_for_2_3_1 = scanner.nextDouble();
                System.out.println("Enter count dot");
                size = scanner.nextInt();
                filling(size, list_x_for_2_3, in_console, scanner, fin);
                filling(size, list_y_for_2_3, in_console, scanner, fin);
                System.out.println("Enter x for Newton's second interpolation formula");
                x_for_2_3_2 = scanner.nextDouble();
//                System.out.println("Enter count dot");
//                size = scanner.nextInt();
//                filling(size, list_x_for_2_3, in_console, scanner, fin);
//                filling(size, list_y_for_2_3, in_console, scanner, fin);
                System.out.println("Enter x for method Newton's with unequal segments");
                x_for_2_4 = scanner.nextDouble();
                System.out.println("Enter count dot");
                size = scanner.nextInt();
                filling(size, list_x_for_2_4, in_console, scanner, fin);
                filling(size, list_y_for_2_4, in_console, scanner, fin);
            }catch (Exception e){
                System.out.println("Enter correct data");
            }
        }


//        System.out.println(x_for_2_2);
//        for(int i=0; i<7; i++){
//            System.out.println("x="+list_x_for_2_2.get(i)+"y="+list_y_for_2_2.get(i));
//        }
//        System.out.println(polynomialLagrange(x_for_2_2,list_x_for_2_2, list_y_for_2_2));

        DecimalFormat df = new DecimalFormat("#.#####");


        System.out.println("Eсли вы хотите вывести данные в файл укажите - y, иначе данные будут выводиться в консоли!");
        if(scanner.next().intern() == "y"){
            try (FileOutputStream out = new FileOutputStream("C:\\Users\\TechnoC\\Desktop\\result.txt")) {
                byte[] buffer = (
                        "2.2) Приближенное значение функции при данном значении аргумента с помощью многочлена Лагранжа: "+df.format(polynomialLagrange(x_for_2_2,list_x_for_2_2, list_y_for_2_2))+"\r\n"+
                        "2.3) Приближенное значение функции используя первую интерполяционную формулу Ньютона: "+df.format(firstInterpolationFormulaNewton_2(x_for_2_3_1, list_x_for_2_3, list_y_for_2_3))+"\r\n"+
                        "2.3) Приближенное значение функции используя вторую интерполяционную формулу Ньютона: "+df.format(secondInterpolationFormulaNewton_2(x_for_2_3_2, list_x_for_2_3, list_y_for_2_3))+"\r\n"+
                        "2.4) Приближенное значение функции, используя интерполяционную формулу Ньютона для неравностоящих узлов: "+df.format(newtonForUnequallySpacedNodes(x_for_2_4, list_x_for_2_4, list_y_for_2_4))).getBytes();
                out.write(buffer, 0, buffer.length);
            }catch(IOException ex){
                System.out.println(ex.getMessage());
            }
        }else {
            System.out.println("2.2) Приближенное значение функции при данном значении аргумента с помощью многочлена Лагранжа: "+df.format(polynomialLagrange(x_for_2_2,list_x_for_2_2, list_y_for_2_2)));
            System.out.println("2.3) Приближенное значение функции используя первую интерполяционную формулу Ньютона: "+df.format(firstInterpolationFormulaNewton_2(x_for_2_3_1, list_x_for_2_3, list_y_for_2_3)));
            System.out.println("2.3) Приближенное значение функции используя вторую интерполяционную формулу Ньютона: "+df.format(secondInterpolationFormulaNewton_2(x_for_2_3_2, list_x_for_2_3, list_y_for_2_3)));
            System.out.println("2.4) Приближенное значение функции, используя интерполяционную формулу Ньютона для неравностоящих узлов: "+df.format(newtonForUnequallySpacedNodes(x_for_2_4, list_x_for_2_4, list_y_for_2_4)));
        }

    }
    static void filling(int size, List<Double> list, boolean console, Scanner scanner, FileInputStream in){
        int number;
        String value = "";
        if (console) {
            for (int i = 0; i < size; i++) {
                list.add(scanner.nextDouble());
//                System.out.println("hi");
//                System.out.println(scanner.nextLine()+"=");
            }
        }else {
            try{
                while((number = in.read()) != 13){
                    if(number==32){
//                        System.out.println(value);
                        list.add(new Double(value));
                        value="";
                        continue;
                    }
                    value=value+(char)number;
                }
//                System.out.println(value);
                list.add(new Double(value));
                in.read();
            }catch (IOException e) {
                System.out.println("Error in stream");
            }
        }
    }

    static double polynomialLagrange(double x, List<Double> list_x, List<Double> list_y){
        int n = 0;
        double result = 0;
        for(; n<list_x.size(); n++){
            if(list_x.get(n)>x){
                n++;
                break;
            }
        }
        for(int index= 0; index<=n; index++){
            result+=lagrange(x, index, n, list_x)*list_y.get(index);
        }
        return result;
    }

    static double lagrange(double x, int index, int n, List<Double> list_x){
        double l = 1;
        double const_val = list_x.get(index);
        for(int i = 0; i<=n; i++){
            if(list_x.get(i) == const_val){
               continue;
            }
            l=l*(x - list_x.get(i))/(const_val - list_x.get(i));
        }
        return l;
    }

    static double value_delta_x(double last_value_delta_x, List<Double> list_x, int index, double x){
        return last_value_delta_x * (x - list_x.get(index));
    }

    static double newtonForUnequallySpacedNodes(double x, List<Double> list_x, List<Double> list_y){
        double y01;
        double y12;
        double y012;
        double res = 0;
        byte count = 0;
        for(int i=2; list_x.get(i-2)<x; i++){
            count++;
            y01 = (list_y.get(i-1)-list_y.get(i-2))/(list_x.get(i-1)-list_x.get(i-2));
            y12 = (list_y.get(i)-list_y.get(i-1))/(list_x.get(i)-list_x.get(i-1));
            y012 = (y12-y01)/(list_x.get(i)-list_x.get(i-2));
            res += list_y.get(i-2) + y01 * (x - list_x.get(i-2)) + y012 * (x - list_x.get(i-2)) * (x - list_x.get(i-1));
        }
        if(count==2){
            return res/2;
        }
        return res;
    }

    static Map finiteDifferenceFunction(List<Double> list_y){
        Map<String, Double> map = new HashMap();
        for(int i=0; i<list_y.size()-1; i++){
            for(int j=0;j<list_y.size()-i-1;j++){
                if(i==0){
                    map.put("del"+(i+1)+""+j, list_y.get(j+1)-list_y.get(j));
//                    System.out.println("del"+(i+1)+""+j +" =  "+ (list_y.get(j+1)-list_y.get(j)));
                }else{
                    map.put("del"+(i+1)+""+j, map.get("del"+i+""+(j+1))-map.get("del"+i+""+j));
//                    System.out.println("del"+(i+1)+""+j + " = "+ (map.get("del"+i+""+(j+1))-map.get("del"+i+""+j)));
                }
            }
        }
        return map;
    }

    static double firstInterpolationFormulaNewton(double x, List<Double> list_x, List<Double> list_y){
        double res;
        int index = 0;

        for(; index<list_x.size(); index++){
            if(list_x.get(index)>x){
                index--;
                break;
            }
        }
        Map map = finiteDifferenceFunction(list_y);
        double t= (x - list_x.get(index))/ (list_x.get(1) - list_x.get(0));
        double last_t=t;
        int last_del=1;
        res = list_y.get(index);
        for(int i=index; i<list_x.size()-1; i++){               // ???   -1
//            System.out.println(map.get("del"+i+"1"));
//            for(int j=1; j<i+1 ; j++){

            last_del*=i;
//            }
//            System.out.println("lt="+last_t);
//            System.out.println(map.get("del"+i +""+ index ));
            try {
                res += (double) map.get("del"+i +""+ index ) * last_t / last_del;
            }catch(Exception e){
                System.out.println(i);
            }
            last_t*=(t-i);
        }
//        System.out.println(index);

        return res;
    }

    static double firstInterpolationFormulaNewton_2(double x, List<Double> list_x, List<Double> list_y){
        int index = 0;

        for(; index<list_x.size(); index++){
            if(list_x.get(index)>x){
                index--;
                break;
            }
        }
        double t = 1;
        double res = list_y.get(index);
        int denominator = 1;
        Map<String, Double> map = finiteDifferenceFunction(list_y);

        for(int i=1; i<=list_y.size()-1-index; i++){
            t = t * (((x - list_x.get(index)) / (list_x.get(1) - list_x.get(0))) - i + 1);
            denominator = denominator * i;
            res = res + t * map.get("del"+i+""+index) / denominator;
        }

        return res;
    }

    static double secondInterpolationFormulaNewton_2(double x, List<Double> list_x, List<Double> list_y){
        int index = list_y.size() - 1;

        for(; index>=0; index--){
            if(list_x.get(index)<x){
                index++;
                break;
            }
        }

        double t = 1;
        double res = list_y.get(index);
        int denominator = 1;
        Map<String, Double> map = finiteDifferenceFunction(list_y);

        for(int i=1; i<=index; i++){
            t = t * (((x - list_x.get(index)) / (list_x.get(1) - list_x.get(0))) + i - 1);
            denominator = denominator * i;
            res = res + t * map.get("del"+i+""+(index - i)) / denominator;
//            System.out.println("del"+i+""+(index - i));
        }

        return res;
    }
}


/*
0,611
7
0,593 0,598 0,605 0,613 0,619 0,627 0,632
0,532 0,5356 0,5406 0,5462 0,5504 0,5559 0,5594

0,551
7
0,5 0,55 0,6 0,65 0,7 0,75 0,8
1,5320 2,5356 3,5406 4,5462 5,5504 6,5559 7,5594

0,783
7
0,5 0,55 0,6 0,65 0,7 0,75 0,8
1,5320 2,5356 3,5406 4,5462 5,5504 6,5559 7,5594

0,595
7
0,593 0,598 0,605 0,613 0,619 0,627 0,632
0,532 0,5356 0,5406 0,5462 0,5504 0,5559 0,5594
*/
