<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>LogOut</title>
</head>
<body>
    <c:if test="${not empty sessionScope.user}">
        <form action="${pageContext.request.contextPath}/api/logout" method="post">
           <button type="submit">LogOut</button>
        </form>
    </c:if>
</body>