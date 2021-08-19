package com.rohit.rest.webservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	
	//retrieveAllUSers
	@Autowired
	private UserDaoService service;
	
	
	@GetMapping("/users")
	public List<User> retrieveAllUsers(){
		return service.findAll(); 
	}
	
	//retrieveUser(int id)
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user =  service.findOne(id);
		if(user ==null) {
			throw new UserNotFoundException("id-" +id);
		}
		EntityModel<User> model = EntityModel.of(user);
		
		
		//Hateoas framework allows you to return data along with links. 
		//In this example it encapsulates user object and the link to retrieveAllUsers()
		//inside the EntityModel object and returns the EntityModel object
		
		//Thus in general, Hateoas allows you to return data along with the actions	
		
		WebMvcLinkBuilder linkToUsers = 
				linkTo(methodOn(this.getClass()).retrieveAllUsers());
		model.add(linkToUsers.withRel("all-users"));
		return model;
	}
	
	
//	input - details of user
	// output - CREATED and Return the created URI
	@PostMapping("/users")
	public ResponseEntity<Object> createUSer(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		
		//Following creates URI of the new resource that we have created
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user =  service.deleteById(id);
		if(user ==null) {
			throw new UserNotFoundException("id-" +id);
		}		
	}
	
	
	
	

}
