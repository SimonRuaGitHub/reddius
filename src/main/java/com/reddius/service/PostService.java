package com.reddius.service;

import java.time.Instant;
import java.util.List;

import org.springframework.stereotype.Service;

import com.reddius.dto.PostRequest;
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
	
	public void savePost(PostRequest postReq) {
		   postRepository.save(mapDtoToPost(postReq));
	}
	
	public Post mapDtoToPost(PostRequest postReq) {
		   return Post.builder()
				      .id(postReq.getPostId())
				      .postName(postReq.getPostName())
				      .url(postReq.getUrl())
				      .description(postReq.getDescription())
				      .createdDate(Instant.now() )
				      .user( userRepository.findById(postReq.getUserid()).get() )
				      .subreddius( subreddiusRepository.findById(postReq.getSubreddiusid()).get() )
				      .build();
	}

	public List<Post> getAllPosts(){
		   return null;
	}
}
