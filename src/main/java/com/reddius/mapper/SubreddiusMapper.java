package com.reddius.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import com.reddius.dto.SubreddiusDto;
import com.reddius.model.Post;
import com.reddius.model.Subreddius;
import com.reddius.model.User;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.WARN)
public interface SubreddiusMapper {

	@Mapping(target = "numberOfPosts", expression="java(qtyPosts(subreddius.getPosts()))")
	@Mapping(target = "subreddiusName", source="name")
	@Mapping(target="userid", expression="java(getUserId(subreddius.getUser()))")
	SubreddiusDto mapSubreddiusToDto(Subreddius subreddius);
	
	default Integer qtyPosts(List<Post> posts){
		    return posts.size();
	}
	
	default String getUserId(User user) {
		    return String.valueOf( user.getId() );
	}

}
