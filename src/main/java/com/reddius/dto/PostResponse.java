package com.reddius.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class PostResponse extends PostDto{

	private String subreddiusName;
	private String userName;
	private Integer voteCount;
	private Integer commentCount;
	private String duration;
}
