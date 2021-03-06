package org.generation.HelloWorld.seguranca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private UserDetailsService userDetailsService;	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);				
	}
	
	@Bean
	public PasswordEncoder passwordEnconder() {
		return new BCryptPasswordEncoder();	
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
		.antMatchers("/usuario/logar").permitAll()
		.antMatchers("/**").permitAll()
		.antMatchers("/usuario/cadastrar").permitAll()
		.antMatchers("produto").permitAll()
		.antMatchers("produto/{id").permitAll()
		.antMatchers("produto/nome/{nome}").permitAll()
		.antMatchers(HttpMethod.GET,"/categoria").permitAll()
		.antMatchers(HttpMethod.POST,"/produto").permitAll()
		.antMatchers(HttpMethod.GET,"/categoria/tema/{tema}").permitAll()
		.antMatchers(HttpMethod.POST,"/categoria").permitAll()
		.antMatchers(HttpMethod.POST,"/categoria/tema/{tema}").permitAll()
		.antMatchers(HttpMethod.GET,"/categoria/{id}").permitAll()
		.anyRequest().authenticated()
		.and().httpBasic()
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().cors()
		.and().csrf().disable();
	}
}
