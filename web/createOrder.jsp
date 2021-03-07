    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Auction </title>
    </head>
    
    <style>
           .container {
                padding: 16px;
                background-color: white;
              }

              /* Full-width input fields */
            input[type=text], input[type=password] {
              width: 100%;
              padding: 15px;
              margin: 5px 0 22px 0;
              display: inline-block;
              border: none;
              background: #f1f1f1;
            }

            input[type=text]:focus, input[type=password]:focus {
              background-color: #ddd;
              outline: none;
            }

            /* Overwrite default styles of hr */
            hr {
              border: 1px solid #f1f1f1;
              margin-bottom: 25px;
            }

            /* Set a style for the submit button */
            .createBtn {
              background-color: #4CAF50;
              color: white;
              padding: 16px 20px;
              margin: 8px 0;
              border: none;
              cursor: pointer;
              width: 100%;
              opacity: 0.9;
            }

            .createBtn:hover {
              opacity: 1;
            }

            /* Add a blue text color to links */
            a {
              color: dodgerblue;
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
        
        <h2 >Create Order</h2>
        
        <form method="post" action="createOrder">
            <div class="container">
                
                <p>Item Name: ${item.getItemName()}</p>

                <p>Description: ${item.getDescription()}</p>

                <p>Your phone number: ${bidder.getPhoneNumber()}</p>

                <label for="delivery">Your delivery address: </label>
                <input type="text" name="delivery" placeholder="${bidder.getAddress()}"/>

                <br/>
                <button type="submit" class="createBtn" value="createOrder"> Order Item </button>   
        </form>
        </div>
    </body>
</html>
