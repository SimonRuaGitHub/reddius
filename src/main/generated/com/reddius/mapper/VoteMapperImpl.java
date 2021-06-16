package com.reddius.mapper;

import com.reddius.dto.VoteDto;
import com.reddius.model.Post;
import com.reddius.model.User;
import com.reddius.model.Vote;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_211 (Oracle Corporation)"
)
@Component
public class VoteMapperImpl implements VoteMapper {

    @Override
    public Vote map(VoteDto voteDto, Post post, User user) {
        if ( voteDto == null && post == null && user == null ) {
            return null;
        }

        Vote vote = new Vote();

        if ( voteDto != null ) {
            vote.setVoteType( voteDto.getVoteType() );
        }
        if ( post != null ) {
            vote.setPost( post );
        }
        if ( user != null ) {
            vote.setUser( user );
        }

        return vote;
    }
}
