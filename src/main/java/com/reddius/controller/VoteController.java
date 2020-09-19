package com.reddius.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reddius.dto.VoteDto;
import com.reddius.service.VoteService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/votes/")
@AllArgsConstructor
public class VoteController {

	private final VoteService voteService;
	
	@PostMapping
	public ResponseEntity<Void> vote(@RequestBody VoteDto voteDto){
           voteService.vote(voteDto);
		   return ResponseEntity.status(HttpStatus.CREATED).build();
	}
}
