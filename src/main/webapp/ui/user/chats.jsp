<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Chats</title>
    <%@ include file="../header.jsp" %>

    <style>
        tr:nth-child(odd) {
          background-color: #f6f6f6;
        }
        th, td {
          text-align: left;
          padding: 5px;
          font-size: 18px;
        }
        table {
          border-collapse: collapse;
          width: 50%;
        }
    </style>

</head>
<body>
    <%@ include file="../LogOut.jsp" %>
    <span>
        <b>Текущий пользователь: ${requestScope.messages[0].userTo.login}</b>
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