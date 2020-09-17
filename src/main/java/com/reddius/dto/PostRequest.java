package com.reddius.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRequest {
	
	private long postId;
	private String postName;
	private String url;
	private String description;
    private long userid;
	private long subreddiusid;
}
