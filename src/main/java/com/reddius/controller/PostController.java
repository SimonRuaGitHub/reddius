package com.reddius.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reddius.dto.PostRequest;
import com.reddius.dto.PostResponse;
import com.reddius.service.PostService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/posts")
public class PostController {

	private PostService postService;

	@Value("${pages.max.size}")
	private Long maxSizePage;

	@Autowired
	public PostController(final PostService postService){
		   this.postService = postService;
	}

	@PostMapping
	public ResponseEntity createPost(@RequestBody PostRequest postRequest) {
		   postService.savePost(postRequest);
		   return ResponseEntity.status(HttpStatus.CREATED)
				                .build();
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<PostResponse>> getAllPosts(){
		   return ResponseEntity.status(HttpStatus.OK)
				                .body( postService.getAllPosts() );
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PostResponse> getPostById(@PathVariable long id){
		   return ResponseEntity.status(HttpStatus.OK)
				                .body( postService.getPostById(id) );
	}
	
	@GetMapping("/by-subreddius/{id}")
	public ResponseEntity<List<PostResponse>> getPostsBySubreddius(@PathVariable long id){
		   return ResponseEntity.status(HttpStatus.OK)
				                .body( postService.getPostsBySubreddius(id) );
	}
	
	@GetMapping("/by-username/{username}")
	public ResponseEntity<List<PostResponse>> getPostsByUserName(@PathVariable String username){
		   return ResponseEntity.status(HttpStatus.OK)
				                .body( postService.getPostsByUserName(username) );
	}

	@GetMapping("/page/{page}")
	public ResponseEntity<Page<PostResponse>> getPostsByPage(@PathVariable Integer page){
		   return ResponseEntity.status(HttpStatus.OK)
				                .body( postService.getAllPostByPage(PageRequest.of(page, maxSizePage.intValue())) );
	}
}
