import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AreaCheckServlet extends HttpServlet {


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int x,r;
        double y;

        x=new Integer(req.getParameter("x"));
        y=new Double(req.getParameter("y"));
        r=new Integer(req.getParameter("r"));

        req.setAttribute("find",find(x,y,r));

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


