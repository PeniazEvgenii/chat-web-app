<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Hello</title>
    <%@ include file="header.jsp" %>
</head>
<body>
    <c:if test="${not empty sessionScope.user }">
        <%@ include file="LogOut.jsp" %>
    </c:if>

    <h2>Привет, ${sessionScope.user != null ? sessionScope.user.login : 'Незнакомец'}</h2>

    <p>
        <h4>В приложении ты можешь:</h4>
    </p>

    <c:if test="${empty sessionScope.user }">
        <a href="${pageContext.request.contextPath}/api/user">
            <button type="button"  style="Margin: 0 40px; width: 20%;">Зарегистрироваться</button>
        </a>
        <br><br>
        <a href="${pageContext.request.contextPath}/api/login">
            <button type="button" style="Margin: 0 40px; width: 20%;">Войти</button>
        </a>
    </c:if>

    <c:if test="${not empty sessionScope.user }">
        <a href="${pageContext.request.contextPath}/api/message">
            <button type="button"  style="Margin: 0 40px; width: 20%;">Отправить сообщения</button>
        </a>
        <br><br>
        <a href="${pageContext.request.contextPath}/api/chat">
            <button type="button" style="Margin: 0 40px; width: 20%;">Посмотреть свои сообщения</button>
        </a>
        <br><br>
        <c:if test="${sessionScope.user.role eq 'ADMIN'}">
            <a href="${pageContext.request.contextPath}/api/admin/statistics">
                <button type="button" style="Margin: 0 40px; width: 20%;">Посмотреть статистику приложения</button>
            </a>
        </c:if>
    </c:if>

</body>
</html>