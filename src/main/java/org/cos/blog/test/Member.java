package org.cos.blog.test;

import lombok.*;

// 해당 클래스가 아닌 다른 곳에서 접근을 하려하면
// 변수의 상태는 메소드에 의해 변경되게 설계되어야 함(객체지향)
// 상태 변경을 위해 Getter/Setter 메서드를 만들어야 함

/*@Getter
@Setter*/
@Data // Getter/Setter 메서드 생성
//@AllArgsConstructor // 모든 인자를 가진 생성자 생성
@NoArgsConstructor // 빈 생성자
//@RequiredArgsConstructor // final이 붙은 변수에 대한 생성자를 만들어줌
public class Member {
    // IntelliJ Generator 단축키 : Alt + Ins
    private int id;
    private String username;
    private String password;
    private String email;

    // 자동으로 증가하는 id값인 시퀀스 없이 객체를 생성하려면 name password email을 인자로 가진 생성자가 하나 더 필요하다.
    // 하지만 Builder를 사용하면 아래와 같이 id값을 제외 후 값을 설정할 수 있다.
    // 내가 값을 넣을 때 순서를 지키지 않아도 된다.
    /*Member user = Member.builder()
            .username("jwheo")
            .password("1234")
            .email("jwheo@naver.com")
            .build();*/
    @Builder
    public Member(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
