/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package B_servlets;

import HelperClasses.Furniture;
import HelperClasses.ShoppingCartLineItem;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author User
 */
public class ECommerce_AddFurnitureToListServlet extends HttpServlet {
    
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
           try {
             HttpSession session = request.getSession();
             Long countryID=(Long) session.getAttribute("countryID");
             
             ShoppingCartLineItem cart=new ShoppingCartLineItem();
             cart.setId(request.getParameter("id"));
             cart.setSKU(request.getParameter("SKU"));
             cart.setName(request.getParameter("name"));
             cart.setImageURL(request.getParameter("imageURL"));
             cart.setPrice(Double.parseDouble(request.getParameter("price")));
             cart.setQuantity(1);
             
             cart.setCountryID(countryID);
             
             ArrayList<ShoppingCartLineItem> Shoppingcart=new ArrayList<ShoppingCartLineItem>();
             Shoppingcart.add(cart);
             
             session.setAttribute("shoppingCart",Shoppingcart);
                response.sendRedirect("/IS3102_Project-war/B/SG/shoppingCart.jsp?goodMsg=Item Successfully added into the cart!");

           }
           catch (Exception ex) {
            out.println("\n\n " + ex.getMessage());
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