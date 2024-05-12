package classes;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SaveQuestion", urlPatterns = {"/SaveQuestion"})
public class SaveQuestion extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        
        PrintWriter out = response.getWriter();
        
        String q = request.getParameter("question");
        String a = request.getParameter("answer");
        String c = request.getParameter("course");
        
        
        DBInit db = new DBInit();
        int updated = db.addQuestion(q, a, c);
        
        if(updated > 0) {
            out.print("<h3 style='margin-left: 16%; margin-top: 3%'>Question Added</h3>");
            request.getRequestDispatcher("adminMainMenu.jsp").include(request, response);
        }
        else {
            out.print("<h3 style='margin-left: 16%; margin-top: 3%'>Error Adding Question</h3>");
            request.getRequestDispatcher("adminMainMenu.jsp").include(request, response);
        }
        
    }

}
