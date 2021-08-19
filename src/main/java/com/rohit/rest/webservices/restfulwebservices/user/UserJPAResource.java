package com.rohit.rest.webservices.restfulwebservices.user;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class UserJPAResource {
	//retrieveAllUSers
				
		@Autowired
		private UserRepository userRepository;
		
		@Autowired
		private PostRepository postRepository;
		
		
		@GetMapping("/jpa/users")
		public List<User> retrieveAllUsers(){
			return userRepository.findAll(); 
		}
		
		//retrieveUser(int id)
		@GetMapping("/jpa/users/{id}")
		public EntityModel<User> retrieveUser(@PathVariable int id) {
			Optional<User> user =  userRepository.findById(id);
			if(!user.isPresent()) {
				throw new UserNotFoundException("id-" +id);
			}
			EntityModel<User> model = EntityModel.of(user.get());
			
			
			//Hateoas framework allows you to return data along with links. 
			//In this example it encapsulates user object and the link to retrieveAllUsers()
			//inside the EntityModel object and returns the EntityModel object
			
			//Thus in general, Hateoas allows you to return data along with the actions	
			
			WebMvcLinkBuilder linkToUsers = 
					linkTo(methodOn(this.getClass()).retrieveAllUsers());
			model.add(linkToUsers.withRel("all-users"));
			return model;
		}
		
		
//		input - details of user
		// output - CREATED and Return the created URI
		@PostMapping("/jpa/users")
		public ResponseEntity<Object> createUSer(@Valid @RequestBody User user) {
			User savedUser = userRepository.save(user);
			
			//Following creates URI of the new resource that we have created
			URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(savedUser.getId()).toUri();
			return ResponseEntity.created(location).build();
		}
		
		@DeleteMapping("/jpa/users/{id}")
		public void deleteUser(@PathVariable int id) {
			userRepository.deleteById(id);
			
		}
		
		@GetMapping("jpa/users/{id}/posts")
		public List<Post> retrievePosts(@PathVariable int id){
			Optional<User> userOptional = userRepository.findById(id);
			if(!userOptional.isPresent()) {
				throw new UserNotFoundException("id-"+id);
			}			
			return userOptional.get().getPosts();			
		}	
		
		@PostMapping("jpa/users/{id}/posts")
		public ResponseEntity<Object> createPosts(@PathVariable int id, @RequestBody Post post){
			Optional<User> userOptional = userRepository.findById(id);
			if(!userOptional.isPresent()) {
				throw new UserNotFoundException("id-"+id);
			}
			User user = userOptional.get();
			post.setUser(user);
			postRepository.save(post);
			
			//Following creates URI of the new resource that we have created
			URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(post.getId()).toUri();
			return ResponseEntity.created(location).build();		
		}

}
