package com.reddius.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.reddius.dto.CommentDto;
import com.reddius.dto.CommentRequest;
import com.reddius.dto.CommentResponse;
import com.reddius.exceptions.PostNotFoundException;
import com.reddius.exceptions.SpringReddiusException;
import com.reddius.mapper.CommentMapper;
import com.reddius.model.Post;
import com.reddius.model.User;
import com.reddius.repository.CommentRepository;
import com.reddius.repository.PostRepository;
import com.reddius.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentMapper commentMapper;
   
    @Transactional
    public void saveComment(CommentRequest commentReq) {
    	
    	commentRepository.save(
    			                commentMapper.map(commentReq,
    			                		          postRepository.findById( commentReq.getPostid() ).get(), 
    			                		          userRepository.findById( commentReq.getUserid() ).get())
    			               );
    	
    }

    @Transactional(readOnly = true)
	public List<CommentResponse> findCommentsByPost(long postid) {
		
		   Post post = postRepository.findById(postid).orElseThrow(() -> new PostNotFoundException(postid));
		   
		   List<CommentResponse> comments = commentRepository.findAllByPost(post)
								                           .orElseThrow(() -> new SpringReddiusException("the post doesn't have any comments "+postid))
								                           .stream()
								                           .map(commentMapper::mapToResponse)
								                           .collect(Collectors.toList());
				                            
	       return comments;
	}

    @Transactional(readOnly = true)
	public List<CommentResponse> findCommentsByUserName(String username) {
		
		   User user = userRepository.findByUsername(username).orElseThrow(() -> new SpringReddiusException("User not found using username: "+username));
		   
		   return commentRepository.findAllCommentsByUser(user)
		                           .orElseThrow(() -> new SpringReddiusException("the user doesn't have any comments "+username))
		                           .stream()
		                           .map(commentMapper::mapToResponse)
		                           .collect(Collectors.toList());
	}
}
