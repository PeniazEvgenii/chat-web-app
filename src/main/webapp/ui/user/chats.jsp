<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Chats</title>
    <%@ include file="../header.jsp" %>
</head>
<body>
    <%@ include file="../LogOut.jsp" %>
    <a href="${pageContext.request.contextPath}/">
         <button type="button" style="Margin: 0 0px; width: 10%;">Стартовая страница</button>
    </a>
    <br>
    <span>
        <b>Текущий пользователь: ${sessionScope.user.login}</b>
    </span>

    <h2>Список сообщений</h2>

    <table>
        <tr>
            <th>Время и дата</th>
            <th>От кого</th>
            <th>Сообщение</th>
        </tr>
        <c:forEach var="message" items="${requestScope.messages}">
            <tr>
                <td>${message.createAt}</td>
                <td>${message.userFrom.login}</td>
                <td>${message.body}</td>
            </tr>
        </c:forEach>
    </table>
    <br>
</body>
</html>