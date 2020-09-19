package com.reddius.dto;

import java.time.Instant;

import com.reddius.model.Post;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentDto {

	private Long id;
	
	private String text;
	
	private Instant createdDate;

	private long postid;
	
	private long userid;
}
