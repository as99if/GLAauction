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
                <a href="allOrders"> MY ORDERS </a>
                <a href="login.jsp"> HOME PAGE </a>
            </div>

        <div>
            <h2> BIDS </h2>
            
        </div>
  
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
                  <th>Max Bid</th>
                  <th>My Max Bid</th>
                  <th>Action</th>
                </tr>
              </thead>
              <tbody
            
                <c:forEach items="${items}" var="row">
                    <tr>
                        <td style="font-weight: bold">${row.getItemName()}</td>
                        <td>${row.getDescription()}</td>
                        <td style="font-weight: bold">${row.getInitialPrice()}</td>
                        <td>${row.getDuration()} days</td>
                        <td>${row.getBidStartDate()}</td>
                        <td>${row.getBidEndDate()}</td>
                        <td>${row.getCategoryNames() }</td>
                        <td><%@include file="status.jsp" %></td>
                        <td style="font-weight: bold">${row.getMaxBid()}</td>
                        <td style="font-weight: bold">${row.getMyMaxBid()}</td>
                        <td>
                        <a href="cancelAllBids?firstname=${param.firstname}&itemId=${row.getId()}">Quit Auction</a>
                        <c:if test="${row.getStatus() == 'ON_SALE' && row.getWinner().getId()==bidder.getId()}">
                            <a href="createOrder?itemId=${row.getId()}"> Order the item </a>
                        </c:if>
                    </td>
                    </tr>
                </c:forEach>
              </tbody>
              </table>
        </div
    </body>
</html>..
