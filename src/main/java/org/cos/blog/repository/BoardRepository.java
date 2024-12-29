package org.cos.blog.repository;

import org.cos.blog.model.Board;
import org.cos.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Integer> { // CRUD 기능 제공

}
