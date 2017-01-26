<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Patient list</title>
</head>
<body>
<h2><a href="../../index.jsp">Home</a></h2>

<h1 align="left">Add Patient</h1>
<form id="editor" action="patients" method="post">
    Birth date: <input name="birth_date" type="date"/>
    Name: <input name="name" type="text"/>
    Comment:  <input name="comment" type="text"/>
    <br>
     <input name="Сохранить" type="submit" value="Сохранить!" />
</form>

<br>
<h2 align="center">Patient list</h2>
<table align="center">
    <c:forEach items="${requestScope.patientList}" var="item">
      <%--<c:set var="cleanedDateTime" value="${fn:replace(item.getDateTime(), 'T', ' ')}" />--%>
        <tr style="color: brown">
            <td style="border: double">${item.getId()}</td>
            <td style="border: double">${item.getBirthDate()}</td>
            <td style="border: double">${item.getSex()==1? 'Ж' : 'М'}</td>
            <td style="border: double">${item.getName()}</td>
            <td style="border: double">${item.getComments()}</td>
            <td style="border: double"><a rel="editor">Edit</a></td>
            <td style="border: double"><a href="patients?action=delete&id=${item.getId()}">Delete</a></td>
        </tr>
    </c:forEach>
    <tr>
        <td>
            <a href="users">Add patient</a>
        </td>
    </tr>
</table>

</body>
</html>
