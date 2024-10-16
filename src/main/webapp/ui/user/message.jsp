<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>SendMessage</title>
    <%@ include file="../header.jsp" %>
</head>
<body>
    <%@ include file="../LogOut.jsp" %>
    <a href="${pageContext.request.contextPath}/">
            <button type="button" style="Margin: 0 0px; width: 10%;">Стартовая страница</button>
        </a>

    <h2>Отправка сообщения пользователю:</h2>

    <form action="${pageContext.request.contextPath}/api/message" method="post">
        <p><label for="loginID"> Кому:</label>
            <input type="text" name="username" id="loginID" placeholder="логин получателя"  style="margin-left: 55px">
        </p>
        <p><label for="messageId"> Сообщение:
            <textarea name="message" id="messageId" rows="7" cols="70" style="margin-left: 10px"></textarea>
        </p></label>
        <button type="submit" style="Margin: 0 140px;">Отправить</button>
        <br>
    </form>

    <c:if test="${not empty requestScope.errors}">
        <div style="color: red; font-size: 14pt">
            <c:forEach var="error" items="${requestScope.errors}">
                <span>${error.rusDescription}</span>
                <br>
            </c:forEach>
        </div>
    </c:if>
    <c:if test="${not empty requestScope.finish}">
         <div style="color: ForestGreen; font-size: 15pt">
             <span>${requestScope.finish}</span>
         </div>
     </c:if>
</body>
</html>
