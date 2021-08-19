package com.rohit.rest.webservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {
	
	//Versioning can be done via following ways
	
	//Method 1- using different URIs
	//URI versioning
	//Used by Twitter
	@GetMapping("v1/person")
	public PersonV1 personV1() {
		return new PersonV1("Rohit Kadam");
		
	}	
	@GetMapping("v2/person")
	public PersonV2 personV2() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}
	
	//Method 2= using different params
	//Request Parameter Versioning
	//Used by Amazon
	@GetMapping(value="/person/param", params="version=1")
	public PersonV1 paramV1() {
		return new PersonV1("Tango Charlie");
	}
	
	@GetMapping(value= "/person/param", params= "version=2")
	public PersonV2 paramV2() {
		return new PersonV2(new Name("Tango", "Charlie"));
	}
	
	
	//Method 3= using different header params
	//Aka Header versioning
	//Used in MicroSoft
	@GetMapping(value="/person/header", headers="X-API-VERSION=1")
	public PersonV1 headerV1() {
		return new PersonV1("Tango Charlie");
	}	
	@GetMapping(value= "/person/header", headers= "X-API-VERSION=2")
	public PersonV2 headerV2() {
		return new PersonV2(new Name("Tango", "Charlie"));
	}
	
	//Method 4 = Using produces(i.e. The Accept field in the header)
	//Aks Accept Header Versioning or Media-Type Versioning
	//Used in gitHub
	@GetMapping(value="/person/produces", produces="application/vnd.company.app-v1+json")
	public PersonV1 producesV1() {
		return new PersonV1("Tango Charlie");
	}	
	@GetMapping(value= "/person/produces", produces= "application/vnd.company.app-v2+json")
	public PersonV2 producesV2() {
		return new PersonV2(new Name("Tango", "Charlie"));
	}
	
	/*
	 * Factors to consider while deciding which method to use?
	 * 1. URI pollution- Method1 and 2 pollute the URI
	 * 2. Misuse of Http Headers- Method 3 and 4 pollute the header
	 * 3. Caching becomes difficult when headers are involved
	 * 4. Can we execute the requests using browser? 1 and 2= Yes. 3 and 4 = No.
	 * 5. how is easy is the API Documentation. 1 and 2, the auto-generation of documentation is easy
	 * 6. No perfect solution
	 * 
	 */
	
	
}
