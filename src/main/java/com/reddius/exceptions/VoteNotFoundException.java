package com.reddius.exceptions;

public class VoteNotFoundException extends RuntimeException{

	   public VoteNotFoundException(String username, String postname) {
		      super("Vote not found for user :"+username+" and post: "+postname);
	   }
}
