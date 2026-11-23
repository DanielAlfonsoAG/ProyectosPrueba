
<%@page import="java.util.List"%>
<%@page import="Logica.Usuario"%>
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
                                <table id="datatablesSimple">
                                    <thead>
                                        <tr>
                                            <th>Id</th>
                                            <th>Nombre de Usuario</th>
                                            <th>Rol</th>
                                            <th width="10%">Acciones</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>Id</th>
                                            <th>Nombre de Usuario</th>
                                            <th>Rol</th>
                                            <th>Acciones</th>
                                        </tr>
                                    </tfoot>
                                    <% List<Usuario> listaUsuarios = (List) request.getSession().getAttribute("listaUsuarios");%>
                                    <tbody>
                                        <% for (Usuario usu : listaUsuarios) {%>
                                        <tr>
                                            <td><%=usu.getId_usuario()%></td>
                                            <td><%=usu.getUsuarios()%></td>
                                            <td><%=usu.getRol()%></td>
                                            <td>
                                                <form id="eliminar" action="svEliminarusu" method="POST" style="float: left; margin-right: 10px">
                                                    <button class="btn btn-outline-danger btn-block" title="Eliminar Registro">
                                                        <i class="fa fa-trash" aria-hidden="true"></i>
                                                    </button>
                                                    <input type="Hidden" name="id" value="<%=usu.getId_usuario()%>">
                                                </form>
                                                <form name="editar" action="svEditusu" method="GET">
                                                    <button class="btn btn-outline-info btn-block" title="Editar Registro">
                                                        <i class="fa fa-pencil" aria-hidden="true"></i>
                                                    </button>
                                                    <input type="Hidden" name="id" value="<%=usu.getId_usuario()%>">
                                                </form>
                                            </td>
                                        </tr>
                                        <% }%>
                                    </tbody>
                                </table>
                                <div class="row mb-3">
                                    <center>
                                    <div class="col-md-2">
                                        <div class="mt-4 md-0">
                                            <div class="d-grid">
                                                <a class="btn btn-primary btn-block" href="altaUsuario.jsp">Crear usuarios</a>
                                            </div>
                                        </div>
                                    </div>
                                    </center>
                                </div>    
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

