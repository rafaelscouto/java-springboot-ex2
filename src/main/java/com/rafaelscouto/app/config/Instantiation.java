package com.rafaelscouto.app.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.rafaelscouto.app.domain.User;
import com.rafaelscouto.app.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		userRepository.deleteAll();
		
		User u1 = new User(null, "Joao", "joao@teste.com.br");
		User u2 = new User(null, "Maria", "maria@teste.com.br");
		User u3 = new User(null, "Jose", "jose@teste.com.br");
		
		userRepository.saveAll(Arrays.asList(u1, u2, u3));
	}
}
