<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<jsp:include page="../layout/header.jsp"/>
<div class="container">
    <form action="/action_page.php">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" class="form-control" placeholder="Enter Username" id="username">
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" placeholder="Enter email" id="email">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" placeholder="Enter password" id="password">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
</div>
<jsp:include page="../layout/footer.jsp"/>