package com.reddius.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.reddius.dto.PostRequest;
import com.reddius.dto.PostResponse;
import com.reddius.model.Post;
import com.reddius.model.Subreddius;
import com.reddius.model.User;

@Mapper(componentModel = "spring")
public interface PostMapper {

	@Mapping(target="id", source="postRequest.postId")
	@Mapping(target="user", source="user")
	@Mapping(target="subreddius", source="subreddius")
	@Mapping(target="description", source="postRequest.description")
	@Mapping(target="createdDate", expression = "java(java.time.Instant.now())")
	Post mapPostRequestToPostEntity(PostRequest postRequest, Subreddius subreddius, User user);
	
	@Mapping(target="postId", source="id")
	@Mapping(target="userName", source="user.username")
	@Mapping(target="subreddiusName", source="subreddius.name")
	@Mapping(target="description", source="description")
	PostResponse mapToDto(Post post);
}
