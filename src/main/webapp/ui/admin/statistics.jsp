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
    <h2>Статистика приложения</h2>

    <table>
        <tr>
           <td>Количество активных пользователей</td>
           <td>Количество пользователей в приложении"</td>
           <td>Количество сообщений отправленных в приложении</td>
        </tr>
        <c:forEach var="statistic" items="${requestScope.statistics}">
            <tr>
                <td>${statistic.key} :</td>
                <td>${statistic.value}</td>
            </tr>
        </c:forEach>
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