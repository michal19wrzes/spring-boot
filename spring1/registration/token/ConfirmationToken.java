package pl.test1.spring1.registration.token;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.test1.spring1.appUser.User;
@Getter
@Setter
@Entity
@NoArgsConstructor
public class ConfirmationToken {
	
	@Id
	@SequenceGenerator(name="token_sequence_generator", sequenceName = "token_sequence_generator", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "token_sequence_generator")
	private Long id;
	
	@Column(nullable = false)
	private String token;
	
	@Column(nullable = false)
	private LocalDateTime createdAt;
	
	@Column(nullable = false)
	private LocalDateTime expiredAt;
	
	private LocalDateTime confirmedAt;
	
	@ManyToOne
	@JoinColumn(
			nullable =false,
			name = "user_id")
	private User user;
	
	public ConfirmationToken(
			String token, 
			LocalDateTime createdAt,
			LocalDateTime expiredAt,
			User user
			
			) {
		super();
		this.token = token;
		this.createdAt = createdAt;
		this.expiredAt = expiredAt;
		this.user = user;
	}
	
	
	

}
