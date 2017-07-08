package com.lh.entity;
/**
 * ”√ªß
 * @author 
 *
 */
public class User{
	private String userName;
	private String password;
	private Role role;

	/**
	 * @param userName
	 * @param password
	 * @param role
	 */
	public User(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
	/**
	 * @return the role
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
