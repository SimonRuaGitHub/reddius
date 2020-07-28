package com.reddius.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.reddius.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
