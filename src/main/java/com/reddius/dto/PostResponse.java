package com.reddius.dto;


import lombok.Data;

@Data
public class PostResponse extends PostDto{

	private String subreddiusName;
	private String userName;
	private Integer voteCount;
	private Integer commentCount;
	private String duration;
}
