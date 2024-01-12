<%@ page language="java"
 contentType="text/html; charset=UTF-8"
 pageEncoding="UTF-8"%>
 <!DOCTYPE html>
 <html>
 <head>
     <title>Вход</title>
 </head>
 <body>
     <h1>Вход</h1>
     <form method="POST" action="${pageContext.request.contextPath}/api/login" >
         <label for="username">Имя пользователя:</label>
         <input type="text" id="username" name="username" required><br>

         <label for="password">Пароль:</label>
         <input type="password" id="password" name="password" required><br>

         <input type="submit" value="Войти">
     </form>
     <a href="http://localhost:8080/user-messages-api/template/ui">На главную</a>
 </body>
 </html>