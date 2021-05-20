package pl.test1.spring1.registration;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
	private final String email;
	private final String password;
	private final String userName;
	private final String lastName;
}
