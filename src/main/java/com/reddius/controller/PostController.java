package com.reddius.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
@AllArgsConstructor
public class PostController {
	
	private final PostService postService;

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

}
