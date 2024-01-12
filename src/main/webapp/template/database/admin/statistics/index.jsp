
<%@ page language="java" contentType="text/html" %>

<!doctype html>
    <html>
        <head><title>Statistics</title></head>
    <body>
        <h1>Hello! This is statistics!</h1>
        <h2>Number of active users</h2>
        <%= request.getAttribute("total_active_sessions") %>
        <h2>Number of registered users</h2>
        <%= request.getAttribute("total_registration_users") %>
        <h2>Number of user messages:</h2>
        <%= request.getAttribute("total_messages") %>
    </body>
</html>