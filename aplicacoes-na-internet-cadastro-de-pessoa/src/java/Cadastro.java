/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Cadastro extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String codigo = request.getParameter("codigo");
            String nome = request.getParameter("nome");
            String idade = request.getParameter("idade");
            Class.forName("org.firebirdsql.jdbc.FBDriver");
            Connection conn = DriverManager.getConnection("jdbc:firebirdsql:127.0.0.1:c:/aula.fdb", "sysdba", "masterkey");
            out.println("Conectado <br>");
            out.println("------------ <br>");
            PreparedStatement prep = conn.prepareStatement("insert into pessoa values(" + codigo + ", '" + nome + "', " + idade + ");");
            prep.executeUpdate();
            prep = conn.prepareStatement("select * from pessoa;");
            ResultSet rs = prep.executeQuery();            
            while (rs.next()) {
                out.println("O resultado da query foi: <br>");
                out.println("Codigo: " + rs.getString(1) + "<br>");
                out.println("Nome: " + rs.getString(2) + "<br>");
                out.println("Idade: " + rs.getString(3) + "<br>");
                out.println("------------ <br>");
            }    
            conn.close();
        } catch (Exception e) {
            out.println("IH!!! Deu M" + e.getMessage());
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

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
