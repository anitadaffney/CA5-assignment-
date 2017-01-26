/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package B_servlets;

import HelperClasses.Member;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ONG
 */
@WebServlet(name = "ECommerce_MemberEditProfileServlet", urlPatterns = {"/ECommerce_MemberEditProfileServlet"})
public class ECommerce_MemberEditProfileServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        try {
            
            String name = request.getParameter("name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            String securityAnswer = request.getParameter("securityAnswer");
            int securityQuestion = Integer.parseInt(request.getParameter("securityQuestion"));
            int age = Integer.parseInt(request.getParameter("age"));
            int income = Integer.parseInt(request.getParameter("income"));
            String password = request.getParameter("password");
            String rePassword = request.getParameter("repassword");
            
            
            //if(password.equals(rePassword)){
                Client client = ClientBuilder.newClient();
            WebTarget target = client
                    .target("http://localhost:8080/IS3102_WebService-Student/webresources/entity.memberentity").path("updateMember")
                    .queryParam("name", name)
                    .queryParam("email", email)
                    .queryParam("phone", phone)
                    .queryParam("address", address)
                    .queryParam("securityAnswer", securityAnswer)
                    .queryParam("securityQuestion", securityQuestion)
                    .queryParam("age", age)
                    .queryParam("income", income);
                   // .queryParam("password", password);

            Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
            Response r = invocationBuilder.post(null);
            
            HttpSession s = request.getSession();
            s.setAttribute("memberEmail", email);

            //out.print(member);
            //session.setAttribute("member", member);
            response.sendRedirect("ECommerce_GetMember?msg=UpdateSuccess");
           // }
//            else{
//                 response.sendRedirect("ECommerce_GetMember?msg=error");
//            }
            

        } catch (Exception ex) {
            out.println(ex);
            ex.printStackTrace();
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
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




