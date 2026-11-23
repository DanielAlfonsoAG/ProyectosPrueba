
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%--<%@page import="logica.Turno"%>--%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%--<%@page import="logica.Controladora"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="sb-topnav navbar navbar-expand navbar-dark bg-black">
    <!-- Navbar Brand-->
    <a class="navbar-brand ps-3" href="index.jsp">Videos Presentacion</a>
    <!-- Sidebar Toggle-->
    <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
    <!-- Navbar Search-->
    <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
    </form>
    <!-- Navbar-->
    <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
        <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i><%=request.getSession().getAttribute("usuarios")%></a>
            <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                <form name="editar" action="svEditusu" method="GET">
                    <button class="dropdown-item" title="Editar Registro">
                        Editar Perfil
                    </button>
                    <input type="Hidden" name="id" value="<%=request.getSession().getAttribute("id_usuario")%>">
                </form>
                
                <li><a class="dropdown-item" href="svLogin">Cerrar sesión</a></li>
            </ul>
        </li>
    </ul>
</nav>
