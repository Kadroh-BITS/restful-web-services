package com.rohit.rest.webservices.restfulwebservices.user;

import java.util.Date;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

public class User {
	
	private Integer id;
	
	@Size(min=2, message ="Name should have atleast 2 characters")
	private String name;
	
	@Past
	private Date birtDate;
	
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
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birtDate=" + birtDate + "]";
	}
	
		
	
	
	

}
