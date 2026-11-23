/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.banners;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Logica.Controladora;
import Logica.Datos;
import Logica.Horario;
import Logica.Videos;
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
@WebServlet(name = "svVideo", urlPatterns = {"/svVideo"})
@MultipartConfig
public class svVideo extends HttpServlet {

    private String[] extens = {".mp4",".mkv",".wmv"};
    Controladora control = new Controladora();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    List<Videos>listaVideos = new ArrayList<Videos>();
        listaVideos = control.getVideos();
        HttpSession misession = request.getSession();
        misession.setAttribute("listaVideos", listaVideos);
        response.sendRedirect("verVideos.jsp");
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
        processRequest(request, response);

        String titulo = request.getParameter("titulo");
        String archivos = request.getParameter("archivos");
        Part part = request.getPart("archivos");
        int idHorario = Integer.parseInt(request.getParameter("horarios"));
        
        Horario horario = control.traerHorario(idHorario);
        if (part == null) {
                System.out.println("No ha seleccionado un archivo");
                return;
            }
            
            if (isExtension(part.getSubmittedFileName(), extens)) {
                Datos datos = new Datos();
                datos= control.traerDatos(1);
                String pathFiles= datos.getRuta();
                File uploads = new File(pathFiles);
                
                String archivo = saveFile(part, uploads);
            }
            Videos vid = new Videos();
            vid.setTitulo(titulo);
            vid.setArchivo("archivos/" + part.getSubmittedFileName());
            vid.setUnHorario(horario);
            
        control.crearVideos(vid);
        
        response.sendRedirect("svVideo");
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
