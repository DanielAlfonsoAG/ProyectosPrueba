
<%@page contentType="text/html" pageEncoding="UTF-8"%>
                <nav class="sb-sidenav accordion sb-sidenav-light" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
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
                                    <img src="<%=request.getSession().getAttribute("image")%>" alt="alt"/>
                                </div>
                                <br>
                                <%=request.getSession().getAttribute("usuarios")%>
                                <br>
                                <% } %>
                            </center>
                            <div class="sb-sidenav-menu-heading">Interface</div>
                             <%
                                 String rol = (String) request.getSession().getAttribute("rol");
                            if ("Admin".equals(rol)) {
                                    %>   
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#pagesCollapseAuth" aria-expanded="false" aria-controls="pagesCollapseAuth">
                                <i class="fa-solid fa-user-check"></i> Autenticacion
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="pagesCollapseAuth" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordionPages">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="svUsuario">Ver Usuarios</a>
                                    <a class="nav-link" href="altaUsuario.jsp">Crear Usuarios</a>
                                </nav>
                            </div>
                            <% }%>
                            <% if ("Admin".equals(rol)) {
                                    %> 
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseHorarios" aria-expanded="false" aria-controls="collapseHorarios">
                                <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                                Horarios
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapseHorarios" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="svHorario">Ver Horarios</a>
                                    <a class="nav-link" href="altaHorario.jsp">Crear Horarios</a>
                                </nav>
                            </div>
                            <% }%>
                            <% if ("Admin".equals(rol)) {
                                    %> 
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseSecretarios" aria-expanded="false" aria-controls="collapseSecretarios">
                                <div class="sb-nav-link-icon"><i class="fa fa-play-circle" aria-hidden="true"></i></div>
                                Videos
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapseSecretarios" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="svVideo">Lista de Videos</a>
                                    <a class="nav-link" href="altaVideo.jsp">Enlistar un Video</a>
                                </nav>
                            </div>
                            <% }%>
                        </div>
                    </div>
                        <% if ("Admin".equals(rol)) {
                        %> 
                    <div class="sb-sidenav-footer">
                        <div class="small">logueado como:</div>
                        <%=rol%>
                    </div>
                        <% }%>
                </nav>
