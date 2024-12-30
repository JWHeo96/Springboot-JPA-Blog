<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ include file="../layout/header.jsp"%>
<div class="container">

    <form>
        <input type="hidden" id="id" value="${principal.user.id}"/>
        <div class="form-group">
            <label for="social">Social</label>
            <input type="text" value="${principal.user.oauth.toUpperCase()}" class="form-control" placeholder="Enter Social" id="social" readonly>
        </div>
        <div class="form-group">
            <label for="username">Username</label>
            <input type="text" value="${principal.user.username}" class="form-control" placeholder="Enter Username" id="username" readonly>
        </div>

        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" placeholder="Enter password" id="password"
                   <c:if test="${not empty principal.user.oauth}">value="${principal.user.password} "readonly</c:if>/>
        </div>

        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" value="${principal.user.email}" class="form-control" placeholder="Enter email" id="email" <c:if test="${not empty principal.user.oauth}">readonly</c:if>/>
        </div>
    </form>
    <button id="btn-update" class="btn btn-primary">Update</button>
</div>
<script type="text/javascript" src="/js/user.js"></script>

<jsp:include page="../layout/footer.jsp"/>