package com.rafaelscouto.app.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafaelscouto.app.domain.Post;
import com.rafaelscouto.app.repository.PostRepository;
import com.rafaelscouto.app.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;
	
	public Post findById(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(id));
	}
	
	public List<Post> findByTitle(String text) {
		return repo.findByTitleContainingIgnoreCase(text);
	}
	
	public List<Post> findByTitleUQ(String text) {
		return repo.findByTitleContainingIgnoreCase(text);
	}
	
	public List<Post> findComplete(String text, Date iDate, Date fDate) {
		fDate = new Date(fDate.getTime() + 24 * 60 * 60 * 1000);
		return repo.findComplete(text, iDate, fDate);
	}
}
