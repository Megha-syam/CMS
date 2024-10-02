package com.controller;

import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import com.model.SignUpRemote;

@ManagedBean(name = "login", eager = true)
@ViewScoped
public class login implements Serializable {


	private static final long serialVersionUID = 1L;

	@EJB(mappedName = "java:global/CMS/SignUpModel!com.model.SignUpRemote")
    private SignUpRemote sr;

	@NotNull(message="Username is Required!")
    private String username;
	@NotNull(message="Password is Required!")
    private String password;
	
    private String dialogMessage;

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDialogMessage() {
        return dialogMessage;
    }

    // Action method for login button
    public String callEjbLogin() {
        boolean loggedIn = sr.login(username, password);
        if (loggedIn) {
            //dialogMessage = "Successfully logged in";
        	FacesContext context = FacesContext.getCurrentInstance();
            HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
            session.setAttribute("username", username);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Successfully logged in"));
            return "Home"; // Redirect to Home.xhtml
        } 
//        else {
//            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid credentials", "Invalid username or password"));
//        }
        return null; // Stay on the same page
    }
}