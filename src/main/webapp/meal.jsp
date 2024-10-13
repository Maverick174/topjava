<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html lang="ru">
<head>
    <title>Meals</title>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<hr>
<h2>Edit meal</h2>
<form action=meals?action=${action} method="post">
    <c:if test="${mealId != null}">
        <input type="hidden" name="mealId" value="<c:out value="${mealId}"/>"/>
    </c:if>

    DateTime: <input type="datetime-local" name="date" value="<c:out value="${meal.dateTimeStr}"/>"/> <br/>
    Description: <input type="text" name="description" value="<c:out value="${meal.description}" />"/> <br/>
    Calories: <input type="text" name="calories" value="<c:out value="${meal.calories}" />"/> <br/>
    <button type="submit">Save</button>
    <button onclick="window.history.back()" type="button">Cancel</button>
</form>
</body>
</html>