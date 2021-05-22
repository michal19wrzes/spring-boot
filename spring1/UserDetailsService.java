package pl.test1.spring1;



import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import pl.test1.spring1.models.User;




@Service
@AllArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService{

private final UserRepository userRepository;
private final BCryptPasswordEncoder bCryptPasswordEncoder;

private final static String USER_NOT_FOUND = "Not found %s";
@Override
public UserDetails loadUserByUsername(String email) 
		throws UsernameNotFoundException {
	return  userRepository.findByEmail(email)
			.orElseThrow(() -> 
				new UsernameNotFoundException(
						String.format(USER_NOT_FOUND, email)));
	
}
public String signUpUser(User user) {
	boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();
	if(userExists) {
		throw new IllegalStateException(String.format("user with %s email is exist", user.getEmail()));
	}
	String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
	user.setPassword(encodedPassword);
	userRepository.save(user);
	// TODO Send confirmation TOKEN
	return "";
}
}
