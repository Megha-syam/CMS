package com.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.model.SignUpRemote;

@ManagedBean(name="sup",eager=true)
public class SignUp {
    
    @EJB(mappedName = "java:global/CMS/SignUpModel!com.model.SignUpRemote")
    SignUpRemote sr;

    @NotNull(message="Username is required!")
    @Size(min=3,max=15,message="Username must be between 3 and 15 characters!")
    private String username;
    
    @NotNull(message="Email is required!")
    @Pattern(regexp="^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",message="invalid Email!")
    private String email;
    
    @NotNull(message="Password is required!")
    @Pattern(
    	    regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%?&])[A-Za-z\\d@$!%?&]{8,}$",
    	    message = "Password must be 8 characters long and must contain at least 1 uppercase letter, 1 digit & 1 special character!"
    	)


    private String password;
    
    @NotNull(message = "Phone number is compulsory.")
    @Pattern(regexp = "\\d{10}", message = "Enter a 10-digit phone number.")
    private String phno;

    // Getters and Setters
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

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String ejbSignUp() {
        sr.insert(username, email, password, phno);
        return "Login";
      
    }
}