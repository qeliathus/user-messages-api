<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Регистрация</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/api/user">
    <div class="rendered-form">
    <div class="formbuilder-text form-group field-username">
        <label for="username" class="formbuilder-text-label">Логин</label>
        <input type="text" class="form-control" name="username" access="false" id="username">
    </div>
    <div class="formbuilder-text form-group field-password">
        <label for="password" class="formbuilder-text-label">Пароль</label>
        <input type="text" class="form-control" name="password" access="false" id="password">
    </div>
    <div class="formbuilder-text form-group field-fullName">
        <label for="fullName" class="formbuilder-text-label">Ф.И.О.</label>
        <input type="text" class="form-control" name="fullName" access="false" id="fullame">
    </div>
    <div class="formbuilder-date form-group field-birthday">
        <label for="birthday" class="formbuilder-date-label">Дата рождения
            <br>
        </label>
        <input type="date" class="form-control" name="birthday" access="false" id="birthday">
    </div>
    <div class="formbuilder-button form-group field-button">
        <button class="btn-default btn" name="button" access="false" style="default" id="button">Отправить</button>
    </div>
    </div>
</form>
</body>
</html>
