<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<fmt:setBundle basename="app" scope="request"/>--%>
<html>
<jsp:include page="/WEB-INF/jsp/fragments/headTag.jsp"/>
<link rel="stylesheet" href="webjars/datatables/1.10.12/css/jquery.dataTables.min.css">

<body>
<link rel="stylesheet" href="resources/css/style.css">
<div class="jumbotron">
    <div class="container-fluid" id="main">
        <div class="col-sm-14">
            <jsp:include page="/WEB-INF/jsp/fragments/header.jsp"/>
        </div>
        <div class="shadow">
            <h3><fmt:message key="images.title"/></h3>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <div class="view-box">
                        <table class="table table-striped display small" id="datatable">
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
                <div class="col-lg-2-offset-2 col-md-8-offset-2 col-sm-3-offset-1">
                    <h4 align="center">Поиск и навигация</h4>
                </div>
                <div class="clearfix"></div>

            </div>
        </div>

        <div class="clearfix"></div>

        <div class="col-lg-8 col-md-8 col-sm-7">
            <button class="btn btn-info" data-toggle="collapse" data-target="#spoiler-1">Посмотреть описание</button>
            <div class="collapse in" id="spoiler-1">
                    <p>test test test!</p>
            </div>
        </div>

        <div class="panel pagination s" align="center" style="width: 100%" id="nav-buttons" >
            <a class="btn btn-md btn-info" href="images/previous/${firstId}" id="${image.id}"><fmt:message key="images.previous"/></a>
            <a class="btn btn-md btn-info" href="images/next/${lastId}" style="align-content: center" id="${image.id}"><fmt:message key="images.next"/></a>
        </div>

        <div class="col-sm-15">
            <jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
        </div>
    </div>
</div>

</body>
</html>
