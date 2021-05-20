package pl.test1.spring1.models;

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
	private boolean enabled = true;
	@Enumerated(EnumType.STRING)
	private UserRole userRole;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.name());
		return Collections.singletonList(authority);
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return userName;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return enabled;
	}
	
	

}
