package com.reddius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reddius.model.Subreddius;

@Repository
public interface SubreddiusRepository extends JpaRepository<Subreddius, Long>{

}
