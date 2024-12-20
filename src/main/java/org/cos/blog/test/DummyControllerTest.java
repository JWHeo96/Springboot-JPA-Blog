package org.cos.blog.test;

import org.cos.blog.model.RoleType;
import org.cos.blog.model.User;
import org.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

// html 파일이 아니라 data를 리턴해주는 컨트롤러
@RestController
public class DummyControllerTest {

    @Autowired // 의존성 주입(DI)
    private UserRepository userRepository;

    @DeleteMapping("/dummy/user/{id}")
    public String deleteUesr(@PathVariable int id) {

        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("삭제에 실패하였습니다. 해당 id는 DB에 없습니다."));
        userRepository.deleteById(user.getId());

        return user.getUsername() + " 사용자가 삭제되었습니다.";
    }

    // email, password
    @Transactional // 함수 호출 시 트랜잭션이 시작됨 / 함수 종료 시 트랜잭션 종료 및 자동 Commit이 됨
    @PutMapping("/dummy/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User requestUser) { // json 데이터를 요청 => Java Object(MessageConverter의 Jaskson 라이브러리가 변환해서 받아준다.)

        System.out.println("id : " + id);
        System.out.println("passwword : " + requestUser.getPassword());
        System.out.println("email : " + requestUser.getEmail());

        User user = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("수정에 실패하였습니다");
        }); // 데이터가 영속화되는 시점
        // 값의 변경 시점
        user.setPassword(requestUser.getPassword());
        user.setEmail(requestUser.getEmail());

        // save 함수는 id를 전달하지 않으면 insert를 해주고
        // save 함수는 id를 전달하면 해당 id에 대한 데이터가 있으면 update를 해주고
        // save 함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 한다.
        // userRepository.save(user);

        // 더티 체킹 : 메소드 종료 시 트랜잭션이 종료되면서 변경된 내용을 영속화된 데이터에 수정하여 커밋함(변경 감지)
        return user;
    }

    // http://localhost:8000/blog/dummy/userList
    @GetMapping("/dummy/users")
    public List<User> user() {
        return userRepository.findAll();
    }

    // 한 페이지당 2건의 데이터를 리턴받아 볼 예정
    // http://localhost:8000/blog/dummy/user?page=0
    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pagingUser = userRepository.findAll(pageable);

        List<User> users = pagingUser.getContent();
        return users;
    }

    // {id} 주소로 파라미터를 전달 받을 수 있음
    // http://localhost:8000/blog/dummy/user/3
    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        // user/4을 찾으면 내가 데이터베이스에서 못 찾아오게 되면 user가 null이 될 것 아냐?
        // 그럼 return null이 리턴이 되잖아. 그럼 프로그램에 문제가 있지 않겠니?
        // Optional로 너의 User 객체를 감싸서 가져올테니 null인지 아닌지 판단해서 return 해
        // get() : null될 일이 없으니 Optional에서 뽑아서 바로 값을 전달
        // orElseGet() : 없다면 객체 하나 만들어서 user에 넣어줘라 -> supplier 타입 -> new Supplier 익명 객체가 만들어짐 -> get 오버라이딩
        /*return userRepository.findById(id).orElseGet(new Supplier<User>() {
            @Override
            public User get() {
                return new User(); // 빈 객체 리턴
            }
        });*/
        User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("user not found. id : " + id)); // 람다식
        // 요청 : 웹브라우저
        // user 객체 = 자바 오브젝트
        // 변환 (웹브라우저가 이해할 수 있는 데이터) -> json (Gson 라이브러리)
        // 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
        // 만약 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서
        // user 오브젝트를 json으로 변환해서 브라우저에게 던져줍니다.
        return user;
    }

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
