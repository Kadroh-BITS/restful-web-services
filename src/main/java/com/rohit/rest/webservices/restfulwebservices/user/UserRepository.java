package com.rohit.rest.webservices.restfulwebservices.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
	//in the <> we mention that this JpaRepository implementation will manage User Entity
	// and that its primary key is Integer
	
	
}
