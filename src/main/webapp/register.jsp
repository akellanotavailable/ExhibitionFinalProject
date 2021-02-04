<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Title</title>

    <link rel="stylesheet" href="style/reset.css">

    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Hachi+Maru+Pop&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
            crossorigin="anonymous"></script>

</head>
<body>
<div class="container">
    <h2>Main page</h2>
    <form action="register" method="post">
        <div class="mb-3 row">
            <label for="inputLogin" class="col-sm-2 col-form-label">Login</label>
            <div class="col-sm-10">
                <input name="login" type="text" class="form-control" id="inputLogin">
            </div>
        </div>
        <div class="mb-3 row">
            <label for="inputEmail" class="col-sm-2 col-form-label">Email</label>
            <div class="col-sm-10">
                <input name="email" type="text" class="form-control" id="inputEmail">
            </div>
        </div>
        <div class="mb-3 row">
            <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-10">
                <input name="password" type="password" class="form-control" id="inputPassword">
            </div>
        </div>
        <div class="mb-3 row">
            <label for="inputRePassword" class="col-sm-2 col-form-label">Re-enter password</label>
            <div class="col-sm-10">
                <input name="rePassword" type="password" class="form-control" id="inputRePassword">
            </div>
        </div>
        <div class="mb-3 row">
            <div class="col-sm-10">
                <button type="submit" class="btn btn-primary mb-3">Register</button>
            </div>
        </div>
    </form>
    <p style="color: red"><c:out value="${message}"/></p>
</div>
</body>
</html>