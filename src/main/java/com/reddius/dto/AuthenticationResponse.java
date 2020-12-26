package com.reddius.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class AuthenticationResponse {
	private String authenticationToken;
	private String username;
	private String refreshToken;
	private Instant expiresAt;
	private long userid;
}
