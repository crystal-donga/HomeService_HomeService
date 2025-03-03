package com.example.HomeService.config;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Autowired
	private UserDetailsService userDetailsService;
	
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
	
  @Bean
  public SecurityFilterChain  securityFilterChain(HttpSecurity http) throws Exception {
	  return http.csrf(customizer -> customizer.disable()).
              authorizeHttpRequests(request -> request.requestMatchers("/auth/register", "/auth/login").permitAll()
            		                                                   .anyRequest().authenticated()).
              authenticationProvider(authenticationProvider()).                                     
              httpBasic(Customizer.withDefaults()).
              sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();
  }
  @Bean
  public AuthenticationProvider authenticationProvider() {
	  DaoAuthenticationProvider provider = new  DaoAuthenticationProvider();
	  provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
	  provider.setUserDetailsService(userDetailsService);
	  return provider;
  }
  
  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
      return config.getAuthenticationManager();

  }
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder(12);
  }
}
