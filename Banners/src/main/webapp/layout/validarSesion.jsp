
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession misession = request.getSession();
    String usuarios = (String) request.getSession().getAttribute("usuarios");
    
    if (usuarios == null) {
            boolean error = false;
            misession.setAttribute("error", error);
            response.sendRedirect("login.jsp");
        }
    
%>