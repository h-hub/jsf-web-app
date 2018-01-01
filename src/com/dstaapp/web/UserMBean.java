package com.dstaapp.web;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.dstaapp.model.User;
import com.dstaapp.session.MangeUserSessionBean;

@ManagedBean(name = "userMBean")
@SessionScoped 
public class UserMBean implements Serializable{
	
	String username;
	
	String email;
	
	String password;
	
	String role;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB 
	private MangeUserSessionBean userSessionBean;
	private User user;
	
	public UserMBean() {}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public String addUser() {
		System.out.println("###ADD###");
		userSessionBean.create(username,email,password,role);
		return "SAVED";
	}

	/**     * Returns list of customer objects to be displayed in the data table     * @return     */
	public List<User> getUsers() {
		return userSessionBean.retrieve();
	}
	/**     * Returns the selected Customer object     * @return     */
	public User getDetails() { //Can either do this for simplicity or fetch the details again from the        //database using the Customer ID        
		return user;
	}
	/**     * Action handler - user selects a customer record from the list     * (data table) to view/edit     * @param customer     * @return     */
	public String showDetails(User user) {
		this.user = user;
		return "DETAILS";
	}
	/**     * Action handler - update the changes in the Customer object to the     * database     * @return     */
	public String update() {
		System.out.println("###UPDATE###");
		user = userSessionBean.update(user);
		return "SAVED";
	}
	/**     * Action handler - goes to the Customer listing page     * @return     */
	public String list() {
		System.out.println("###LIST###");
		return "LIST";
	}
}