/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.banners;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Logica.Usuario;
import Logica.Controladora;
import Logica.Datos;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

/**
 *
 * @author agude
 */
@WebServlet(name = "svUsuario", urlPatterns = {"/svUsuario"})
@MultipartConfig
public class svUsuario extends HttpServlet {
    
    private String[] extens = {".jpg",".png"};
    Controladora control = new Controladora();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Usuario>listaUsuarios = new ArrayList<Usuario>();
        listaUsuarios= control.getUsuario();
        HttpSession misession = request.getSession();
        misession.setAttribute("listaUsuarios", listaUsuarios);
        response.sendRedirect("verUsuarios.jsp");
    }
    
    private String saveFile(Part part, File pathUploads){
    String pathAbsolute="";
        try {
            Path path = Paths.get(part.getSubmittedFileName());
            String filename = path.getFileName().toString();
            InputStream input = part.getInputStream();
            if (input != null) {
                File file = new File(pathUploads,filename);
                pathAbsolute = file.getPath();
                
                Files.copy(input, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pathAbsolute;
    }
    private boolean isExtension(String fileName, String[] extensions){
        for (String et: extensions) {
            if (fileName.toLowerCase().endsWith(et)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String image = request.getParameter("image");
        Part part = request.getPart("image");
        String usuarios = request.getParameter("usuarios");
        String contrasenia = request.getParameter("contrasenia");
        String rol = request.getParameter("rol");
        if (part == null) {
                System.out.println("No ha seleccionado un archivo");
                return;
            }
            
            if (isExtension(part.getSubmittedFileName(), extens)) {
                Datos datos = new Datos();
                datos= control.traerDatos(2);
                String pathFiles= datos.getRuta();
                File uploads = new File(pathFiles);
                
                String archivo = saveFile(part, uploads);
            }
            Usuario usu = new Usuario();
            usu.setUsuarios(usuarios);
            usu.setContrasenia(contrasenia);
            usu.setRol(rol);
            usu.setImage("img/" + part.getSubmittedFileName());
            
        control.crearUsuario(usu);
        response.sendRedirect("svUsuario");
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
