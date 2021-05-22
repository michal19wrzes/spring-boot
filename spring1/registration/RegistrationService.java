package pl.test1.spring1.registration;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import pl.test1.spring1.UserDetailsService;
import pl.test1.spring1.models.User;
import pl.test1.spring1.models.UserRole;

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
