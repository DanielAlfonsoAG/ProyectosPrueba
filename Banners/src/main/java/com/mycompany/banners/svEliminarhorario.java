/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.mycompany.banners;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Logica.Controladora;
import Logica.Horario;
import javax.servlet.http.HttpSession;

/**
 *
 * @author agude
 */
@WebServlet(name = "svEliminarhorario", urlPatterns = {"/svEliminarhorario"})
public class svEliminarhorario extends HttpServlet {

    Controladora control = new Controladora();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        int id = Integer.parseInt(request.getParameter("id"));
        boolean horarios = control.comprobarHorarios(id);
        boolean error = false;
        HttpSession misession = request.getSession();
        
        if (horarios) {
            error = true;
            misession.setAttribute("error", error);
            response.sendRedirect("svHorario");
        }
        else{
            control.borrarHorario(id);
            misession.setAttribute("error", error);
            response.sendRedirect("svHorario");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
