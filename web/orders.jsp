
<%@page import="javax.ejb.EJB"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
            <a class="active" href="bidderItems" > MY BIDS </a>
            <a href="orders"> MY ORDERS </a>
            <a href="login.jsp"> HOME PAGE </a>
        </div>
        
        
        <h2>My Orders </h2>
       
        
        <div class="container">          
            <table>
              <thead>
                <tr >
                  <th>Item Name</th>
                  <th>Description</th>
                  <th>Categories</th>
                  <th>Price</th>
                  <th>Action</th>
                </tr>
              </thead>
              <tbody
                
                <tr>
                <td style="font-weight: bold">${item.getItemName()}</td>
                <td>${item.getDescription()}</td>
                <td>${item.getCategories()}</td>
                <td style="font-weight: bold">${item.getMyMaxBid()}</td>

                <td>
                    <a href="cancelOrderedItems?itemId=${item.getId()}">Cancel item</a>
                </td>
                </tr>

              </tbody>
              </table>
        </div>

        <br/>
        
        <div style="min-height:0px;">
            <a type="button" href="orders">Order item</a>
        </div>
       
    </body>
</html>
