
<%@page import="classes.DBInit"%>
<%@page import="classes.Question"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>


<%
    String course = request.getParameter("course");
    if (course.equals("")) {
        out.print("<h3 style='margin-left: 16%; margin-top: 3%'>Enter a Valid Course</h3>");
%>

<jsp:include page="userMainMenu.html"/>
<%
} else {
    int i = 1;
    DBInit db = new DBInit();
    ArrayList<Question> questions = db.getQuestions(course);    

    if (questions != null) {
        Cookie q1 = new Cookie("q1", questions.get(0).getQuestion());
        Cookie q2 = new Cookie("q2", questions.get(1).getQuestion());
        Cookie q3 = new Cookie("q3", questions.get(2).getQuestion());
        Cookie q4 = new Cookie("q4", questions.get(3).getQuestion());
        Cookie q5 = new Cookie("q5", questions.get(4).getQuestion());
        Cookie c1 = new Cookie("c1", questions.get(0).getC_name());

        response.addCookie(q1);
        response.addCookie(q2);
        response.addCookie(q3);
        response.addCookie(q4);
        response.addCookie(q5);
        response.addCookie(c1);

%>

<form action="Result">
    <div class="container">
        <h1 style="margin-bottom: 3%; margin-top: 3%">Exam for Course: <%=course%></h1>
        <%
            for (Question q : questions) {
        %>

        <%
            out.print("<h5>" + q.getQuestion() + "</h5>");
        %>
        <input class="form-control form-control-lg" type="text" name="answer<%=i%>">
        <%
                i++;
            }
        %>
        <input type="submit" value="Submit" name="submit1" class="btn btn-primary" style="margin-right: 10px; margin-top: 15px"/>


    </div>
</form>
<%
} else {
    out.print("<h1 style='margin-bottom: 3%; margin-top: 3%'>Unxepected Error Try Again Later</h1>");
%>
<button type="submit" class="btn btn-danger" formaction="userMainMenu.html" style="margin-right: 10px; margin-top: 15px">Back</button>
<%
        }
    }
%>






