
<%@page import="java.util.ArrayList"%>
<%@page import="Logica.Horario"%>
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
                                <h4>Alta Videos</h4>
                            </div>
                            <div class="card-body">
                                <form action="svVideo" method="POST" enctype="multipart/form-data">
                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <div class="form-floating mb-3 mb-md-0">
                                                <input class="form-control" name = "titulo" id="titulo" type="text" placeholder="Ingrese el titulo" required/>
                                                <label for="inputUser">Titulo</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <div class="form-floating">
                                                <%                                                            
                                                    Controladora control = new Controladora();
                                                    List<Horario> listaHorarios = new ArrayList<Horario>();
                                                    listaHorarios = control.getHorario();
                                                %>     
                                                <select class="form-control" id="horarios" name="horarios" required>
                                                    <option value="">--- Seleccione---</option>
                                                    <%
                                                        for (Horario hor : listaHorarios) {
                                                    %>
                                                    <option value="<%=hor.getId_horario()%>"><%=hor.getHorario_inicio()%> <%=hor.getHorario_fin()%></option>
                                                    <%
                                                        }
                                                    %>
                                                </select>
                                                <label for="inputUser">asignar horarios</label>    
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-floating mb-3 mb-md-0">
                                                <input class="form-control" name = "archivos" id="archivos" type="file" placeholder="Ingrese el archivo" required/>
                                                <label for="inputUser">Videos</label>
                                            </div>
                                        </div>
                                    <div class="row mb-3">
                                        <div class="col-md-12">
                                            <div class="form-floating">
                                                <%  
                                                    String errorMessage = (String) request.getAttribute("errorMessage");
                                                    if (errorMessage != null) {
                                                %>

                                                <div style="color: red; font-weight: bold;">
                                                    <%=errorMessage%>
                                                </div>
                                                <%
                                                    }
                                                %>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-md-3">
                                            <div class="mt-4 md-0">
                                                <div class="d-grid">
                                                    <button class="btn btn-primary btn-block" type="submit">Publicar Video</button>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="mt-4 md-0">
                                                <div class="d-grid">
                                                    <a class="btn btn-danger btn-block" href="svVideo">Cancelar</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
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

