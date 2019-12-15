package com.IRL.appformacioncontinua.auth;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
	/*
	// forma programatica de asignar permisos a roles otra forma seria con anotaciones 
	// eso lo haremosen el controlador 	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/api/cursos", "/api/cursos/page/**", "/api/uploads/img/**", "/images/**").permitAll()
		.antMatchers(HttpMethod.GET, "/api/cursos/{id}").hasAnyRole("USER", "ADMIN")	
		.antMatchers(HttpMethod.POST, "/api/cursos/upload").hasAnyRole("USER", "ADMIN")		
		.antMatchers(HttpMethod.POST, "/api/cursos").hasRole("ADMIN")
		.antMatchers("/api/cursos/**").hasRole("ADMIN")		
		.anyRequest().authenticated();		
	}
	*/
	
	
	// roles mediante anotaciones:
	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/api/cursos", "/api/cursos/page/**", "/api/uploads/img/**", "/images/**").permitAll()		
		.anyRequest().authenticated()		
		.and().cors().configurationSource(corsConfigurationSource());
	}
	 
	
	
	
	
	// metodo que permite el intercambio de recursos de origen cruzado que residen en distinto dominio
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Arrays.asList("Content-Type", "Authorization"));
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
	
	
	@Bean
	public FilterRegistrationBean<CorsFilter>  corsFilter(){
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
	
	
	

}
