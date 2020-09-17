package com.reddius.service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.reddius.dto.PostRequest;
import com.reddius.dto.PostResponse;
import com.reddius.exceptions.PostNotFoundException;
import com.reddius.exceptions.SpringReddiusException;
import com.reddius.exceptions.SubreddiusNotFoundException;
import com.reddius.mapper.PostMapper;
import com.reddius.model.Post;
import com.reddius.model.Subreddius;
import com.reddius.model.User;
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
	
	public PostResponse getPostById(long id) {
		
		   Post post = postRepository.findById(id).orElseThrow(() -> new PostNotFoundException(id));
		    
		   return postMapper.mapToDto(post);
	}
	
	public List<PostResponse> getPostsBySubreddius(long subreddiusId) {
		
		   Subreddius subreddius = subreddiusRepository.findById(subreddiusId).orElseThrow(() -> new SubreddiusNotFoundException(subreddiusId));
		   
		   return postRepository.findAllBySubreddius(subreddius).stream().map(postMapper::mapToDto).collect(Collectors.toList());
	}
	
	public List<PostResponse> getPostsByUserName(String username) {
		
		   User user = userRepository.findByUsername(username).orElseThrow(() -> new SpringReddiusException("Couldn't find user by username: "+username));
		   
		   return postRepository.findAllByUser(user).stream().map(postMapper::mapToDto).collect(Collectors.toList());
	}
	
}
