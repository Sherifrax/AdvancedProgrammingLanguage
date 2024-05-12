


<%@page import="java.util.logging.Logger"%>
<%@page import="java.util.logging.Level"%>
<%@page import="java.sql.*"%>
<%@page import="classes.User"%>
<%@page import="classes.DBInit"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<jsp:useBean id="db" class="classes.DBInit" scope="application">

</jsp:useBean>

<jsp:useBean id="u" class="classes.User" scope="page">
    <jsp:setProperty property="*" name="u"/>
</jsp:useBean>





<%

    int updated = db.storeUser(u);
    System.out.println(updated);

    if (updated > 0) {
        request.getRequestDispatcher("index.html").forward(request, response);
    } else {
        session.removeAttribute("u");
%>

<h3 style='margin-left: 16%; margin-top: 3%'>An Error Has Occurred Try Again Later</h3>
<jsp:include page="register.html"/>

<%
    }

%>



