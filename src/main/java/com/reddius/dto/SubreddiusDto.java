package com.reddius.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubreddiusDto {

	private Long id;
	private String subreddiusName;
	private String description;
	private String userid;
	private Integer numberOfPosts;
	private String username;
}
