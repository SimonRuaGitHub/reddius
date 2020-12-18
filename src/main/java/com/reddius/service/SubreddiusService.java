package com.reddius.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reddius.dto.SubreddiusDto;
import com.reddius.mapper.SubreddiusMapper;
import com.reddius.model.Subreddius;
import com.reddius.repository.SubreddiusRepository;
import com.reddius.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class SubreddiusService {
	
	private final SubreddiusRepository subreddiusRepository;
	private final UserRepository userRepository;
	private final SubreddiusMapper subreddiusMapper;
	
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
					     .user( userRepository.findByUsername(subreddiusReq.getUsername()).get() )
					     .build();        
	}
	
	@Transactional(readOnly = true)
	public List<SubreddiusDto> getAll() {
		    
		    return subreddiusRepository.findAll().stream().map(subreddiusMapper::mapSubreddiusToDto).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public SubreddiusDto getSubreddiusById(Long id) {
		   return subreddiusRepository.findById(id).map(subreddiusMapper::mapSubreddiusToDto).get();
	}

}
