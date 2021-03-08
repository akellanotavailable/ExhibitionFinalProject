<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<%@include file="../jspf/head.jspf" %>
<body>
<div class="container">
    <%@include file="../jspf/navbar.jspf" %>
    <h4>Creating exposition.</h4>
    <h5>Step 1:</h5>
    <form class="input-group mb-3" id="formInput" method="post" action="/newexposition">
        <div class="input-group-prepend">
            <span class="input-group-text" id="titleLabel">Title</span>
            <input type="text" class="form-control" aria-label="Title" aria-describedby="titleLabel" name="title" required>
        </div>

        <div class="input-group-prepend">
            <span class="input-group-text" id="dateStartTimeLabel">Start date</span>
            <input type="datetime-local" class="form-control" aria-label="Start date"
                   aria-describedby="dateStartTimeLabel" name="dateStart" required>
        </div>

        <div class="input-group-prepend">
            <span class="input-group-text" id="dateEndTimeLabel">End date</span>
            <input type="datetime-local" class="form-control" aria-label="End date" aria-describedby="dateEndTimeLabel"
                   name="dateEnd" required>
        </div>

        <div class="input-group-prepend">
            <span class="input-group-text" id="priceLabel">Price</span>
            <input type="text" class="form-control" aria-label="Price" aria-describedby="priceLabel" name="price"
                   pattern="[0-9]*\.[0-9]{2}" placeholder="000.00" required>
        </div>

        <div class="input-group-prepend">
            <span class="input-group-text" id="ticketLabel">Number of tickets</span>
            <input type="text" class="form-control" aria-label="Number of tickets" aria-describedby="ticketLabel"
                   name="capacity" pattern="[0-9]*" placeholder="000" required>
        </div>

        <div class="input-group-prepend">
            <span class="input-group-text" id="imageLabel">Image link</span>
            <input type="text" class="form-control" aria-label="Image link" aria-describedby="imageLabel"
                   name="imageLink" pattern="(https:\/\/)*(.+)" placeholder="https://...">
        </div>

        <div class="input-group-prepend">
            <span class="input-group-text" id="detailsLinkLabel">Event webpage</span>
            <input type="text" class="form-control" aria-label="Event webpage" aria-describedby="detailsLinkLabel"
                   name="detailsLink" pattern="(https:\/\/)*(.+)" placeholder="https://...">
        </div>

        <button type="submit" class="btn btn-primary mb-3">Proceed</button>

    </form>
</div>
</body>
</html>