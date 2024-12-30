package org.cos.blog.repository;

import jakarta.transaction.Transactional;
import org.cos.blog.dto.ReplySaveRequestDto;
import org.cos.blog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Integer> { // CRUD 기능 제공

    @Modifying // INSERT, UPDATE, DELETE와 같은 쿼리를 실행하려면 이 어노테이션이 필요
    @Query(value = "INSERT INTO reply(userId, boardId, content, createDate) VALUES(?1,?2,?3,now())", nativeQuery = true)
    int mSave(int userId, int boardId, String content);
}
