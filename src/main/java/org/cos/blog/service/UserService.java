package org.cos.blog.service;

import jakarta.transaction.Transactional;
import org.cos.blog.model.User;
import org.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// 스프링이 컴포넌트 스캔을 통해서 Bean에 등록을 해줌. IoC를 해준다.
// Repository는 CRUD를 하나씩 들고 있다면, Service는 각 CRUD를 모아 서비스를 실행 (ex: 송금 -> 입금후 업데이트, 송금 후 업데이트 등 로직이 모여져 있음)
// 두개의 트랜잭션을 하나의 트랜잭션으로 묶어서 서비스화 할 수 있다.
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional // 전체 서비스가 하나의 트랜잭션으로 묶이게 됨, 성공시 커밋 실패 시 롤백
    public int join(User user) {
        try {
            return userRepository.save(user).getId();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("UserService : 회원가입() : " + e.getMessage());

            return -1;
        }
    }
}
