package com.model;

import javax.ejb.Remote;

@Remote
public interface SignUpRemote {
	public void insert(String username,String email ,String password, String phno );
	public boolean login(String username,String password);

}