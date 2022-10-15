package com.example.springbootsecurity;

import com.example.springbootsecurity.model.Usuario;
import com.example.springbootsecurity.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;
import org.thymeleaf.spring5.SpringTemplateEngine;

@SpringBootApplication
public class SpringBootSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSecurityApplication.class, args);
	}

	// Permite ejecutar código con interfaz funcional
	// Se ejecuta después de que se haya creado el contexto de app y antes de que se ejecute la app principal
	// Es como un import.sql
	@Bean
	CommandLineRunner commandLineRunner(UsuarioRepository usuarios, PasswordEncoder encoder) {
		return args -> {

			usuarios.save(new Usuario("user", encoder.encode("password"), "ROLE_USER"));
			usuarios.save(new Usuario("admin", encoder.encode("password"), "ROLE_USER,ROLE_ADMIN"));

		};
	}

}
