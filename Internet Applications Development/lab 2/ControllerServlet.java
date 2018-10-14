//package src;//http://localhost:5280/mywar/kek

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String x=req.getParameter("x");
        String y=req.getParameter("y");
        String r=req.getParameter("r");

        int x_code=volidet(x, "x");
        int y_code=volidet(y, "y");
        int r_code=volidet(r, "r");

        if((x_code + y_code + r_code)==0) {
            this.getServletContext().setAttribute("x", x);
            this.getServletContext().setAttribute("y", y);
            this.getServletContext().setAttribute("r", r);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/check");
            dispatcher.forward(req, resp);
        }else{
            this.getServletContext().setAttribute("x", x_code);
            this.getServletContext().setAttribute("y", y_code);
            this.getServletContext().setAttribute("r", r_code);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/Start.jsp");
            dispatcher.forward(req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private int volidet(String arg, String name){
        int result=0;
        double arg_num=0;
        boolean res=true;
        if(arg==null){
            result=result+100;
        }else {
            try {
                arg_num = new Double(arg);
            } catch (NumberFormatException ex1) {
                arg=comma(arg);
                try {
                    arg_num = new Double(arg);
                } catch (NumberFormatException ex2) {
                    res = false;
                    result = result + 400;
                }
            }
            if (res) {
                if(arg.charAt(0)=='.'){
                    result=result+200;
                }else {
                    if (name == "x") {
                        result = result + volidetRangeForX(arg_num);
                    } else if (name == "y") {
                        result = result + volidetRangeForY(arg_num);
                    } else if (name == "r") {
                        result = result + volidetRangeForR(arg_num);
                    }
                }
            }
        }
        return result;
    }

    private int volidetRangeForX(double arg_num){
        if (Math.abs(arg_num)!=4 && Math.abs(arg_num)!=3 && Math.abs(arg_num)!=2 && Math.abs(arg_num)!=1 && Math.abs(arg_num)!=0) {
            return 800;
        }
        return 0;
    }

    private int volidetRangeForY(double arg_num){
        if (arg_num<=-3 | arg_num>=5) {
            return 80;
        }
        return 0;
    }

    private int volidetRangeForR(double arg_num){
        if (arg_num!=1 && arg_num!=2 && arg_num!=3 && arg_num!=4 && arg_num!=5) {
            return 8;
        }
        return 0;
    }

    private String comma(String arg){
        int pos = arg.indexOf(',');
        if(pos!=-1 & pos!=0 & pos!=arg.length()-1){
            try {
                new Double(arg.substring(0,pos));
                new Double(arg.substring(pos+1));
            }catch(NumberFormatException ex) {
                return arg;
            }
            return arg.substring(0,pos)+'.'+arg.substring(pos+1);
        }else{
            return arg;
        }
    }

}

