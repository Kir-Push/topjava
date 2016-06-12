<%--
  Created by IntelliJ IDEA.
  User: buhalo
  Date: 05.06.16
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Meal list</title>
</head>
<body>
<h2>Meal list</h2>

<table  border>
    <tr>
        <td>ИД</td>
        <td>Время</td>
        <td>Еда</td>
        <td>Калории</td>
    </tr>
<c:forEach var="meal" items="${meals}">
        <tr style="${meal.exceed == 'true' ? 'color: red' : 'color: LawnGreen'}">
            <td> ${meal.id}</td>
            <td> ${meal.date}</td>
            <td>${meal.description}</td>
            <td>${meal.calories}</td>
            <td><a href="meals?action=edit&id=${meal.id}" style="${meal.exceed == 'true' ? 'color: red' : 'color: LawnGreen'}">Edit</a></td>
            <td><a href="meals?action=delete&id=${meal.id}"style="${meal.exceed == 'true' ? 'color: red' : 'color: LawnGreen'}">Delete</a></td>
        </tr>
</c:forEach>
    </table>

</body>
</html>
