//package src;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AreaCheckServlet extends HttpServlet {


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int x=0,r=0;
        double y=0;
        try {
            x = new Integer(this.getServletContext().getAttribute("x").toString());
            y = new Double(this.getServletContext().getAttribute("y").toString());
            r = new Integer(this.getServletContext().getAttribute("r").toString());
        }
        catch(NullPointerException e){
            RequestDispatcher dispatcher = req.getRequestDispatcher("/kek");
            dispatcher.forward(req, resp);
        }
        req.setAttribute("x",x);
        req.setAttribute("y",y);
        req.setAttribute("r",r);
        req.setAttribute("find",find(x,y,r));

//        PrintWriter pw = resp.getWriter();
//            pw.write("x="+x+" ");
//            pw.write("y="+y+" ");
//            pw.write("r="+r+" ");

        RequestDispatcher dispatcher = req.getRequestDispatcher("/R.jsp");
        dispatcher.forward(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private boolean find(int x, double y, int r){
        if(x>=0 & x<=r){
            if(y<=r/2 & y>x-r){
                return true;
            }
        }else{
            if(y<=0 & x*x+y*y<=r/2){
                return true;
            }
        }
        return false;
    }

}


