package com.model;

import javax.ejb.Remote;

@Remote
public interface SendRemote {
	
	public void insert(String sname,String snumber,String saddress,String spincode,String scity,String rname,String rnumber,
			String raddress,String rpincode,String rcity,String ctype,double cweight );
	
	default double calculateCost(double weight) {
		return weight*33.0;
	}

}