package org.cos.blog.repository;

import org.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository는 User 테이블이 관리하는 레포지토리이며, User 테이블의 PK는 Integer이다.
// DAO (Data Access Object)
// 자동으로 bean등록이 된다. @Repository 생략 가능
public interface UserRepository extends JpaRepository<User, Integer> { // CRUD 기능 제공

}
