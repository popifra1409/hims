package co.iaf.service.security.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.SecurityFilterChain;

import co.iaf.entity.authentification.Utilisateur;
import co.iaf.service.authentification.AccountService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private AccountService accountService;

	public SecurityConfig(AccountService accountService) {
		this.accountService = accountService;
	}

	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		// stopper la génération du token csrf
		http.csrf().disable();
		// authentification stateless
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		//use form login
		//http.formLogin();
		http.authorizeRequests().antMatchers(HttpMethod.GET).permitAll();
		//http.authorizeRequests().anyRequest().authenticated();

		return http.build();

	}


	@Bean
	public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
		AuthenticationManagerBuilder authenticationManagerBuilder = http
				.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.userDetailsService(new UserDetailsService() {

			@Override
			public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
				Utilisateur utilisateur = accountService.loadUserByUserName(username);
				Collection<GrantedAuthority> authorities = new ArrayList<>();
				utilisateur.getRoles().forEach(r -> {
					authorities.add(new SimpleGrantedAuthority(r.getRoleName()));
				});
				return new User(utilisateur.getUsername(), utilisateur.getPassword(), authorities);
			}
		});

		return authenticationManagerBuilder.build();
	}
}
