package com.reddius.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reddius.model.Post;
import com.reddius.model.User;
import com.reddius.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long>{
       Optional<Vote> findByPostAndUser(Post post, User user);
       List<Optional<Vote>> findByPost(Post post);
}
