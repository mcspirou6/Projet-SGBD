/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.myapp;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;


/**
 *
 * @author ROYAL COMPUTER
 */
@WebServlet(urlPatterns = {"/InscriptionServlet"})
public class InscriptionServlet extends HttpServlet {

    @Resource(name = "jdbc/OracleDB")
    private DataSource dataSource;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Employe> employes = retrieveEmployeListFromDatabase();
        request.setAttribute("employes", employes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Liste.jsp");
        dispatcher.forward(request, response);
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idEmployeStr = request.getParameter("id_employe");
        int idEmploye = Integer.parseInt(idEmployeStr); // Convertir en entier

        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String telephone = request.getParameter("telephone");
        
        try (Connection connection = dataSource.getConnection()) {
        System.out.println("Database connected successfully!");
        } catch (SQLException e) {
        e.printStackTrace();
        }


        try (Connection connection = dataSource.getConnection()) {
            String sql = "INSERT INTO employe (ID_EMP, NOM_EMP, PRENOM_EMP, NUM_TEL) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, idEmploye);
                statement.setString(2, nom);
                statement.setString(3, prenom);
                statement.setString(4, telephone);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }

        response.sendRedirect("Liste.jsp");       
    }
    
    private List<Employe> retrieveEmployeListFromDatabase() throws ServletException {
        List<Employe> employes = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            String sql = "SELECT ID_EMP, NOM_EMP, PRENOM_EMP, NUM_TEL FROM employe";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        Employe employe = new Employe();
                        employe.setId(resultSet.getInt("ID_EMP"));
                        employe.setNom(resultSet.getString("NOM_EMP"));
                        employe.setPrenom(resultSet.getString("PRENOM_EMP"));
                        employe.setTelephone(resultSet.getString("NUM_TEL"));
                        employes.add(employe);
                        
                        // Affichage dans la console pour vérification
                    System.out.println("Employé récupéré : " + employe.getId() + " - " + employe.getNom());
                    }
                }
            }
        } catch (SQLException e) {
            throw new ServletException("Erreur lors de la récupération des données de la base de données", e);
        }
        return employes;
    }
    
    public class Employe {
    private int id;
    private String nom;
    private String prenom;
    private String telephone;

    // Constructeur par défaut
    public Employe() {
    }

    // Getters et Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InscriptionServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet InscriptionServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
