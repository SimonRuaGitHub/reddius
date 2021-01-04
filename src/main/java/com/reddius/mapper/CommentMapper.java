package com.reddius.mapper;

import java.time.Instant;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.github.marlonlom.utilities.timeago.TimeAgo;
import com.reddius.dto.CommentDto;
import com.reddius.dto.CommentRequest;
import com.reddius.dto.CommentResponse;
import com.reddius.model.Comment;
import com.reddius.model.Post;
import com.reddius.model.User;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target="id", ignore = true)
    @Mapping(target="text", source="commentRequest.text")
    @Mapping(target="post", source="post")
    @Mapping(target="user", source="user")
    @Mapping(target="createdDate", expression="java(java.time.Instant.now())")
    Comment map(CommentRequest commentRequest, Post post, User user);
    
    @Mapping(target="postid", source="post.id")
    @Mapping(target="userid", source="user.id")
    @Mapping(target="duration", expression="java(getRelativeTimeAgo(comment.getCreatedDate()))")
    CommentResponse mapToResponse(Comment comment);
    
    default String getRelativeTimeAgo(Instant createdDate) {
    	   return TimeAgo.using(createdDate.toEpochMilli());
    }
}
