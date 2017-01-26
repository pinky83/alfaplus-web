<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<fmt:setBundle basename="app" scope="request"/>--%>
<html>
<jsp:include page="/WEB-INF/jsp/fragments/headTag.jsp"/>
<link rel="stylesheet" href="webjars/datatables/1.10.12/css/jquery.dataTables.min.css">

<body>
<jsp:include page="/WEB-INF/jsp/fragments/header.jsp"/>

<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h3><fmt:message key="images.title"/></h3>

            <div class="view-box">
                <table class="table table-striped display" id="datatable">
                    <thead>
                    <tr>
                        <th><fmt:message key="images.id"/></th>
                        <th><fmt:message key="images.date"/></th>
                        <th><fmt:message key="images.name"/></th>
                        <th><fmt:message key="images.sex"/></th>
                        <th><fmt:message key="images.birthday"/></th>
                    </tr>
                    </thead>
                    <jsp:useBean id="image"  scope="page" class="org.pinky83.alfaplus.model.Image"/>
                    <c:set var="counter" scope="request" value="0"/>
                    <c:forEach items="${imageList}" var="image">
                        <c:set var="counter" scope="request" value="${counter+1}"/>
                        <tr>
                            <td>${image.id}</td>
                            <td>${image.imageDate}</td>
                            <td>${image.patient.name}</td>
                            <td>${image.patient.sex}</td>
                            <td>${image.patient.birthDate}</td>
                        </tr>
                        <c:set var="lastId" scope="request" value="${image.id}"/>
                        <c:set var="firstId" scope="request" value="${lastId-counter+1}"/>
                    </c:forEach>

                </table>
            </div>
        </div>
    </div>
</div>

<div class="panel panel-default" >
    <div class="panel-heading">
        <h3 class="panel-title"><fmt:message key="images.description"/></h3>
    </div>
    <div class="panel-body">
        <div class="text-info">
            ${image.description}
        </div>
    </div>
</div>

<a class="btn btn-lg btn-info" href="images/previous/${firstId}" id="${image.id}"><fmt:message key="images.previous"/></a>
<a class="btn btn-lg btn-info" href="images/next/${lastId}" style="align-content: center" id="${image.id}"><fmt:message key="images.next"/></a>

<jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
</body>
</html>
