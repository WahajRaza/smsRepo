/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package services;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserCredential implements Serializable{
	private static final long serialVersionUID = 1L;
	
	String email;
	String name;
	
	Set<String> roles = new HashSet<String>();

	public UserCredential(String email, String name) {
		this.email = email;
		this.name = name;
	}

	public UserCredential() {
		this.email = "Anonymous";
		this.name = "Anonymous";
		roles.add("Anonymous");
	}

	public boolean isAnonymous() {
		return hasRole("Anonymous") || "Anonymous".equals(email);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean hasRole(String role){
		return roles.contains(role);
	}
	
	public void addRole(String role){
		roles.add(role);
	}

}
