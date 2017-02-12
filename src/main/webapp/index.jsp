<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<jsp:include page="WEB-INF/jsp/fragments/headTag.jsp"/>

<link rel="stylesheet" href="webjars/datatables/1.10.12/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="resources/css/style.css">
<body>
    <div class="jumbotron">
            <div class="container-fluid" id="main">
                <div class="col-sm-14">
                    <jsp:include page="/WEB-INF/jsp/fragments/header.jsp"/>
                </div>
                    <%--<div class="shadow">--%>
                        <ul class="nav nav-tabs">
                            <li class="active"><a data-toggle="tab" href="#Home"><fmt:message key="app.home"/></a></li>
                            <li><a data-toggle="tab" href="#Images"><fmt:message key="images.title"/> </a></li>
                            <li><a data-toggle="tab" href="#Patients"><fmt:message key="patients.title"/> </a></li>
                        </ul>

                        <div class="tab-content">
                            <div id="Home" class="tab-pane fade in active">
                                <br>
                                <div class="panel panel-info">
                                    <div class="panel-heading">О проекте</div>
                                    <h2><a href="https://github.com/pinky83/alfaplus-web"
                                                                             target="_blank"><fmt:message key="app.title"/></a></h2>
                                    <p>
                                        Имплементация функциональности программного комплекса "Альфаплюс"
                                        для цифровых флюорографов .
                                    </p>
                                </div>
                            </div>

                            <div id="Images" class="tab-pane fade">
                                <h3><fmt:message key="images.title"/></h3>
                                    <%--<h3><fmt:message key="images.title"/></h3>--%>

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
                                            <div class="mini-image row">
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

                                <div class="clearfix"></div>

                                <div class="panel pagination s" align="center" style="width: 100%" id="nav-buttons" >
                                    <a class="btn btn-md btn-info"  id="prev"><fmt:message key="images.previous"/></a>
                                    <a class="btn btn-md btn-info"  id="next"><fmt:message key="images.next"/></a>
                                </div>
                            </div>

                            <div id="Patients" class="tab-pane fade">
                                <br>
                                <div class="panel panel-info">
                                    <div class="panel-heading">
                                        <br>
                                    </div>
                                    <div class="panel-body">
                                            <div class="col-lg-8 col-md-8 col-sm-8 panel panel-danger pull-left">
                                                <div class="panel-heading">
                                                    <fmt:message key="patients.title2"/>
                                                </div>

                                                <div class="view-box">
                                                    <table class="table table-striped display small " id="datatable2">
                                                        <thead>
                                                        <tr>
                                                            <th><fmt:message key="patients.id"/></th>
                                                            <th><fmt:message key="patients.name"/></th>
                                                            <th><fmt:message key="patients.birthDate"/></th>
                                                            <th><fmt:message key="patients.comments"/></th>
                                                            <%--<th style="display: none"><fmt:message key="patients.images"/></th>--%>
                                                        </tr>
                                                        </thead>
                                                    </table>
                                                </div>
                                            </div>
                                            <div class="row col-lg-4 col-md-4 col-sm-4 pull-right">
                                                <div class="col-lg-12 col-md-12 col-sm-12 panel panel-default  pull-right">
                                                    <div class="panel-heading">
                                                        <fmt:message key="patients.search"/>
                                                    </div>

                                                    <div class="panel-body">
                                                        <div class="input-group">
                                                             <span class="input-group-addon">
                                                                <input id="nameCheck" type="checkbox">
                                                             </span>

                                                            <input id="searchPatientName" type="text" class="form-control" placeholder="Ф.И.О.">
                                                        </div>

                                                        <div class="input-group">
                                                             <span class="input-group-addon">
                                                                <input id="ageCheck" type="checkbox">
                                                             </span>

                                                            <input id="searchPatientAge" type="number" class="form-control" placeholder="Год рождения">
                                                        </div>

                                                        <br>
                                                        <button type="button" onclick="searchByName()" class="btn btn-default btn-sm glyphicon glyphicon-search pull-right"></button>
                                                    </div>
                                                </div>

                                                <div class="col-lg-12 col-md-12 col-sm-12 panel panel-default">
                                                    <div class="panel-heading">
                                                        <fmt:message key="patients.images"/>
                                                    </div>

                                                    <div class="panel-body">
                                                        <table class="table table-striped display small " id="datatable3">
                                                            <thead id="images_table_head" style="display: none">
                                                            <tr>
                                                                <th><fmt:message key="images.id"/></th>
                                                                <th><fmt:message key="images.date"/></th>
                                                                <th><fmt:message key="images.description"/></th>
                                                                <%--<th><fmt:message key="images.alt"/></th>--%>
                                                                <%--<th style="display: none"><fmt:message key="patients.images"/></th>--%>
                                                            </tr>
                                                            </thead>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                <div class="col-sm-15">
                    <jsp:include page="/WEB-INF/jsp/fragments/footer.jsp"/>
                </div>
            </div>
    <%--</div>--%>
</body>
<script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.12/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/imageDatatables.js"></script>
<script type="text/javascript" src="resources/js/patientDatatables.js"></script>
</html>