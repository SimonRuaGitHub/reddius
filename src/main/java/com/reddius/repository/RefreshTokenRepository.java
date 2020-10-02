package com.reddius.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reddius.model.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long>{
	
       public Optional<RefreshToken> findByToken(String token);
	   public void deleteByToken(String token);
}
