package com.reddius.mapper;

import com.reddius.dto.CommentRequest;
import com.reddius.dto.CommentResponse;
import com.reddius.model.Comment;
import com.reddius.model.Post;
import com.reddius.model.User;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_211 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment map(CommentRequest commentRequest, Post post, User user) {
        if ( commentRequest == null && post == null && user == null ) {
            return null;
        }

        Comment comment = new Comment();

        if ( commentRequest != null ) {
            comment.setText( commentRequest.getText() );
        }
        if ( post != null ) {
            comment.setPost( post );
        }
        if ( user != null ) {
            comment.setUser( user );
        }
        comment.setCreatedDate( java.time.Instant.now() );

        return comment;
    }

    @Override
    public CommentResponse mapToResponse(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentResponse commentResponse = new CommentResponse();

        Long id = commentPostId( comment );
        if ( id != null ) {
            commentResponse.setPostid( id );
        }
        Long id1 = commentUserId( comment );
        if ( id1 != null ) {
            commentResponse.setUserid( id1 );
        }
        commentResponse.setText( comment.getText() );
        commentResponse.setId( comment.getId() );

        commentResponse.setDuration( getRelativeTimeAgo(comment.getCreatedDate()) );

        return commentResponse;
    }

    private Long commentPostId(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        Post post = comment.getPost();
        if ( post == null ) {
            return null;
        }
        Long id = post.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long commentUserId(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        User user = comment.getUser();
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
