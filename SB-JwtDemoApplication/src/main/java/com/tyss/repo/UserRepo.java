package com.tyss.repo;

import java.util.Optional;




import org.springframework.data.jpa.repository.JpaRepository;

import com.tyss.entity.User;


//SUPPORT CLASSES (data related)

public interface UserRepo  extends JpaRepository<User, Integer> {
	
	 Optional<User> findByEmail(String email);

}
