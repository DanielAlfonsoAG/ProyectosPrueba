
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Logica.Videos"%>
<%@page import="java.util.List"%>
<%@page import="Logica.Controladora"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <jsp:include page="layout/header.jsp"/>
    <body class="sb-nav-fixed">
        
        <%@include file="layout/validarSesion.jsp"%>
        <jsp:include page="layout/NavegacionHorizontal.jsp"/>   
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <jsp:include page="layout/NavegacionVertical.jsp"/>  
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Videos Presentacion</h1>
                        <div class="card mb-4">
                            <div class="card-header">
                                Videos Presentativos
                            </div>
                            <div class="card-body">
                                <%
                                    Controladora control = new Controladora();
                                    List<Videos> ListaEventos = new ArrayList<Videos>();

                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                                    LocalDateTime fecha = LocalDateTime.now();
                                    String fechaFormateada = fecha.format(formatter);
                                    if (request.getSession().getAttribute("id_usuario") != null) {
                                        ListaEventos = control.listaVideosHoy(fechaFormateada);
                                    }
                                    int nEventos = ListaEventos.size();
                                %>
                                  <% if (nEventos == 0) { %>
                                  <center><img src="img/videonodisponible.jpg" alt="videos"/></center>
                                    <% } else { %>
                                    <%for (Videos vid1 : ListaEventos) {%>
                                
                                    <h1><%=vid1.getTitulo()%></h1>
                                    <br>
                                <center>
                                    <video width="80%" height="80%" controls autoplay>
                                        <source src="<%=vid1.getArchivo()%>" type="video/mp4">
                                    </video>
                                </center>                                
                                    <% } %>
                                    <% }%>
                            </div>
                        </div>
                    </div>
                </main>
                <jsp:include page="layout/footer.jsp"/>
            </div>
        </div>
        <jsp:include page="layout/scripts.jsp"/>
    </body>
</html>

