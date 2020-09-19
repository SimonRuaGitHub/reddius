package com.reddius.dto;

import com.reddius.model.VoteType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VoteDto {

	private long id;
	private VoteType voteType;
	private long postid;
	private long userid;
	
}
