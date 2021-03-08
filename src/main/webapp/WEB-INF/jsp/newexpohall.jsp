<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<%@include file="../jspf/head.jspf" %>
<body>
<div class="container">
    <%@include file="../jspf/navbar.jspf" %>
    <h4>Create exposition:</h4>
    <h5>Step 2:</h5>

    <form action="/reservehall" method="post">
        <%@include file="../jspf/halls.jspf"%>
        <button type="submit" class="btn btn-primary mb-3">Submit</button>
    </form>

</div>
</body>
</html>