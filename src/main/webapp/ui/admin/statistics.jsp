<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Statistics</title>
    <%@ include file="../header.jsp" %>

    <style>
        tr:nth-child(odd) {
          background-color: #f6f6f6;
        }
        td {
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
    <a href="${pageContext.request.contextPath}/">
        <button type="button" style="Margin: 0 0px; width: 10%;">Стартовая страница</button>
    </a>
    <h2>Статистика приложения</h2>

    <table>
        <tr>
           <td>Количество активных пользователей:</td>
           <td>${requestScope.statistics.countActiveUsers}</td>
        </tr>
        <tr>
           <td>Количество пользователей в приложении</td>
           <td>${requestScope.statistics.countAllUsers}</td>
        </tr>
        <tr>
            <td>Количество сообщений отправленных в приложении</td>
            <td>${requestScope.statistics.countMessages}</td>
        </tr>
    </table>
    <br>
     <details>
         <summary>Активные пользователи</summary>
         <span>Раскрывающийся текст</span><br>
         <span>Раскрывающийся текст</span><br>
         <span>Раскрывающийся текст</span>
     </details>
</body>
</html>