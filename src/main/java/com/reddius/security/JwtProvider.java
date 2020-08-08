package com.reddius.security;

import java.io.IOException;
import java.io.InputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.annotation.PostConstruct;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.reddius.exceptions.SpringReddiusException;
import io.jsonwebtoken.Jwts;

@Service
public class JwtProvider {
	
	private KeyStore keyStore;

	public String generateToken(Authentication authentication) {
		User ppalFromUserSecurity = (User) authentication.getPrincipal();
		
		return Jwts.builder()
				   .setSubject(ppalFromUserSecurity.getUsername())
				   .signWith(getPrivateKey())
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
}
