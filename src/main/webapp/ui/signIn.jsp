<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Login</title>
    <%@ include file="header.jsp" %>
</head>
<body>
    <h2>Вход пользователя в систему:</h2>
    <c:if test="${param.register != null}">
        <div style="color: ForestGreen; font-size: 13pt">
           <span>Пользователь успешно зарегистрирован. Введите логин и пароль</span>
        </div>
    </c:if>
    <form action="${pageContext.request.contextPath}/api/login" method="post">
        <p><label for="loginID"> Логин:</label>
            <input type="text" name="login" id="loginID" value="${param.login}"  style="margin-left: 35px">
        </p>
        <p><label for="passwordId"> Пароль:
            <input type="password" name="password" id="passwordId"  style="margin-left: 25px">
        </p></label>
            <input type="hidden" id="timezone" name="timezone">
        <br>
        <button type="submit" style="Margin: 0 140px;">Вход</button>
        <br>
    </form>

    <c:if test="${param.error != null}">
        <div style="color: red; font-size: 15pt">
            <span>Неверный логин или пароль</span>
        </div>
    </c:if>

    <h4>Если у вас нет учетной записи, вам необходимо пройти регистрацию</h4>

    <a href="${pageContext.request.contextPath}/api/user">
        <button type="button" style="Margin: 0 140px;">Зарегистрироваться</button>
    </a>

    <script>
         window.onload = function() {
            const timeZone = Intl.DateTimeFormat().resolvedOptions().timeZone;
            document.getElementById('timezone').value = timeZone;
         };
    </script>
</body>
</html>
