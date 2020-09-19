package com.reddius.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reddius.dto.VoteDto;
import com.reddius.mapper.VoteMapper;
import com.reddius.repository.PostRepository;
import com.reddius.repository.UserRepository;
import com.reddius.repository.VoteRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class VoteService {
	
	private final VoteRepository voteRepository;
	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final VoteMapper voteMapper;

	@Transactional
	public void vote(VoteDto voteDto) {
		    voteRepository.save( voteMapper.map(voteDto, 
		    	                             	postRepository.findById( voteDto.getPostid() ).get(), 
		    		                            userRepository.findById( voteDto.getUserid() ).get()) 
		    		           );
	}

}
