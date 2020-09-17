package com.reddius.exceptions;

public class SubreddiusNotFoundException extends RuntimeException{

	   public SubreddiusNotFoundException(long id) {
		      super("Subreddius not found using id: "+id);
	   }
}
