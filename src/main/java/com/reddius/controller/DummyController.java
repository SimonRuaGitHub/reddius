package com.reddius.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dummy")
public class DummyController {

	@RequestMapping(path = "/wave-to-user",method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
	public String helloReddius() {
		   return "what's up dude";
	}
}
