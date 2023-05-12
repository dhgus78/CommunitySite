package com.keepgoing.website.entity;

import java.util.Date;

public class UserVoRole extends UserVo{
	private String role;
	
	public UserVoRole(){}

	public UserVoRole(String email, String name, String pwd, int id, Date update_date, Date append_date, String role) {
		super(email, name, pwd, id, update_date, append_date);
		this.role = role;
	}
	
	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserVoRole [getRole()=" + getRole() + ", getEmail()=" + getEmail() + ", getName()=" + getName()
				+ ", getPwd()=" + getPwd() + ", getId()=" + getId() + ", getUpdate_date()=" + getUpdate_date()
				+ ", getAppend_date()=" + getAppend_date() + ", getClass()=" + getClass() + "]";
	}

	

	
	
	

	
	
	
	
}
