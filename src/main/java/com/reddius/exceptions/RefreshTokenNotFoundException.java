package com.reddius.exceptions;

public class RefreshTokenNotFoundException extends RuntimeException{

	   public RefreshTokenNotFoundException(String refreshtoken) {
		      super("The refresh token wasn't found: "+refreshtoken);
	   }
}
