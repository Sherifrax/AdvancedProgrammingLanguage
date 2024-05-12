<%-- 
    Document   : history
    Created on : May 3, 2022, 11:12:03 PM
    Author     : Egor
--%>

<%@page import="classes.DBInit"%>
<%@page import="classes.History"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>



<%
    Cookie[] c = request.getCookies();
    String u_id = null;
    DBInit db = new DBInit();

    for (Cookie cookie : c) {
        if (cookie.getName().equals("id")) {
            u_id = cookie.getValue();
            break;
        }
    }

    ArrayList<History> h = db.getHistory(Integer.parseInt(u_id));

    if (h == null || h.isEmpty()) {
        out.print("<h3 style='margin-left: 16%; margin-top: 3%'>Your history is empty please take an exam first then check again</h3>");
%>
<jsp:include page="userMainMenu.html"/>
<%
} else {

%>
<div class="container">
    <h1 style="margin-top: 5%">History</h1>
</div>
<div class="container">
    <table class="table table-dark table-hover" style="margin-top: 5%">
        <thead>
            <tr>
                <th scope="col">User ID</th>
                <th scope="col">Grade</th>
                <th scope="col">Course</th>
                <th scope="col">Date</th>
            </tr>
        </thead>
        <tbody>
            <%    for (History history : h) {
            %>

            <tr>
                <th scope="row"><%=history.getU_id()%></th>
                <td><%=history.getGrade()%></td>
                <td><%=history.getC_name()%></td>
                <td><%=history.getDate()%></td>
            </tr>

            <%
                }

            %>
        </tbody>

    </table>
    <button type="button" class="btn btn-danger" style="margin-right: 10px;"><a href="userMainMenu.html" style="text-decoration: none; color: inherit">Back</a></button>

</div>
<%    }

%>
