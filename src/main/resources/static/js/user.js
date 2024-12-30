let index = {
    init: function() {
        console.log("init");
        $("#btn-save").on("click", this.save.bind(this)); // 바인딩으로 컨텍스트 고정
        $("#btn-update").on("click", this.update.bind(this)); // 바인딩으로 컨텍스트 고정
    },

    save: function() {
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val(),
        };
        console.log(data);

        // ajax 호출시 default가 비동기 호출
        // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
        // ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환
        $.ajax({
            // 회원가입 수행 요청
            type: "POST",
            url: "/auth/joinProc",
            data: JSON.stringify(data), // http body 데이터
            contentType: "application/json; charset=utf-8", // body 데이터가 어떤 타입인지(MIME)
            //dataType: "json" // 요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json이라면) => javascript오브젝트로 변경
        }).done(function(res) {
            console.log(res);
            if (res.status === 500) {
                alert("회원가입에 실패하였습니다.");
            } else {
                alert("회원가입이 완료되었습니다.");
                location.href = "/"
            }
        }).fail(function(err) {
            alert(JSON.stringify(err));
        }); // ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
    },

    update: function() {
        let data = {
            id: $("#id").val(),
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val(),
        };

        $.ajax({
            type: "PUT",
            url: "/user",
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
        }).done(function(res) {
            alert("회원정보 수정이 완료되었습니다.");
            location.href = "/"
        }).fail(function(err) {
            alert(JSON.stringify(err));
        });
    }
};

index.init();