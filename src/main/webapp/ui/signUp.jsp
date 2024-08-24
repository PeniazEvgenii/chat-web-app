<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Регистрация</title>
    <%@ include file="header.jsp" %>
</head>
<body>

    <h2>Регистрация пользователя:</h2>
    <form action="${pageContext.request.contextPath}/api/user" method="post">

        <p><label for="loginID"> Логин:</label>
            <input type="text" name="login" id="loginID" value="${requestScope.enterLogin}"  style="margin-left: 70px">
        </p>

        <p><label for="passwordId"> Пароль:
            <input type="password" name="password" id="passwordId"  style="margin-left: 60px">
        </p></label>

        <p><label for="nameID"> ФИО:
            <input type="text" name="name" id="nameID"  style="margin-left: 75px" >
        </p></label>

        <p><label for="birthdayID"> Дата рождения:
            <input type="date" name="birthday" id="birthdayID"  style="margin-left: 5px">
        </p></label>
        <br>
        <button type="submit" style="Margin: 0 140px;">Зарегистрироваться</button>

        <br>
        <br>

        <c:if test="${not empty requestScope.errors}">
            <div style="color: red; font-size: 14pt">
                <c:forEach var="error" items="${requestScope.errors}">
                    <span>${error.description}</span>
                    <br>
                </c:forEach>
            </div>
        </c:if>

    </form>

</body>
</html>
