package com.reddius.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reddius.dto.SubreddiusDto;
import com.reddius.model.Subreddius;
import com.reddius.repository.SubreddiusRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class SubreddiusService {
	
	private final SubreddiusRepository subreddiusRepository;
	
	@Transactional
	public SubreddiusDto saveReddius(SubreddiusDto subreddiusDto) {
	       Subreddius savedSubreddius = subreddiusRepository.save(mapSubreddiusRequest(subreddiusDto));
	       subreddiusDto.setId(savedSubreddius.getId());
	       return subreddiusDto;
	}

	private Subreddius mapSubreddiusRequest(SubreddiusDto subreddiusReq) {
		return Subreddius.builder()
					     .name(subreddiusReq.getSubreddiusName())
					     .description(subreddiusReq.getDescription())
					     .build();        
	}
	
	@Transactional(readOnly = true)
	public List<SubreddiusDto> getAll() {
		   subreddiusRepository.findAll().stream().map((Function<? super Subreddius, ? extends SubreddiusDto>) (subreddius) -> {
			
			return SubreddiusDto.builder()
					            .id(subreddius.getId())
					            .subreddiusName(subreddius.getName())
					            .description(subreddius.getDescription())
					            .numberOfPosts(subreddius.getPosts().size())
					            .build();	
			
		   }).collect(Collectors.toList());
		   
		return null;
	}

}
