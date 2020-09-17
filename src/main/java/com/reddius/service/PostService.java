package com.reddius.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.reddius.dto.PostRequest;
import com.reddius.dto.PostResponse;
import com.reddius.mapper.PostMapper;
import com.reddius.model.Post;
import com.reddius.repository.PostRepository;
import com.reddius.repository.SubreddiusRepository;
import com.reddius.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class PostService {
	
	private final PostRepository postRepository;
	private final UserRepository userRepository;
	private final SubreddiusRepository subreddiusRepository;
	private final PostMapper postMapper;
	
	public void savePost(PostRequest postReq) {
		   postRepository.save(
				                postMapper.mapPostRequestToPostEntity( postReq, 
				                		                               subreddiusRepository.findById(postReq.getSubreddiusid()).get(), 
				                		                               userRepository.findById(postReq.getUserid()).get() )
				               );
	}

	public List<PostResponse> getAllPosts(){
		   return postRepository.findAll().stream().map(postMapper::mapToDto).collect(Collectors.toList());
	}
}
