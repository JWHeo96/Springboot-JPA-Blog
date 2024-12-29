<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<jsp:include page="../layout/header.jsp"/>
<div class="container">

    <form>
        <input type="hidden" id="id" value="${board.id}"/>
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Enter Title" id="title" value="${board.title}">
        </div>
        <div class="form-group">
            <textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
        </div>
    </form>
    <button id="btn-update" class="btn btn-primary">Update</button>
</div>
<script>
    $('.summernote').summernote({
        tabsize: 2,
        height: 300
    });
</script>
<script src="/js/board.js"></script>
<jsp:include page="../layout/footer.jsp"/>