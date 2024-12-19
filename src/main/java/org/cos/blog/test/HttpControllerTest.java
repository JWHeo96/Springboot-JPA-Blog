package org.cos.blog.test;

import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.HTML;

/**
 * 사용자가 요청 -> 응답(HTML 파일)
 * @Controller
 */

// 사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {

    private static final String TAG = "HttpControllerTest:";

    @GetMapping("/http/lombok")
    public String lombokTest() {
        Member user = Member.builder()
                .username("jwheo")
                .password("1234")
                .email("jwheo@naver.com")
                .build();
        System.out.println(TAG + "getter : " + user.getUsername());
        user.setUsername("cos");
        System.out.println(TAG + "setter : " + user.getUsername());
        
        return "lombok test 완료";
    }

    // 인터넷 브라우저 요청은 무조건 get 요청밖에 할 수 없다.
    // http://localhost:8080/http/get (select)
    @GetMapping("/http/get")
    public String getTest(Member user) { // ?id=1&username=jwheo&password=1234&email=jwheo@naver.com // MessageConverter (스트링부트)
        return "get 요청 : " + user.getId() + ", "+ user.getUsername() + ", " + user.getPassword() + ", " + user.getEmail();
    }

    // http://localhost:8080/http/post (insert)
    @PostMapping("/http/post") // raw -> text/plain, application/json
    public String postTest(@RequestBody Member user) { // MessageConverter (스트링부트)
        return "post 요청 : " + user.getId() + ", "+ user.getUsername() + ", " + user.getPassword() + ", " + user.getEmail();
    }

    // http://localhost:8080/http/put (update)
    @PutMapping("/http/put")
    public String putTest(@RequestBody Member user) {
        return "put 요청 : " + user.getId() + ", "+ user.getUsername() + ", " + user.getPassword() + ", " + user.getEmail();
    }

    // http://localhost:8080/http/delete (delete)
    @DeleteMapping("/http/delete")
    public String deleteTest() {
        return "delete 요청";
    }
}
