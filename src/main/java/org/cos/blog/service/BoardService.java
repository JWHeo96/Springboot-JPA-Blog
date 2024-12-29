package org.cos.blog.service;

import org.cos.blog.model.Board;
import org.cos.blog.model.RoleType;
import org.cos.blog.model.User;
import org.cos.blog.repository.BoardRepository;
import org.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Transactional
    public void write(Board board, User user) {
        board.setCount(0);
        board.setUser(user);
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Page<Board> getBoardList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board getBoardDetail(int id) {
        return boardRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다." + id)
                );
    }

    @Transactional
    public void deleteById(int id) {
        System.out.println("BoardApiController : deleteById() id : " + id);
        boardRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Board reqBoard) {
        System.out.println("BoardApiController : update() id : " + id);
        Board board = boardRepository.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다")
                ); // 영속화 완료
        board.setTitle(reqBoard.getTitle());
        board.setContent(reqBoard.getContent());
        // 해당 함수 종료시에(Service가 종료될 때) 트랜잭션이 종료됨. 이때 더티체킹 - 자동 업데이트가 됨. DB flush
    }
}