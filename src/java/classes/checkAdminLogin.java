package classes;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "checkAdminLogin", urlPatterns = {"/checkAdminLogin"})
public class checkAdminLogin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html");
        
        String uname = request.getParameter("username");
        String pass = request.getParameter("password");
        PrintWriter out = response.getWriter();
        
        if(uname.equals("admin") && pass.equals("admin")) {
            request.getRequestDispatcher("adminMainMenu.jsp").forward(request, response);
        }
        else {
            out.print("<h3 style='margin-left: 16%; margin-top: 3%'>Incorrect username or password</h3>");
            request.getRequestDispatcher("adminLogin.html").include(request, response);
        }
        
    }

}
