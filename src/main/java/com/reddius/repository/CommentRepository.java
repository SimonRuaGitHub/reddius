package com.reddius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reddius.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

}
