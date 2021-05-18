package pl.test1.spring1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.NoOp;

@EnableWebSecurity
public class SecurityConf extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserDetailsService userDetailsService; 
	
	//configure ctrl+space auth
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// configure http and delete super.configure after generated
		http.authorizeRequests()
		.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/user").hasRole("USER")
		.antMatchers("/").permitAll()
		.and().formLogin();
		
	}
	@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder gePasswordEncoder() {return NoOpPasswordEncoder.getInstance();}
	

}
