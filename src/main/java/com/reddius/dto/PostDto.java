package com.reddius.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public abstract class PostDto {

	protected long postId;
    protected long userId;
	protected String postName;
	protected String url;
	protected String description;
}
