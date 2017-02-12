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

            <div class="input-group col-lg-4 col-md-4 col-sm-4 col-xs-4 pull-right">
                <input id="search" type="number" class="form-control" placeholder="Поиск&hellip;">
                <div class="input-group-btn">
                <button type="button" class="btn btn-block dropdown-toggle" data-toggle="dropdown">Искать
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
                            <li id="byNumber"><a href="#" onclick="searchById();return false;">По номеру</a></li>
                            <li id="byDate"><a href="#" onclick="searchByDate();return false;">По дате</a></li>
                </ul>
                </div>
            </div>
            <div class="row">
                <div class="col-lg-8 col-md-8 col-sm-7">
                    <div class="view-box">
                        <table class="table table-striped display small " id="datatable">
                            <thead>
                            <tr>
                                <th><fmt:message key="images.id"/></th>
                                <th><fmt:message key="images.date"/></th>
                                <th><fmt:message key="images.name"/></th>
                                <th><fmt:message key="images.sex"/></th>
                                <th><fmt:message key="images.birthday"/></th>
                                <th style="display: none"><fmt:message key="images.description"/></th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                 </div>


                <div class="col-lg-2-offset-2 col-md-4-offset-2 col-sm-3-offset-1" align="center">
                    <br>
                    <div class="mini-image> row">
                        <br>
                        <h4 align="center">Предварительный просмотр</h4>

                        <img id="image_thumb" src="thumb/00048125" class="img-thumbnail" alt=<fmt:message key="images.alt"/>>
                        <br>

                        <div class="form-horizontal">
                            <div class="form-group">
                                <div class="image-description col-lg-4 col-md-4 col-sm-4 col xs-4">
                                    <textarea id="image_description" class="form-control" readonly="readonly" rows="5" placeholder="Здесь будет описание снимка"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="clearfix"></div>

            </div>
        </div>

        <div class="clearfix"></div>

        <div class="panel pagination s" align="center" style="width: 100%" id="nav-buttons" >
            <a class="btn btn-md btn-info"  id="prev"><fmt:message key="images.previous"/></a>
            <a class="btn btn-md btn-info"  id="next"><fmt:message key="images.next"/></a>
        </div>

        <div class="col-sm-15">
            <jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
        </div>
    </div>
</div>

</body>
<script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.12/js/dataTables.bootstrap.min.js"></script>
<%--<script type="text/javascript" src="webjars/noty/2.3.8/js/noty/packaged/jquery.noty.packaged.min.js"></script>--%>
<%--<script type="text/javascript" src="resources/js/datatablesUtil.js"></script>--%>
<script type="text/javascript" src="resources/js/imageDatatables.js"></script>
</html>
