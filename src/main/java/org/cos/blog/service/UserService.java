package org.cos.blog.service;

import org.cos.blog.auth.PrincipalDetail;
import org.cos.blog.model.RoleType;
import org.cos.blog.model.User;
import org.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다.
// Repository는 CRUD를 하나씩 들고 있다면, Service는 각 CRUD를 모아 서비스를 실행 (ex: 송금 -> 입금후 업데이트, 송금 후 업데이트 등 로직이 모여져 있음)
// 두개의 트랜잭션을 하나의 트랜잭션으로 묶어서 서비스화 할 수 있다.
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Transactional // 전체 서비스가 하나의 트랜잭션으로 묶이게 됨, 성공시 커밋 실패 시 롤백
    public void join(User user) {
        String rawPassword = user.getPassword(); // 1234 원문
        String encPassword = encoder.encode(rawPassword); // 해쉬

        user.setPassword(encPassword);
        user.setRole(RoleType.USER);
        userRepository.save(user);
    }

    @Transactional
    public void update(User reqUser) {
        // 수정시에는 영속성 컨텍스트 User 오브젝트를 영속화시키고, 영속화된 User 오브젝트를 수정
        User user = userRepository.findById(reqUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("사용자가 존재하지 않습니다."));
        user.setPassword(encoder.encode(reqUser.getPassword()));
        user.setEmail(reqUser.getEmail());

        // 회운수정 함수 종료시 = 서비스 종료 = 트랜잭션 종료 = commit이 자동으로 된다.
        // 영속화된 persistance 객체의 변화가 감지되면 더티체킹이 되어 update문을 날려줌.
    }

    /*@Transactional(readOnly = true) // Select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료 (정합성 유지)
    public User login(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }*/
}