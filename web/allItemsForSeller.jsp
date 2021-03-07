<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Auction</title>
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
            <a href="sellerItems" > MY SELLING ITEMS </a>
            <a href="createAuction"> CREATE AUCTION </a>
            <a href="login.jsp"> HOME PAGE </a>
        </div>
      
           
        <h2>My Selling Items</h2>
 
        
       
            <table>
              <thead>
                <tr>
                  <th>Item Name</th>
                  <th>Description</th>
                  <th>Initial price</th>
                  <th>Duration</th>
                  <th>Bid Start Date</th>
                  <th>Bid End Date</th>
                  <th>Categories</th>
                  <th>Status</th>
                  <th>Action</th>
                </tr>
              </thead>
              <tbody>
            
                <c:forEach items="${items}" var="row">
                    <tr>
                    <td style="font-weight: bold">${row.getItemName()}</td>
                    <td>${row.getDescription()}</td>
                    <td style="font-weight: bold">${row.getInitialPrice()}</td>
                    <td>${row.getDuration()} days</td>
                    <td>${row.getBidStartDate()}</td>
                    <td>${row.getBidEndDate()}</td>
                    <td>${row.getCategoryNames() }</td>
                    <td style="font-weight: bold"><%@include file="status.jsp" %></td>
                    
                    <td><a href="cancelItems?username=${param.username}&itemId=${row.getId()}">Cancel</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
       
    </body>
</html>
