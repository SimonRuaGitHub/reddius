package com.reddius.dto;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefreshTokenRequest {

	   @NotBlank
	   private String refreshToken;
	   
	   @NotBlank
	   private String username;
}
