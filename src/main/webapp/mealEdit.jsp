<%--
  Created by IntelliJ IDEA.
  User: buhalo
  Date: 05.06.16
  Time: 21:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meal Edit</title>
</head>
<body>
<form method="post">
    <input type="hidden" name="id" value="${meal.id}">
    <input type="text" name="Date" value="${meal.date}">
    <input type="text" name="description" value="${meal.description}">
    <input type="text" name="calories" value="${meal.calories}">
    <input type="submit">
</form>
</body>
</html>
