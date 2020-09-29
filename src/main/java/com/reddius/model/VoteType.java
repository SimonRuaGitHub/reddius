package com.reddius.model;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.reddius.exceptions.SpringReddiusException;

public enum VoteType {
	
   UPVOTE(1), DOWNVOTE(-1);
	
	private int direction;
	
	private VoteType(int direction){
		this.direction=direction;
	}
	
	public int getDirection() {
		   return direction;
	}
	
	public static VoteType checkVoteType(Integer direction) { 
		   
		   return Arrays.stream(VoteType.values())
				        .filter(value -> value.getDirection() == direction)
				        .findAny()
				        .orElseThrow(() -> new SpringReddiusException("Vote not found"));
	}
}
