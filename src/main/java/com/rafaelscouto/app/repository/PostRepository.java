package com.rafaelscouto.app.repository;

import java.util.Date;
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
	
	@Query("{ $and: [ { date: {$gte: ?1} }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } }, { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> findComplete(String text, Date iDate, Date fDate);
}
