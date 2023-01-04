package com.rafaelscouto.app.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.rafaelscouto.app.domain.Post;
import com.rafaelscouto.app.domain.User;
import com.rafaelscouto.app.dto.AuthorDTO;
import com.rafaelscouto.app.repository.PostRepository;
import com.rafaelscouto.app.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		User u1 = new User(null, "Joao", "joao@teste.com.br");
		User u2 = new User(null, "Maria", "maria@teste.com.br");
		User u3 = new User(null, "Jose", "jose@teste.com.br");
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
		
		Post p1 = new Post(null, sdf.parse("02/01/2023"), "Title 01", "Content 01", new AuthorDTO(u1));
		Post p2 = new Post(null, sdf.parse("03/01/2023"), "Title 02", "Content 02", new AuthorDTO(u1));
		Post p3 = new Post(null, sdf.parse("02/01/2023"), "Title 03", "Content 03", new AuthorDTO(u2));
		
		postRepository.saveAll(Arrays.asList(p1, p2, p3));
	}
}
