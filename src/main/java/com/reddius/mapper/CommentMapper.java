package com.reddius.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.reddius.dto.CommentDto;
import com.reddius.model.Comment;
import com.reddius.model.Post;
import com.reddius.model.User;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target="id", source="commentDto.id")
    @Mapping(target="text", source="commentDto.text")
    @Mapping(target="post", source="post")
    @Mapping(target="user", source="user")
    @Mapping(target="createdDate", expression="java(java.time.Instant.now())")
    Comment map(CommentDto commentDto, Post post, User user);
}
