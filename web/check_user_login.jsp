<%-- 
    Document   : check_user_login
    Created on : Apr 30, 2022, 4:19:52 PM
    Author     : Egor
--%>

<%@page import="classes.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<jsp:useBean id="db" class="classes.DBInit" scope="application">

</jsp:useBean>

<jsp:useBean id="u" class="classes.User" scope="page">
    <jsp:setProperty property="*" name="u"/>
</jsp:useBean>

<%

    User userCheck = db.loginUser(u);

    if (userCheck.isLoggedIn()) {

        Cookie id = new Cookie("id", String.valueOf(userCheck.getId()));
        Cookie username = new Cookie("username", userCheck.getUsername());
        Cookie email = new Cookie("email", userCheck.getEmail());

        id.setDomain("localhost");
        username.setDomain("localhost");
        email.setDomain("localhost");

        response.addCookie(id);
        response.addCookie(username);
        response.addCookie(email);
        response.sendRedirect("userMainMenu.html");

%>

<%
} else {
%>
<h3 style='margin-left: 16%; margin-top: 3%'>Invalid Username or Password</h3>
<jsp:include page="index.html"/>
<%
    }

%>

