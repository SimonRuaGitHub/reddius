package com.reddius.mapper;

import com.reddius.dto.PostRequest;
import com.reddius.dto.PostResponse;
import com.reddius.model.Post;
import com.reddius.model.Subreddius;
import com.reddius.model.User;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_211 (Oracle Corporation)"
)
@Component
public class PostMapperImpl extends PostMapper {

    @Override
    public Post mapPostRequestToPostEntity(PostRequest postRequest, Subreddius subreddius, User user) {
        if ( postRequest == null && subreddius == null && user == null ) {
            return null;
        }

        Post post = new Post();

        if ( postRequest != null ) {
            post.setDescription( postRequest.getDescription() );
            post.setPostName( postRequest.getPostName() );
            post.setUrl( postRequest.getUrl() );
        }
        if ( subreddius != null ) {
            post.setSubreddius( subreddius );
        }
        if ( user != null ) {
            post.setUser( user );
        }
        post.setCreatedDate( java.time.Instant.now() );

        return post;
    }

    @Override
    public PostResponse mapToDto(Post post) {
        if ( post == null ) {
            return null;
        }

        PostResponse postResponse = new PostResponse();

        postResponse.setDescription( post.getDescription() );
        postResponse.setSubreddiusName( postSubreddiusName( post ) );
        if ( post.getId() != null ) {
            postResponse.setPostId( post.getId() );
        }
        postResponse.setUserName( postUserUsername( post ) );
        Long id = postUserId( post );
        if ( id != null ) {
            postResponse.setUserId( id );
        }
        postResponse.setPostName( post.getPostName() );
        postResponse.setUrl( post.getUrl() );

        postResponse.setDuration( getRelativeTimeAgo(post) );
        postResponse.setVoteTypeOfUser( getUserVoteOnPost(post) );
        postResponse.setVoteCount( countVotes(post) );
        postResponse.setCommentCount( countComments(post) );

        return postResponse;
    }

    private String postSubreddiusName(Post post) {
        if ( post == null ) {
            return null;
        }
        Subreddius subreddius = post.getSubreddius();
        if ( subreddius == null ) {
            return null;
        }
        String name = subreddius.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String postUserUsername(Post post) {
        if ( post == null ) {
            return null;
        }
        User user = post.getUser();
        if ( user == null ) {
            return null;
        }
        String username = user.getUsername();
        if ( username == null ) {
            return null;
        }
        return username;
    }

    private Long postUserId(Post post) {
        if ( post == null ) {
            return null;
        }
        User user = post.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
