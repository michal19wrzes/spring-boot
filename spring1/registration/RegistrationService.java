package pl.test1.spring1.registration;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import pl.test1.spring1.appUser.UserDetailsService;
import pl.test1.spring1.appUser.User;
import pl.test1.spring1.appUser.UserRole;

@Service
@AllArgsConstructor
public class RegistrationService {
	
	private final UserDetailsService userDetailsService;
	private final EmailValidator emailValidator;

	public String register(RegistrationRequest request) {
		boolean isValidEmail = emailValidator.test(request.getEmail());
		if(!isValidEmail) {
			throw new IllegalStateException("Email is not valid");
		}
			
		return userDetailsService.signUpUser(
				new User(
						request.getUserName(),
						request.getLastName(),
						request.getEmail(),
						request.getPassword(),
						UserRole.USER
				));
	}

}
