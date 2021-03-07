
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
          .submitBtn {
            background-color: #4CAF50;
            color: white;
            padding: 16px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
            opacity: 0.9;
          }

          .submitBtn:hover {
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
                <a class="active" href="bidderItems" > MY BIDS </a>
                <a href="orders"> MY ORDERS </a>
                <a href="login.jsp"> HOME PAGE </a>
            </div>
        
        
            <div>
                <h2>Item: ${item.getItemName()} </h2>
            </div>
            
        
            <br/>
        
            Bid Price:  <b>${bidPrice}</b>. 
            <form method="post">
                <div>
                    <label for="itemId">Bid price:</label>
                    <input type="hidden" name="itemId" value="${item.getId()}"/>
                     <input type="text" name="price"/>
                    <br/>
                    <button type="submit" class="submitBtn"> Submit Bid </button>   
                </div>
            </form>
        </div>
    </body>
</html>
