<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<jsp:include page="../layout/header.jsp"/>
<div class="container">

    <form>
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Enter Title" id="title">
        </div>
        <div class="form-group">
            <textarea class="form-control summernote" rows="5" id="content"></textarea>
        </div>
    </form>
    <button id="btn-save" class="btn btn-primary">Save</button>
</div>
<script>
    $('.summernote').summernote({
        tabsize: 2,
        height: 300
    });
</script>
<script src="/js/board.js"></script>
<jsp:include page="../layout/footer.jsp"/>