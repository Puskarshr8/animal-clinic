package com.spring5app.animalclinic.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import com.spring5app.animalclinic.model.common.UserInfo;

@Entity
@Table(name = "User_Info")
public class User extends BaseEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5588574133820843439L;
	
	@Embedded
	@Valid
	private UserInfo  userInfo = new UserInfo();
	
	@Column(unique = true, length = 100, nullable = false)
	@Size(min=1)
	@Size(max=100)
	private String email;
	
	@Column(unique = true, length = 100, nullable = false)
	@Size(min=1)
	@Size(max=100)
	private String userName;
	
	@Column(length = 200)
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "userStatus", length = 40, nullable = false)
	private Status status = Status.INACTIVE;
	
	public User()
	{
		
	}
	
	public User(Long id)
	{
		super.setId(id);
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
