//package src;

import src.Results;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class AreaCheckServlet extends HttpServlet {


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int r=0;
        double x=0,y=0;

        PrintWriter pw = resp.getWriter();
//        pw.write((req.getParameter("iscanvas")==null)+"");


            if (req.getParameter("iscanvas")!=null){
                if(req.getParameter("iscanvas").equals("true")){
                    x = new Double(req.getParameter("x"));
                    y = new Double(req.getParameter("y"));
                    r = new Integer(req.getParameter("r"));
                }else{
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/kek");
                    dispatcher.forward(req, resp);
                    return;
                }
            }else{
                try {
                    x = new Double(this.getServletContext().getAttribute("x").toString());
                    y = new Double(this.getServletContext().getAttribute("y").toString());
                    r = new Integer(this.getServletContext().getAttribute("r").toString());
                } catch (NullPointerException e) {
                    RequestDispatcher dispatcher = req.getRequestDispatcher("/kek");
                    dispatcher.forward(req, resp);
                    return;
                }
            }

//        req.setAttribute("x",x);
//        req.setAttribute("y",y);
//        req.setAttribute("r",r);
//        req.setAttribute("find",find(x,y,r));
        if(this.getServletContext().getAttribute("listResults") == null){
            this.getServletContext().setAttribute("listResults", new ArrayList<Results>());
        };
        ArrayList<Results> listResults = (ArrayList<Results>) this.getServletContext().getAttribute("listResults");

        boolean result = find(x,y,r);
        listResults.add(new Results(x, y, r, result));
        req.setAttribute("x",x);
        req.setAttribute("y",y);
        req.setAttribute("r",r);
        req.setAttribute("find",result);

//        pw.write(listResults.size());

        RequestDispatcher dispatcher = req.getRequestDispatcher("/R.jsp");
        dispatcher.forward(req, resp);

    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private boolean find(double x, double y, int r){
        if(x>=0 & x<=r){
            if(y<=(double)r/2 & y>x-r){
                return true;
            }
        }else{
            if(y<=0 & x*x+y*y<=(double)(r*r)/4){
                return true;
            }
        }
        return false;
    }

}


