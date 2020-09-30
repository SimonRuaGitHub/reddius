package com.reddius.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.reddius.exceptions.SpringReddiusException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtProvider {
	
	private KeyStore keyStore;
	
	@Value("${jwt.expiration.time}")
	private Long jwtExpirationInMillis;

	public String generateToken(Authentication authentication) {
		User ppalFromUserSecurity = (User) authentication.getPrincipal();
		
		return Jwts.builder()
				   .setSubject(ppalFromUserSecurity.getUsername())
				   .setIssuedAt(Date.from(Instant.now()))
				   .signWith(getPrivateKey())
				   .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
				   .compact();
	}
	
	public String generateTokenWithUsername(String username) {
		
		return Jwts.builder()
				   .setSubject(username)
				   .setIssuedAt(Date.from(Instant.now()))
				   .signWith(getPrivateKey())
				   .setExpiration(Date.from(Instant.now().plusMillis(jwtExpirationInMillis)))
				   .compact();
	}
	
	@PostConstruct
	public void loadKeyStore() {
		try {
			keyStore = keyStore.getInstance("JKS");
			InputStream resourceAsStream = getClass().getResourceAsStream("/springblog.jks");
			keyStore.load(resourceAsStream, "secret".toCharArray());
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException e) {
			throw new SpringReddiusException("Exception ocurred while retrieving public key from keystore");
		}	
	}

	private PrivateKey getPrivateKey() {
		try {
			return (PrivateKey) keyStore.getKey("springblog", "secret".toCharArray());
		}catch(KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
			throw new SpringReddiusException("Exception ocurred while retrieving public key from keystore");
		}
	}
	
	public boolean validateToken(String jwt) {
		Jwts.parserBuilder().setSigningKey(getPublicKey()).build().parseClaimsJws(jwt);
		return true;
	}
	
	private PublicKey getPublicKey() {
		
		try {
			PublicKey publicKey = keyStore.getCertificate("springblog").getPublicKey();
			return publicKey;
		} catch (KeyStoreException e) {
			throw new SpringReddiusException("Exception occurred while retrieving public key from key store");
		}
	}
	
	public String getUsernameFromJwt(String token) {
		Claims claims =  Jwts.parserBuilder()
				             .setSigningKey(getPublicKey())
				             .build()
				             .parseClaimsJws(token)
				             .getBody();
		
		return claims.getSubject();
	}
	
	public Long getJwtExpirationInMillis() {
		   return jwtExpirationInMillis;
	}
}
