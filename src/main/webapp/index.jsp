<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<jsp:include page="WEB-INF/jsp/fragments/headTag.jsp"/>
<body>
<jsp:include page="WEB-INF/jsp/fragments/header.jsp"/>
<section>
    <h2>Проект "<a href="https://github.com/pinky83/alfaplus-web" target="_blank"><fmt:message key="app.title"/>"</a></h2>
    <hr>
    <ul>
        <li><a href="images/last"><fmt:message key="images.title"/> </a></li>
    </ul>

    <ul>
        <li><a href="rest/patients/getBetween?date1=1927-01-01&&date2=1945-01-01"><fmt:message key="patients.title"/> </a></li>
    </ul>
</section>
<jsp:include page="WEB-INF/jsp/fragments/footer.jsp"/>
</body>
</html>