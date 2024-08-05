package snaplogic.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * Spring Security configuration.  This is to show how to configure the OAuth2 (using Github), and a form login that 
 * can be backed by whatever we choose.  Ie MongoDB.
 * @author bgoff
 * @since 5 Aug 2024
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig
{

	/**
	 * Define a Password Encoder for security reasons.
	 * @return PasswordEncoder to use to encode / decode passwords.
	 */
	@Bean
	PasswordEncoder passwordEncoder() { 
	    return new BCryptPasswordEncoder(); 
	}
	
	/**
	 * For demo purpose defile an in memory UserDetailService.  This can be swapped out for a UserDetailService backed
	 * by our mongodb in the future.
	 * @return InMemoryUserDetailsManager.
	 */
	@Bean
	InMemoryUserDetailsManager userDetailsService() 
	{
		UserDetails user1 = User.withUsername("wrgoff")
				.password(passwordEncoder().encode("wrgoff1"))
				.roles("USER")
				.build();
		
		return (new InMemoryUserDetailsManager(user1));
	}
	
	
	/**
	 * Defines the Security Filter chain will will use.
	 * @param http HttpSecurity to append the providers to.
	 * @return SecurityFilterChain with the appropriate security enabled.
	 * @throws Exception in the event we are unable to process the request.
	 */
	@Bean
	SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
		return http.authorizeHttpRequests(auth -> {
					auth.requestMatchers("/").permitAll();
					auth.anyRequest().authenticated();
				})
				.oauth2Login(withDefaults())
				.formLogin(withDefaults())
				.build();
	}
}
