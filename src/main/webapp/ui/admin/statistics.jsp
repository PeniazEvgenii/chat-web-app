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

    <h2>Статистика приложения</h2>

    <table>
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