package com.keepgoing.website.entity;

import java.util.Date;

public class UserVo {
	private String email;
	private String name;
	private String pwd;
	private int id;
	private Date update_date;
	private Date append_date;
	
	public UserVo(){}

	public UserVo(String email, String name, String pwd, int id, Date update_date, Date append_date) {
		super();
		this.email = email;
		this.name = name;
		this.pwd = pwd;
		this.id = id;
		this.update_date = update_date;
		this.append_date = append_date;
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

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public Date getAppend_date() {
		return append_date;
	}

	public void setAppend_date(Date append_date) {
		this.append_date = append_date;
	}

	@Override
	public String toString() {
		return "UserVo [email=" + email + ", name=" + name + ", pwd=" + pwd + ", id=" + id + ", update_date="
				+ update_date + ", append_date=" + append_date + "]";
	}

	
	
	
	
}
