package com.rohit.rest.webservices.restfulwebservices.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;


//With @Entity, now JPA will manage the User Entity
@Entity
public class User {
	@Id
	@GeneratedValue
	private Integer id;
	
	@Size(min=2, message ="Name should have atleast 2 characters")
	private String name;
	
	@Past
	private Date birtDate;
	
	@OneToMany(mappedBy="user")
	private List<Post> posts;
	
	
	protected User() {
		
	}
	
	public User(Integer id, String name, Date birtDate) {
		super();
		this.id = id;
		this.name = name;
		this.birtDate = birtDate;
	}

	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirtDate() {
		return birtDate;
	}
	public void setBirtDate(Date birtDate) {
		this.birtDate = birtDate;
	}
	
	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
		
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birtDate=" + birtDate + "]";
	}
	
	
	
	
	

}
