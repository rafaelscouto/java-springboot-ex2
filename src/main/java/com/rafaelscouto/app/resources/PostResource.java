package com.rafaelscouto.app.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rafaelscouto.app.domain.Post;
import com.rafaelscouto.app.resources.util.URL;
import com.rafaelscouto.app.services.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {
	
	@Autowired
	private PostService service;

	// @RequestMapping(method=RequestMethod.GET)
	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
		Post obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	// @RequestMapping(method=RequestMethod.GET)
	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<Post>> findByTitle(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/titlesearch2")
	public ResponseEntity<List<Post>> findByTitleUQ(@RequestParam(value="text", defaultValue="") String text) {
		text = URL.decodeParam(text);
		List<Post> list = service.findByTitle(text);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/findcomplete")
	public ResponseEntity<List<Post>> findComplete(
			@RequestParam(value="text", defaultValue="") String text,
			@RequestParam(value="iDate", defaultValue="") String iDate,
			@RequestParam(value="fDate", defaultValue="") String fDate) {
		text = URL.decodeParam(text);
		Date i = URL.convertDate(iDate, new Date(0L));
		Date f = URL.convertDate(fDate, new Date());
		List<Post> list = service.findComplete(text, i, f);
		return ResponseEntity.ok().body(list);
	}
}
