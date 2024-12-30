<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<jsp:include page="../layout/header.jsp"/>
<div class="container">
    <form action="/auth/loginProc" method="post">
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" name="username" class="form-control" placeholder="Enter Username" id="username">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
        </div>
        <button id="btn-login" class="btn btn-primary">Login</button>
        <a href="https://kauth.kakao.com/oauth/authorize?response_type=code&client_id=a386028a0e5845510b5985d82d2d4e7c
&redirect_uri=http://localhost:8000/auth/kakao/callback"><img src="/image/kakao_login_btn.png" style="height:38px"/></a>
    </form>
</div>
<jsp:include page="../layout/footer.jsp"/>