package pl.test1.spring1;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

@Override
public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
	return new MyUserDetails(s);
	
}
}
