package com.reddius.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reddius.dto.SubreddiusDto;
import com.reddius.service.SubreddiusService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/subreddius")
@AllArgsConstructor
@Slf4j
public class SubreddiusController {
	
	private final SubreddiusService subreddiusService;

	@PostMapping
	public ResponseEntity<SubreddiusDto> createSubreddit(@RequestBody SubreddiusDto subreddiusDto) {	
		   return ResponseEntity.status(HttpStatus.CREATED)
				                .body(subreddiusService.saveReddius(subreddiusDto));
	}
	
	@GetMapping
	public ResponseEntity<List<SubreddiusDto>> getAllsubreddius(){
		   return ResponseEntity.status(HttpStatus.OK)
				                .body(subreddiusService.getAll());
	}
	
}
