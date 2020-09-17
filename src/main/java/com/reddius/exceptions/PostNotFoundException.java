package com.reddius.exceptions;

public class PostNotFoundException extends RuntimeException{

	public PostNotFoundException(long id) {
		   super("Post was not found using following post id: "+id);
	}
	
	public PostNotFoundException(String value) {
		   super("Post was not found using following value: "+value);
	}
}
