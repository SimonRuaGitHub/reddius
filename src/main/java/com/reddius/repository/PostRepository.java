package com.reddius.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reddius.model.Post;
import com.reddius.model.Subreddius;
import com.reddius.model.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{
       List<Post> findAllBySubreddius(Subreddius subreddius);
       List<Post> findAllByUser(User user);
}
