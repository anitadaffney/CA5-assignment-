/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package B_servlets;

import HelperClasses.ShoppingCartLineItem;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Enidtingz
 */
@WebServlet(name = "ECommerce_MinusFurnitureToListServlet", urlPatterns = {"/ECommerce_MinusFurnitureToListServlet"})
public class ECommerce_MinusFurnitureToListServlet extends HttpServlet {

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
        
       try {
            HttpSession session = request.getSession();
            ArrayList<ShoppingCartLineItem> current = (ArrayList<ShoppingCartLineItem>) (session.getAttribute("shoppingCart"));
            ArrayList<ShoppingCartLineItem> Shoppingcart = new ArrayList<ShoppingCartLineItem>();
            
            String sku= request.getParameter("SKU");
            
            for (ShoppingCartLineItem items : current)
            {
             if (!items.getSKU().equals(sku)){
                 Shoppingcart.add(items);
             } else if(items.getQuantity() == 1){
                 response.sendRedirect("/IS3102_Project-war/B/SG/shoppingCart.jsp?errMsg=Quantity cannot be less than 1");
             }  else{
                 items.setQuantity(items.getQuantity()-1);
                 Shoppingcart.add(items);
                 session.setAttribute("shoppingCart", Shoppingcart);
                response.sendRedirect("/IS3102_Project-war/B/SG/shoppingCart.jsp?errMsg=Item successfully removed from the cart!");
             }
            }
        } catch (Exception ex) {
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