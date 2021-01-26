package com.reddius.dto;


import com.reddius.model.VoteType;

import lombok.Data;

@Data
public class PostResponse extends PostDto{

	private String subreddiusName;
	private String userName;
	private Integer voteCount;
	private Integer commentCount;
	private String duration;
	private VoteType voteTypeOfUser;
}
