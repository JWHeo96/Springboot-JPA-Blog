package org.cos.blog.test;

import org.cos.blog.model.RoleType;
import org.cos.blog.model.User;
import org.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyControllerTest {

    @Autowired // 의존성 주입(DI)
    private UserRepository userRepository;

    // http://localhost:8000/blog/dummy/join (요청)
    // http의 body에 username, password, email 데이터를 가지고 (요청)
    @PostMapping("/dummy/join") // @RequestParam("username")을 적어주면 변수명을 자유자재로 사용 가능
    public String join(User user) { // key=value(약속된 규칙)
        System.out.println("id : " + user.getId());
        System.out.println("username : " + user.getUsername());
        System.out.println("password : " + user.getPassword());
        System.out.println("email : " + user.getEmail());
        System.out.println("role : " + user.getRole());
        System.out.println("createDate : " + user.getCreateDate());

        user.setRole(RoleType.USER);
        userRepository.save(user);

        return "회원가입이 완료되었습니다.";
    }
}
