/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package B_servlets;

import HelperClasses.ShoppingCartLineItem;
import java.io.IOException;
import static java.lang.System.out;
import java.util.ArrayList;
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
 * @author Lim
 */
@WebServlet(name = "ECommerce_AddFurnitureToListServlet", urlPatterns = {"/ECommerce_AddFurnitureToListServlet"})
public class ECommerce_AddFurnitureToListServlet extends HttpServlet {

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
            Long countryID = (Long) session.getAttribute("countryID");
            ArrayList<ShoppingCartLineItem> current = (ArrayList<ShoppingCartLineItem>) (session.getAttribute("shoppingCart"));
            ArrayList<ShoppingCartLineItem> Shoppingcart = new ArrayList<ShoppingCartLineItem>();
            
            ShoppingCartLineItem cart = new ShoppingCartLineItem();
            
            cart.setId(request.getParameter("id"));
            cart.setSKU(request.getParameter("SKU"));
            cart.setName(request.getParameter("name"));
            cart.setImageURL(request.getParameter("imageURL"));
            cart.setPrice(Double.parseDouble(request.getParameter("price")));
            cart.setCountryID(countryID);
            cart.setQuantity(+1);
          
            Shoppingcart.add(cart);    
            
            if (current != null) {

                for (ShoppingCartLineItem items : current) {
                    
                    if(items.getSKU().equals(cart.getSKU()))
                    {
                        Shoppingcart.remove(cart);
                        items.setQuantity(items.getQuantity()+1);
                        Shoppingcart.add(items);
                    }                 
                    else
                    {
                        Shoppingcart.add(items);
                    }
                }
                
            }
            session.setAttribute("shoppingCart", Shoppingcart);
            response.sendRedirect("/IS3102_Project-war/B/SG/shoppingCart.jsp?goodMsg=Item Successfully added into the cart!");
        } catch (Exception ex) {
            out.println("\n\n " + ex.getMessage());
        }
    }

    /*public int getQuantityOfSKU(Long CountryID, String SKU) {
        try {
            Client client = ClientBuilder.newClient();
            WebTarget target = client
                    .target("http://localhost:8080/WebServices/webresources/entity.countryentity/")
                    .path("getQuantity")
                    .queryParam("CountryID", CountryID)
                    .queryParam("SKU", SKU);
            Invocation.Builder invocationBuilder = target.request(MediaType.APPLICATION_JSON);
            Response response = invocationBuilder.get();
            System.out.println("status: " + response.getStatus());

            String result = (String) response.readEntity(String.class);
            System.out.println("Result returned from ws: " + result);
            return Integer.parseInt(result);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }*/

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