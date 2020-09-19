package com.reddius.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reddius.model.Comment;
import com.reddius.model.Post;
import com.reddius.model.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{
       Optional<List<Comment>> findAllByPost(Post post);
       Optional<List<Comment>> findAllCommentsByUser(User user);
}
