package com.reddius.service;

import java.time.Instant;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reddius.exceptions.RefreshTokenNotFoundException;
import com.reddius.model.RefreshToken;
import com.reddius.repository.RefreshTokenRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RefreshTokenService {

	   private final RefreshTokenRepository refreshTokenRepository;
	   
	   @Transactional
	   public RefreshToken generateRefreshToken() {
		            
		      RefreshToken refreshToken = new RefreshToken();
		      refreshToken.setToken(UUID.randomUUID().toString());
		      refreshToken.setCreatedDate(Instant.now());
		            
		       return refreshTokenRepository.save(refreshToken);
	   }
	   
	   @Transactional
	   public void deleteRefreshToken(String token) {   
		      refreshTokenRepository.deleteByToken(token);
	   }
	   
	   @Transactional(readOnly = true)
	   public void validateRefreshToken(String refreshtoken) {
		     refreshTokenRepository.findByToken(refreshtoken)
		    		               .orElseThrow(() -> new RefreshTokenNotFoundException(refreshtoken));
	   }
}
