package pl.test1.spring1.registration;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import pl.test1.spring1.appUser.UserDetailsService;
import pl.test1.spring1.Email.EmailSender;
import pl.test1.spring1.appUser.User;
import pl.test1.spring1.appUser.UserRole;
import pl.test1.spring1.registration.token.ConfirmationToken;
import pl.test1.spring1.registration.token.ConfirmationTokenService;

@Service
@AllArgsConstructor
public class RegistrationService {
	
	private final UserDetailsService userDetailsService;
	private final EmailValidator emailValidator;
	private final ConfirmationTokenService confirmationTokenService;
	private final EmailSender emailSender;

	public String register(RegistrationRequest request) {
		boolean isValidEmail = emailValidator.test(request.getEmail());
		if(!isValidEmail) {
			throw new IllegalStateException("Email is not valid");
		}
		
		String token = userDetailsService.signUpUser(
				new User(
						request.getUserName(),
						request.getLastName(),
						request.getEmail(),
						request.getPassword(),
						UserRole.USER
				));
		String link = "localhost:8080/api/v1/registration/confirm?token=" +token;
		emailSender.send(
				request.getEmail(),
				buildEmail(request.getUserName(),link));
		return token;
	}
	
	

	@Transactional
	public String confirmToken(String token) {
		ConfirmationToken confirmationToken = confirmationTokenService
				.getToken(token)
				.orElseThrow(() -> new IllegalStateException("Token not found"));
		
		if(confirmationToken.getConfirmedAt()!=null) {
			throw new IllegalStateException("Email already confirmed");
		}
		
		LocalDateTime expiredAt = confirmationToken.getExpiredAt();
		
		if(expiredAt.isBefore(LocalDateTime.now())) {
			throw new IllegalStateException("Token expired");
		}
		confirmationTokenService.setConfirmedAt(token);
		userDetailsService.enableUser(
				confirmationToken.getUser().getEmail());
		
		return "confirmed";
	}
	private String buildEmail(String name, String link) {
		// TODO template do zrobienia
		return String.format("Confirm your account %s", link);
	}

}
