<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bids</title>
    </head>
        
    <style>
        table {
            border-collapse: collapse;
            border-spacing: 0;
            width: 100%;
            border: 1px solid #ddd;
          }

          th, td {
            text-align: left;
            padding: 16px;
          }

          tr:nth-child(even) {
            background-color: #f2f2f2;
          }
          
          .topnav {
                overflow: hidden;
                background-color: #333;
           }

            .topnav a {
              float: left;
              color: #f2f2f2;
              text-align: center;
              padding: 14px 16px;
              text-decoration: none;
              font-size: 17px;
            }

            .topnav a:hover {
              background-color: #ddd;
              color: black;
            }

            .topnav a.active {
              background-color: #4CAF50;
              color: white;
            }
        
    </style>
    
    <body>
        
         <div class="topnav">

                <a class="active" href="bidderItems" > ALL ITEMS </a>
                <a href="myBidItems"> MY BIDS </a>
                <a href="orders"> MY ORDERS </a>
                <a href="login.jsp"> HOME PAGE </a>
            </div>
        
        <div>
            <h2> BIDS </h2>
            
            <form method="post" action="bidderItems">
            <div class="category">
                <p>Search items by category, write category of items: </p>
                
                <select name="selected_cat">
                    <c:forEach var="cat" items="${cats}">
                        
                        <option value="${cat.getId()}">${cat.getName()}</option>
                        
                    </c:forEach>
                </select>
            
                <button type="submit" class="createBtn" value="bidderItems"> Find Items </button>   
                </div>
            </form>
            
        </div>
  
            <table>
              <thead>
                <tr>
                  <th>Item Name</th>
                  <th>Description</th>
                  <th>Categories</th>
                  <th>Status</th>
                </tr>
              </thead>
              <tbody
            
                <c:forEach items="${items}" var="row">
                    <tr>
                        <td style="font-weight: bold">${row.getItemName()}</td>
                        <td>${row.getDescription()}</td>
                        <td>${row.getCategoryNames() }</td>
                        <td><%@include file="status.jsp" %></td>
                        
                    </tr>
                </c:forEach>
              </tbody>
              </table>
        </div
    </body>
</html>
