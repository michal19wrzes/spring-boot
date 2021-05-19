package pl.test1.spring1;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



import pl.test1.spring1.models.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByUserName (String userName);

}
