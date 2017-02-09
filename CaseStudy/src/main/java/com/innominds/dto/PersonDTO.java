package com.innominds.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.gson.annotations.SerializedName;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude( Include.NON_NULL )
public class PersonDTO {

	@SerializedName( "_id" )
    private String id;
	
	@SerializedName("name")
	private String name;
	
	@SerializedName("age")
	private int age;
	
	 @SerializedName( "_rev" )
	    private String rev;

	public String getId() {
		return id;
	}

	

	public void setId(String id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getRev() {
		return rev;
	}

	public void setRev(String rev) {
		this.rev = rev;
	}

	@Override
	public String toString() {
		return "PersonDTO [id=" + id + ", name=" + name + ", age=" + age + ", rev=" + rev + "]";
	}
	 
	 
	
	
}
