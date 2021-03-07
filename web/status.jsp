
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${row.getStatus()=='ON_SALE'}"> ON_SALE </c:if>
<c:if test="${row.getStatus()=='SALE_CLOSED'}"> SALE_CLOSED </c:if>
<c:if test="${row.getStatus()=='ON_DELIVERY'}"> ON_DELIVERY </c:if>