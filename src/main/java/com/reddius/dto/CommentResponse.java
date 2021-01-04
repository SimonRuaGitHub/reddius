package com.reddius.dto;

import lombok.Data;

@Data
public class CommentResponse extends CommentDto{

	private Long id;
	private String duration;
}
