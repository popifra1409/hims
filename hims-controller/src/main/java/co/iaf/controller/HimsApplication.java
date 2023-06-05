package co.iaf.controller;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import co.iaf.service.authentification.AccountService;

@SpringBootApplication
@PropertySource("classpath:co/iaf/controller/application.properties")
@ComponentScan(basePackages = { "co.iaf.*" })
@EntityScan(basePackages = { "co.iaf.entity.*" })
@EnableJpaRepositories(basePackages = { "co.iaf.*" })
public class HimsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HimsApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner start(AccountService accountService) {
		return args -> {
			// roles

			/*accountService.addNewRole(new Role(null, "USER"));
			accountService.addNewRole(new Role(null, "ADMIN"));
			accountService.addNewRole(new Role(null, "MANAGER"));

			// utlisateurs
			accountService.addNewUtilisateur(new Utilisateur(null, "popifra1409", null, "123456", new ArrayList<>()));
			accountService.addNewUtilisateur(new Utilisateur(null, "geraldin", null, "12345678", new ArrayList<>()));
			accountService.addNewUtilisateur(new Utilisateur(null, "nathan", null, "1234567", new ArrayList<>()));
			accountService.addNewUtilisateur(new Utilisateur(null, "herve", null, "123456789", new ArrayList<>()));
			accountService.addNewUtilisateur(new Utilisateur(null, "admin", null, "1234567890", new ArrayList<>()));

			// affectation des Roles aux utilisateurs
			accountService.addRoleToUser("popifra1409", "MANAGER");
			accountService.addRoleToUser("admin", "USER");
			accountService.addRoleToUser("admin", "ADMIN");
			accountService.addRoleToUser("herve", "MANAGER");
			accountService.addRoleToUser("nathan", "USER");
			accountService.addRoleToUser("geraldin", "USER");*/

		};
	}
}
