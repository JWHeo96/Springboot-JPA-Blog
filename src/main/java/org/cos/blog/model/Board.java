package org.cos.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    //@Lob // 대용량 데이터 : TINYTEXT으로 등록되어 에러남
    @Column(columnDefinition = "longblob")
    private String content; // 섬머노트 라이브러리 <html>태그가 섞여서 디자인 됨.

    //@ColumnDefault("0")
    private int count; // 조회수

    // FetchType.EAGER 전략 -> 무조건 조회해와라
    @ManyToOne(fetch = FetchType.EAGER) // Many = Board, User = One -> 한명의 사용자가 여러개의 게시글을 작성할 수 있다. -> 연관관계
    @JoinColumn(name="userId") // 생성되는 필드명
    private User user; // DB는 오브젝트를 저장할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.

    // mappedBy 연관관계의 주인이 아니다 (난 FK가 아니다) -> DB에 컬럼을 만들지 말 것. -> 단순히 board를 select할때 join문을 통해 값을 얻기 위함이다.
    // mappedBy = "" <- Reply 클래스에 있는 Field 이름 Board 'board'
    // FetchType.LAZY 전략 -> 없으면 들고오지 마라 -> 게시글 상세보기에 '댓글 펼치기'가 있는 경우
    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // 하나의 게시글은 여러개의 답변을 가질 수 있다.
    //@JoinColumn(name="replyId") 외래키가 필요 없다. 하나의 게시판 데이터에 여러개의 답변 id가 들어가면 안됨
    @JsonIgnoreProperties({"board"}) // Json으로 파싱하지 않는다. (무한참조 방지)
    @OrderBy("id desc")
    private List<Reply> replys;

    @CreationTimestamp
    private Timestamp createDate;
}
