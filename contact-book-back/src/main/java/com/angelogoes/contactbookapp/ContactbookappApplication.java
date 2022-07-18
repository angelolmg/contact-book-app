package com.angelogoes.contactbookapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.angelogoes.contactbookapp.model.Contact;
import com.angelogoes.contactbookapp.repository.ContactRepository;

@SpringBootApplication
public class ContactbookappApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactbookappApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(ContactRepository contactRepository) {
		return args -> {

			Contact c = new Contact();
			c.setName("Angular com Spring");
			c.setEmail("angelo@gmail.com");
			c.setPhone("1233456");
			
			contactRepository.save(c);
			
		};
	}

}
