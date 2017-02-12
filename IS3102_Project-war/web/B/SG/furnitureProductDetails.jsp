<%@page import="HelperClasses.Member"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="HelperClasses.Furniture"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.net.URLEncoder"%>
<%@page import="EntityManager.StoreEntity"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="checkCountry.jsp" />
<%
    String sku = request.getParameter("sku");
    if (sku == null) {
%>
<jsp:forward page="index.jsp" />
<%
    }
    Boolean isMemberLoggedIn = false;
    String memberEmail = (String) (session.getAttribute("memberEmail"));
    if (memberEmail == null) {
        isMemberLoggedIn = false;
    } else {
        isMemberLoggedIn = true;
    }
%>
<html> <!--<![endif]-->
    <jsp:include page="header.html" />

    <body>
        <%
            List<StoreEntity> storesInCountry = (List<StoreEntity>) session.getAttribute("storesInCountry");
            List<Furniture> furnitures = (List<Furniture>) (session.getAttribute("furnitures"));
            /*define your variables here*/
            /*set your variables here*/
          
            int n = Integer.parseInt(request.getParameter("furnitureNumber"));
               String catName = request.getParameter("categoryName");
               String url = furnitures.get(n).getCategory();
        %>
        <div class="body">
            <jsp:include page="menu2.jsp" />
            <div class="body">
                <div role="main" class="main">
                    <section class="page-top">
                        <div class="container">
                            <div class="row">
                                <div class="col-md-12">
                                    <h2>Furnitures</h2>
                                </div>
                            </div>
                        </div>
                    </section>
                    <div class="container">
                        <hr class="tall">
                         <%
                                try {
                                    
                                         if (furnitures != null) {
                                             
                                             
                                                      {
                                                                      
                           %>
                        <div class="row">
                          
                            <div class="col-md-6">
                                <div>
                                    <span class="thumbnail">
                                        <img alt="" class="img-responsive img-rounded" src="../../..<%=furnitures.get(n).getImageUrl()%>">
                                            
                                        </span>
                                </div>
                            </div>
                                            
                            <div class="col-md-6">
                                <div class="summary entry-summary">
                                    <h2 class="shorter"><strong><%=furnitures.get(n).getName()%></strong></h2>
                                    <%
                                        if (isMemberLoggedIn == true) {
                                    %>
                                    <form action="../../ECommerce_AddFurnitureToListServlet">
                                        <input type="hidden" name="id" value="<%=furnitures.get(n).getId()%>"/>
                                        <input type="hidden" name="SKU" value="<%=furnitures.get(n).getSKU()%>"/>
                                        <input type="hidden" name="price" value="<%=furnitures.get(n).getPrice()%>"/>
                                        <input type="hidden" name="name" value="<%=furnitures.get(n).getName()%>"/>
                                        <input type="hidden" name="imageURL" value="<%=furnitures.get(n).getImageUrl()%>"/>
                                        <input type="submit" name="btnEdit" class="btn btn-primary" id="<%=furnitures.get(n).getId()%>" value="Add To Cart"/>
                                    </form>
                                    <%}%>
                                    <p class="price"><h4 class="amount">$<%=furnitures.get(n).getPrice()%>0</h4></p>
                                    <strong>Description</strong>
                                    <p class="taller">
                                       <%=furnitures.get(n).getDescription()%>
                                    </p>
                                    <p>
                                        Height: <%=furnitures.get(n).getHeight()%><br/>
                                        Length: <%=furnitures.get(n).getLength()%><br/>
                                        Width: <%=furnitures.get(n).getWidth()%>
                                    </p>
                                    <div class="product_meta">
                                        
                                        <span class="posted_in">Category: <a rel="tag" href="../../ECommerce_FurnitureCategoryServlet?cat=<%=URLEncoder.encode(url, "UTF-8")%>"><%=furnitures.get(n).getCategory()%></a></span>
                                    </div>
                                    <br/><br/>

                                    <div class="row">
                                        <div class="col-md-4">
                                            <form action="../../ECommerce_StockAvailability">
                                                View Item Availability<br/>
                                                <select style="color: black;" name="storeID">
                                                    <option> </option>
                                                    <%String storeIDstring = (request.getParameter("storeID"));
                                                        Long storeID = 1L;
                                                        if (storeIDstring != null) {
                                                            storeID = Long.parseLong(storeIDstring);
                                                        }
                                                        for (int i = 0; i < storesInCountry.size(); i++) {
                                                            if (!storesInCountry.get(i).getId().equals(storeID)) {%>
                                                    <option value="<%=storesInCountry.get(i).getId()%>"><%=storesInCountry.get(i).getName()%></option>
                                                    <%} else {%>
                                                    <option selected value="<%=storesInCountry.get(i).getId()%>"><%=storesInCountry.get(i).getName()%></option>
                                                    <%
                                                            }
                                                        }
                                                    %>
                                                </select><br/><br/>
                                                <input type="submit" class="btn btn-primary btn-icon" value="Check Item Availability"/>
                                                <input type="hidden" name="furnitureNumber" value="<%=n%>"/>
                                                <input type="hidden" name="sku" value="<%=sku%>"/>
                                                <input type="hidden" name="type" value="Furniture"/>
                                            </form>
                                        </div>
                                        <%
                                            String itemQty = (String) (request.getParameter("itemQty"));
                                            if (itemQty != null) {
                                        %>
                                        <div class="col-md-6">
                                            Status: <%if (Integer.parseInt(itemQty) > 0) {%>Available<%} else {%>Unavailable<%}%>
                                            <br/>
                                            Remaining Qty: <%=itemQty%>
                                            <%}%>
                                        </div>
                                    </div>
                                </div>
                            </div>
                              
                            <hr class="tall">
                        </div>
                              <% }
                                    }
                                    } catch (Exception ex) {
                                        System.out.println(ex);
                                        ex.printStackTrace();
                                    }
                                %>         
                    </div>
                </div>
                <jsp:include page="footer.html" />
            </div>
    </body>
</html>