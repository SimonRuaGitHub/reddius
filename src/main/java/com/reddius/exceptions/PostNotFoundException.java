package com.reddius.exceptions;

public class PostNotFoundException extends RuntimeException{

	public PostNotFoundException(String id) {
		   super("Post was not found using following post id: "+id);
	}
}
