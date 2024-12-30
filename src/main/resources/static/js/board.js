let index = {
    init: function() {
        console.log("board.js init");
        $("#btn-save").on("click", this.save.bind(this)); // 바인딩으로 컨텍스트 고정
        $("#btn-delete").on("click", this.deleteById.bind(this)); // 바인딩으로 컨텍스트 고정
        $("#btn-update").on("click", this.update.bind(this)); // 바인딩으로 컨텍스트 고정
        $("#btn-reply-save").on("click", this.replySave.bind(this)); // 바인딩으로 컨텍스트 고정
    },

    save: function() {
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        };
        console.log(data);

        $.ajax({
            // 회원가입 수행 요청
            type: "POST",
            url: "/api/board",
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8",
        }).done(function(res) {
            alert("글쓰기가 완료되었습니다.");
            location.href = "/"
        }).fail(function(err) {
            alert(JSON.stringify(err));
        });
    },

    deleteById: function() {
        $.ajax({
            type: "DELETE",
            url: "/api/board/" + $("#id").text(),
            contentType: "application/json; charset=utf-8",
        }).done(function(res) {
            alert("글 삭제가 완료되었습니다.");
            location.href = "/"
        }).fail(function(err) {
            alert(JSON.stringify(err));
        });
    },

    update: function() {
        let id = $("#id").val();

        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        };
        console.log(data);

        $.ajax({
            // 회원가입 수행 요청
            type: "PUT",
            url: "/api/board/" + id,
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8",
        }).done(function(res) {
            alert("글 수정이 완료되었습니다.");
            location.href = "/"
        }).fail(function(err) {
            alert(JSON.stringify(err));
        });
    },

    replySave: function() {
        let data = {
            boardId: $("#boardId").val(),
            content: $("#reply-content").val(),
        };
        console.log(data);

        $.ajax({
            // 회원가입 수행 요청
            type: "POST",
            url: `/api/board/${data.boardId}/reply`,
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8",
        }).done(function(res) {
            alert("댓글 작성이 완료되었습니다.");
            location.href = `/board/${data.boardId}`;
        }).fail(function(err) {
            alert(JSON.stringify(err));
        });
    },

    replyDelete: function(boardId, replyId) {
        $.ajax({
            type: "DELETE",
            url: `/api/board/${boardId}/reply/${replyId}`,
            contentType: "application/json; charset=utf-8",
        }).done(function(res) {
            alert("댓글 삭제가 완료되었습니다.");
            location.href = `/board/${boardId}`;
        }).fail(function(err) {
            alert(JSON.stringify(err));
        });
    },
};

index.init();