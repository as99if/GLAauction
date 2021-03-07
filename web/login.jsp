
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        
        <style>
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

              hr {
                border: 1px solid #f1f1f1;
                margin-bottom: 25px;
              }

              /* Set a style for all buttons */
              button {
                background-color: #4CAF50;
                color: white;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                cursor: pointer;
                width: 100%;
                opacity: 0.9;
              }

              button:hover {
                opacity:1;
              }

              /* Extra styles for the cancel button */
              .cancelbtn {
                padding: 14px 20px;
                background-color: #f44336;
              }

              /* Float cancel and signup buttons and add an equal width */
              .loginBtn {
                float: left;
                width: 50%;
              }

              /* Add padding to container elements */
              .container {
                padding: 16px;
              }

              /* Clear floats */
              .clearfix::after {
                content: "";
                clear: both;
                display: table;
              }
        </style>
        
    </head>
    <body>
        <!--Login:
        <form method="post" action="login">
            Username: <input type="text" id="username"  name="username"/>
            Password: <input type="password" id="password"  name="password"/>
            <input type="submit" value="login"/>
        </form-->

        <form method="post" action="loginSeller">
        <div class="container ">

            <br/>
            
                <h3><strong>Login Seller</strong> </h3>

                <label for="username">Name: </label>
                <input type="text" id="username"  name="username" placeholder="name..."/>
                <label for="password">Password: </label>
                <input type="password" id="password"  name="password" placeholder="password.."/>
                
                <div class="clearfix">
                    <button type="submit" class="loginBtn" value="loginSeller">Sign in </button>
                </div>
                
        </div>
         </form>
        
        <br/><br/>
        <form method="post" action="loginBidder">
            
            <div class="container ">

            <br/>
            
                <h3><strong>Login Bidder</strong> </h3>

                <label for="username">Name: </label>
                <input type="text" id="username"  name="username" placeholder="name..."/>
                <label for="password">Password: </label>
                <input type="password" id="password"  name="password" placeholder="password.."/>

                
                <div class="clearfix">
                    <button type="submit" class="loginBtn" value="loginBidder">Sign in </button>
                </div>
                
        </div>
               
      
        </form>
    </body>
</html>
