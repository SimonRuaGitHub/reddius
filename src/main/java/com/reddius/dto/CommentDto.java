package com.reddius.dto;

import java.time.Instant;

import com.reddius.model.Post;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public abstract class CommentDto {
	
	protected String text;

	protected long postid;
	
	protected long userid;
}
