<%-- 
    Document   : index
    Created on : 16 Feb 2021, 00:22:40
    Author     : asifshuvo
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Items</title>
    </head>
    <body>
        <h1>Item Management</h1>
        <form action="./ItemServlet" method="POST">
            <table>
                <tr>
                    <td>Item ID</td>
                    <td><input type="text" name="id" value="${item.id}" /></td>
                </tr>
                <tr>
                    <td>Item Name</td>
                    <td><input type="text" name="name" value="${item.name}" /></td>
                </tr>
                <tr>
                    <td>Description</td>
                    <td><input type="text" name="description" value="${item.description}" /></td>
                </tr>
                <tr>
                    <td>Starting Price</td>
                    <td><input type="text" name="startingPrice" value="${item.startingPrice}" /></td>
                </tr>
                <tr>
                    <td>Bidding End Date</td>
                    <td><input type="text" name="endDate" value="${item.endDate}" /></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" name="action" value="Add" />
                        <input type="submit" name="action" value="Edit" />
                        <input type="submit" name="action" value="Delete" />
                        <input type="submit" name="action" value="Search" />
                    </td>                
                </tr>            
            </table>
        </form>        
        <br>
        <table border="1">
            <th>ID</th>
            <th>Item Name</th>
            <th>Description</th>
            <th>Bidding Ends</th>
            <c:forEach items="${allItems}" var="i">
                <tr>
                    <td>${i.id}</td>
                    <td>${i.name}</td>
                    <td>${i.description}</td>
                    <td>${i.startingPrice}</td>
                    <td>${i.endDate}</td>
                </tr>
            </c:forEach>
        </table>  
    </body>
</html>
