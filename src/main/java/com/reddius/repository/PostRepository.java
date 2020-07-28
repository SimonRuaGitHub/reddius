package com.reddius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reddius.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

}
