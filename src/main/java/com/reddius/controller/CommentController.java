package com.reddius.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reddius.dto.CommentDto;
import com.reddius.dto.CommentRequest;
import com.reddius.dto.CommentResponse;
import com.reddius.service.CommentService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/comments")
@AllArgsConstructor
public class CommentController {

	private final CommentService commentService;
	
	@PostMapping
	public ResponseEntity createComment(@RequestBody CommentRequest commReq) {
		   commentService.saveComment(commReq);
		   return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping("/by-postId/{postId}")
	public ResponseEntity<List<CommentResponse>> getCommentsByPost(@PathVariable(name = "postId") long postid) {
		   return ResponseEntity.status(HttpStatus.OK)
				                .body( commentService.findCommentsByPost(postid) );
	}
	
	@GetMapping("/by-user/{username}")
	public ResponseEntity<List<CommentResponse>> getCommentsByUserName(@PathVariable String username){
		   return ResponseEntity.status(HttpStatus.OK)
				                .body( commentService.findCommentsByUserName(username) );
	}
  }
