/**
 * 
 */
package com.AccountAccess.Model;

/**
 * @author Amady
 *
 */
public class User {
	private Long id;

	private String login;

	private String password;

	
	public User() {
		super();
	}

	public User(Long id, String login, String password) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password + "]";
	}

}
