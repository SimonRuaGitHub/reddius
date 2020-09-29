package com.reddius.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.reddius.dto.VoteDto;
import com.reddius.model.Post;
import com.reddius.model.User;
import com.reddius.model.Vote;

@Mapper(componentModel = "spring")
public interface VoteMapper {

    @Mapping(target="id", ignore = true)
    @Mapping(target="voteType", source="voteDto.voteType")
    @Mapping(target="post", source="post")
    @Mapping(target="user", source="user")
	Vote map(VoteDto voteDto, Post post, User user);
}
