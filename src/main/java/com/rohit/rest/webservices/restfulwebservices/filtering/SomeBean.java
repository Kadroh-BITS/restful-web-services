package com.rohit.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;

//@JsonIgnoreProperties(value= {"field3"})
@JsonFilter("SomeBeanFilter")
public class SomeBean {
	private String field1;
	private String field2;
	
	//Static Filtering
	//I don't want to send this field to response. This will ignore tthe filed whether 
	//you are sendng one bean as a response or list of beans
	//At field level you can use @JsonIgnore. At class level you can use @JsonIgnoreProperties
	//@JsonIgnore
	private String field3;
	
	
	//Dynamic Filtering
	//In Static filtering we have fixed that field3 should be ignored. But what if you want to 
	//ignore different fields for different requests
	//@JsonFilter helps in dynamic filtering. 
	//In our example, I have defined filters in the FilterController.java
	public SomeBean(String field1, String field2, String field3) {
		super();
		this.field1 = field1;
		this.field2 = field2;		
		this.field3 = field3;
	}

	

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}
	
	
	
	
	

}
