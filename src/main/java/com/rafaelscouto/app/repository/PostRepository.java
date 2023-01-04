package com.rafaelscouto.app.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.rafaelscouto.app.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	// query methods - spring data
	List<Post> findByTitleContainingIgnoreCase(String text);
	
	// query regex - mongo
	@Query("{ 'title': { $regex: ?0, $option: 'i' } }")
	List<Post> findByTitleUsingQuery(String text);
}
