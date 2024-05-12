package classes;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@WebServlet(name = "Result", urlPatterns = {"/Result"})
public class Result extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String u_id = null, username = null, email = null, c_name = null;
        int res = 0;

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        DBInit db = new DBInit();

        Cookie[] questions = request.getCookies();
        String[] ans = new String[5];

        for (int i = 0; i < 5; i++) {
            ans[i] = request.getParameter("answer" + (i + 1));
        }

        for (Cookie q : questions) {

            if (q.getName().equals("id")) {
                u_id = q.getValue();
            } else if (q.getName().equals("username")) {
                username = q.getValue();
            } else if (q.getName().equals("email")) {
                email = q.getValue();
            } else if (q.getName().equals("c1")) {
                c_name = q.getValue();
            } else if (q.getName().equals("q1")) {
                if (db.checkAnswer(q.getValue(), ans[0])) {
                    res += 20;
                }
            } else if (q.getName().equals("q2")) {
                if (db.checkAnswer(q.getValue(), ans[1])) {
                    res += 20;
                }
            } else if (q.getName().equals("q3")) {
                if (db.checkAnswer(q.getValue(), ans[2])) {
                    res += 20;
                }
            } else if (q.getName().equals("q4")) {
                if (db.checkAnswer(q.getValue(), ans[3])) {
                    res += 20;
                }
            } else if (q.getName().equals("q5")) {
                if (db.checkAnswer(q.getValue(), ans[4])) {
                    res += 20;
                }
            }

        }

        int updated = db.addHistory(u_id, res, c_name);

        if (updated > 0) {
            out.print("<h3 style='margin-left: 16%; margin-top: 3%'>Your result will be sent via email please check your inbox to confirm</h3>");

            String to = email;
            String from = "apaprojectmail@gmail.com";
            String host = "smtp.gmail.com";
            
            Properties properties = System.getProperties();
            
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port", "465");
            properties.put("mail.smtp.ssl.enable", "true");
            properties.put("mail.smtp.auth", "true");

            Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("apaprojectmail@gmail.com", "Project123");
                }
            });
            
            session.setDebug(true);
            try {
                
                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
                message.setSubject("Results for Course: " + c_name);
                message.setText("User ID: " + u_id + "\nUsername: " + username + "\nResult: " + res);
                System.out.println("sending...");
                
                Transport.send(message);
                System.out.println("Sent message successfully....");
            } catch (MessagingException mex) {
                mex.printStackTrace();
            }

            request.getRequestDispatcher("userMainMenu.html").include(request, response);
        } else {
            out.print("end my life");
        }

    }

}
