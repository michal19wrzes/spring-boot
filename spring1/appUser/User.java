package pl.test1.spring1.appUser;

import java.util.Collection;
import java.util.Collections;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@Entity
@Table(name = "User")
@NoArgsConstructor
@EqualsAndHashCode
public class User implements UserDetails{
	@Id
	@SequenceGenerator(name="sequence_generator", sequenceName = "sequence_generator", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequence_generator")
	private int id;
	private String userName;
	private String lastName;
	private String email;
	private String password;
	private boolean enabled = false;
	@Enumerated(EnumType.STRING)
	private UserRole userRole;
	
	
	
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
		return Collections.singletonList(authority);
	}
	@Override
	public String getUsername() {		
		return email;
	}
	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {		
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {		
		return true;
	}
	@Override
	public String getPassword() {	
		return password;
	}
	@Override
	public boolean isEnabled() {
		
		return enabled;
	}
	public String getUserName() {
		return userName;
	}
	public User(String userName, String lastName, String email, String password,  UserRole userRole) {
		super();
		this.userName = userName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.userRole = userRole;
	}
	
	

}
