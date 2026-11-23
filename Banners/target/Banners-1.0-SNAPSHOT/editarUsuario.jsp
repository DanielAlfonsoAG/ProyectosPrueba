
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

                            </div>
                            <div class="card-body">
                                <p>
                                    Seccion para modificar los datos de un usuario en el sistema
                                </p>
                                <% Usuario usu = (Usuario) request.getSession().getAttribute("usuEditar");%>
                                <center>
                                    <%
                                    if (request.getSession().getAttribute("image") == null) { 
                                    %>
                                    <div class="profile-container">
                                        <img src="img/perfil.png" alt="alt"/>
                                    </div>
                                    <br>
                                    <%=request.getSession().getAttribute("usuarios")%>
                                    <br>
                                    <%
                                    }else{ 
                                    %>
                                    <div class="profile-container">
                                        <img src="<%=request.getSession().getAttribute("image")%>" width="30%" height="30%" alt="alt"/>
                                    </div>
                                    <br>
                                    <%=request.getSession().getAttribute("usuarios")%>
                                    <br>
                                    <% } %>
                                </center>
                                    <br>
                                <form action="svEditusu" method="POST" enctype="multipart/form-data">
                                    <div class="row mb-3">
                                        <center>
                                        <div class="col-md-3">
                                            <input class="form-control" name = "image" id="image" type="file" placeholder="Ingrese la imagen" required/>  
                                        </div>
                                        </center>
                                    </div>
                                    <br>
                                    <div class="row mb-3">
                                        <div class="col-md-6">
                                            <div class="form-floating mb-3 mb-md-0">
                                                <input class="form-control" name="usuarios" id="usuarios" type="text" placeholder="Ingrese el nombre del usuario" required value="<%=usu.getUsuarios()%>"/>
                                                <label for="inputUser">Nombre Usuario</label>
                                            </div>
                                        </div>
                                        <div class="col-md-6">
                                            <div class="form-floating">
                                                <input class="form-control" name="contrasenia" id="contrasenia" type="password" placeholder="Ingrese la contraseña" value="<%=usu.getContrasenia()%>" required/>
                                                <label for="inputPassword">Contraseña</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-md-3">
                                            <div class="form-floating mb-3 mb-md-0">
                                                <%
                                                String rol = (String) request.getSession().getAttribute("rol");
                                                if ("Admin".equals(rol)) {
                                                   %>
                                                <select class="form-control" id="rol" name="rol" value="<%=usu.getRol()%>" required >
                                                    <option value="">--- Selecciona ---</option>
                                                    <option value="Admin">Admin</option>
                                                    <option value="Usuario">Usuario</option>
                                                </select>
                                                <% } %>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row mb-3">
                                        <div class="col-md-3">
                                            <div class="mt-4 md-0">
                                                <div class="d-grid">
                                                    <button class="btn btn-primary btn-block" type="submit">Editar Usuario</button>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="mt-4 md-0">
                                                <div class="d-grid">
                                                    <a class="btn btn-danger btn-block" href="index.jsp">Cancelar</a>
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

