
<%@page import="java.util.List"%>
<%@page import="Logica.Videos"%>
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
                            </div>
                            <div class="card-body">
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th>Id</th>
                                            <th>Titulo</th>
                                            <th>Fecha de inicio</th>
                                            <th>Fecha de fin</th>
                                            <th width="10%">Acciones</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>Id</th>
                                            <th>Titulo</th>
                                            <th>Fecha de inicio</th>
                                            <th>Fecha de fin</th>
                                            <th>Acciones</th>
                                        </tr>
                                    </tfoot>
                                    <% List<Videos> listaVideos = (List) request.getSession().getAttribute("listaVideos");%>
                                    <tbody>
                                        <% for (Videos vid : listaVideos) {%>
                                        <tr>
                                            <td><%=vid.getId_video()%></td>
                                            <td><%=vid.getTitulo()%></td>
                                            <td><%=vid.getUnHorario().getHorario_inicio()%></td>
                                            <td><%=vid.getUnHorario().getHorario_fin()%></td>
                                            <td>
                                                <form name="eliminar" action="svEliminarvid" method="POST" style="float: left; margin-right: 10px">
                                                    <button class="btn btn-outline-danger btn-block" title="Eliminar Registro">
                                                        <i class="fa fa-trash" aria-hidden="true"></i>
                                                    </button>
                                                    <input type="Hidden" name="id" value="<%=vid.getId_video()%>">
                                                </form>
                                                <form name="editar" action="svEditvideo" method="GET">
                                                    <button class="btn btn-outline-info btn-block" title="Editar Registro">
                                                        <i class="fa fa-pencil" aria-hidden="true"></i>
                                                    </button>
                                                    <input type="Hidden" name="id" value="<%=vid.getId_video()%>">
                                                </form>
                                            </td>
                                        </tr>
                                        <% }%>
                                    </tbody>
                                </table>
                            </div>
                            <div class="row mb-3">
                                <center>
                                <div class="col-md-2">
                                    <div class="mt-4 md-0">
                                        <div class="d-grid">
                                            <a class="btn btn-primary btn-block" href="altaVideo.jsp">Enlistar Videos</a>
                                        </div>
                                    </div>
                                </div>
                                </center>
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

