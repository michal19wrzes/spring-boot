package pl.test1.spring1;



import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;




@Service
@AllArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

private final UserRepository userRepository;

private final static String USER_NOT_FOUND = "Not found %s";
@Override
public UserDetails loadUserByUsername(String email) 
		throws UsernameNotFoundException {
	return  userRepository.findByEmail(email)
			.orElseThrow(() -> 
				new UsernameNotFoundException(
						String.format(USER_NOT_FOUND, email)));
	
///	Optional<User> user = userRepository;findByEmail(email);
	
//	user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + email));
//	return user.map(MyUserDetails::new).get();
	
}
}
