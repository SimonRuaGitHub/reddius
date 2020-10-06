package com.reddius.mapper;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import com.reddius.dto.PostRequest;
import com.reddius.dto.PostResponse;
import com.reddius.model.Comment;
import com.reddius.model.Post;
import com.reddius.model.Subreddius;
import com.reddius.model.User;
import com.reddius.repository.CommentRepository;
import com.reddius.repository.VoteRepository;
import com.github.marlonlom.utilities.timeago.TimeAgo;


@Mapper(componentModel = "spring")
public abstract class PostMapper {
	
	@Autowired
	private VoteRepository voteRepository;
	
	@Autowired
	private CommentRepository commentRepository;

	@Mapping(target="id", source="postRequest.postId")
	@Mapping(target="user", source="user")
	@Mapping(target="subreddius", source="subreddius")
	@Mapping(target="description", source="postRequest.description")
	@Mapping(target="createdDate", expression = "java(java.time.Instant.now())")
	public abstract Post mapPostRequestToPostEntity(PostRequest postRequest, Subreddius subreddius, User user);
	
	@Mapping(target="postId", source="id")
	@Mapping(target="userName", source="user.username")
	@Mapping(target="subreddiusName", source="subreddius.name")
	@Mapping(target="description", source="description")
	@Mapping(target="voteCount", expression="java(countVotes(post))")
	@Mapping(target="commentCount", expression ="java(countComments(post))")
	@Mapping(target="duration",  expression = "java(getRelativeTimeAgo(post))")
	public abstract PostResponse mapToDto(Post post);
	
	Integer countComments(Post post) {
		    return commentRepository.findAllByPost(post)
		    		                .orElse(Collections.emptyList()).size();
	}
	
	Integer countVotes(Post post) {
		    return voteRepository.findByPost(post).size();
	}
	
	String getRelativeTimeAgo(Post post) {
		    return TimeAgo.using(post.getCreatedDate().toEpochMilli());
	}
	
}
