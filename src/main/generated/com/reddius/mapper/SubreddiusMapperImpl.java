package com.reddius.mapper;

import com.reddius.dto.SubreddiusDto;
import com.reddius.model.Subreddius;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 1.8.0_211 (Oracle Corporation)"
)
@Component
public class SubreddiusMapperImpl implements SubreddiusMapper {

    @Override
    public SubreddiusDto mapSubreddiusToDto(Subreddius subreddius) {
        if ( subreddius == null ) {
            return null;
        }

        SubreddiusDto subreddiusDto = new SubreddiusDto();

        subreddiusDto.setSubreddiusName( subreddius.getName() );
        subreddiusDto.setId( subreddius.getId() );
        subreddiusDto.setDescription( subreddius.getDescription() );

        subreddiusDto.setNumberOfPosts( qtyPosts(subreddius.getPosts()) );
        subreddiusDto.setUserid( getUserId(subreddius.getUser()) );
        subreddiusDto.setUsername( subreddius.getUser().getUsername() );

        return subreddiusDto;
    }
}
