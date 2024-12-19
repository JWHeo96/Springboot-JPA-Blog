package org.cos.blog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
// ORM -> Java(다른언어 포함) Object -> 테이플로 매핑해주는 기술
@Entity // User 클래스가 MySQL에 테이블이 생성이 된다.
// @DynamicInsert // insert 시에 null인 필드를 제외시켜준다.
public class User {

    @Id // Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private int id; // 시퀀스, auto_increment

    @Column(nullable = false, length = 30)
    private String username; // 아이디

    @Column(nullable = false, length = 100) // 123456 => 해쉬(비밀번호 암호화)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    //@ColumnDefault("'user'")
    // DB는 RoleType이라는게 없다. 때문에 String 타입이라는걸 정의해준다.
    @Enumerated(EnumType.STRING)
    private RoleType role; // Enum을 쓰는게 좋다. // admin, user, manager ex) mangerrrrr같은 정확하지 않은 데이터가 들어갈 수 있다.
    // Enum을 사용하는 이유 : 도메인을 정해놓고 사용 ex) 초등학생 학년 : 1 ~ 6 등의 정해진 범위

    @CreationTimestamp // 시간 자동 입력
    private Timestamp createDate;
}
