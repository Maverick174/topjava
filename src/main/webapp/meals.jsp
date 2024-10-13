<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Meals</h2>
<h3><a href="meals?action=add">Add Meal</a></h3>
<table border cellspacing="0" cellpadding="3" width="50%">
    <tr>
        <th><h3></h3></th>
        <th><h3></h3></th>
        <th><h3>Date</h3></th>
        <th><h3>Description</h3></th>
        <th><h3>Calories</h3></th>
    </tr>
    <c:forEach items="${meals}" var="m">
        <c:choose>
            <c:when test="${m.excess}">
                <tr bgcolor="red">
                    <td><a href="meals?action=update&mealId=<c:out value="${m.id}"/>">Update</a></td>
                    <td><a href="meals?action=delete&mealId=<c:out value="${m.id}"/>">Delete</a></td>
                    <td>${m.dateTimeStr}</td>
                    <td>${m.description}</td>
                    <td>${m.calories}</td
                </tr>
            </c:when>
            <c:when test="${!m.excess}">
                <tr bgcolor="green">
                    <td><a href="meals?action=update&mealId=<c:out value="${m.id}"/>">Update</a></td>
                    <td><a href="meals?action=delete&mealId=<c:out value="${m.id}"/>">Delete</a></td>
                    <td>${m.dateTimeStr}</td>
                    <td>${m.description}</td>
                    <td>${m.calories}</td
                </tr>
            </c:when>
            <c:otherwise>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</table>
</body>
</html>