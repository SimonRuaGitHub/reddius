package com.reddius.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reddius.dto.VoteDto;
import com.reddius.exceptions.PostNotFoundException;
import com.reddius.exceptions.SpringReddiusException;
import com.reddius.mapper.VoteMapper;
import com.reddius.model.Post;
import com.reddius.model.User;
import com.reddius.model.Vote;
import com.reddius.model.VoteType;
import com.reddius.repository.PostRepository;
import com.reddius.repository.UserRepository;
import com.reddius.repository.VoteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VoteService {
	
	private final VoteRepository voteRepository;
	private final PostRepository postRepository;
	private final AuthService authService; //instead of using userRepository to find the user object throught the corresponding id
	private final VoteMapper voteMapper;

	@Transactional
	public void vote(VoteDto voteDto) {
		   
		    Post post = postRepository.findById( voteDto.getPostid() )
		    		                  .orElseThrow(() -> new PostNotFoundException( String.valueOf( voteDto.getPostid() ) ));
		    
		    voteRepository.save( consolidateVoteToSave(post, authService.getCurrentUser(), voteDto) );
	}
	
	private Vote consolidateVoteToSave(Post post, User user, VoteDto voteDto) {
		
		 Optional<Vote> voteByPostAndUser = voteRepository.findByPostAndUser(post, user);
   	  
 	    if(voteByPostAndUser.isPresent()) {
 	    	
 	    	if( voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType()) )
 	           throw new SpringReddiusException("You have already vote in same direction "+voteDto.getVoteType());
 	    	
 	    	else {
 	    		
 	    	     voteByPostAndUser.get().setVoteType(voteDto.getVoteType());
 	    	     return voteByPostAndUser.get();
 	    	}
 	    	
 	    }	
 	    else 
 	    	 return voteMapper.map(voteDto, post,  user);
	}


}
