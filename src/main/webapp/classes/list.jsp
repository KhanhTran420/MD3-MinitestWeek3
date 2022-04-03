<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 4/3/2022
  Time: 10:54 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Class Management Application</title>
</head>
<body>
<center>
    <h1>Class Management</h1>
    <h2>
        <a href="/classes/create.jsp">Add New Class</a>
    </h2>
    <h2>
        <a href="/student">Student List</a>
    </h2>
</center>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Class</h2></caption>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
        </tr>
        <c:forEach var="classes" items="${listClass}">
            <tr>
                <td><c:out value="${classes.id}"/></td>
                <td><c:out value="${classes.name}"/></td>
                <td><c:out value="${classes.description}"/></td>
                <td>
                    <a href="/student?action=editClass&id=${classes.id}">Edit</a>
                    <a href="/student?action=deleteClass&id=${classes.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>