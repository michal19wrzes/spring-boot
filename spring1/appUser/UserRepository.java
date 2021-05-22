package pl.test1.spring1.appUser;

import java.util.Optional;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail (String email);

}
