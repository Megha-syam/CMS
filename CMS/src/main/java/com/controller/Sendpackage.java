package com.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.model.SendRemote;

@ManagedBean(name="send" ,eager=true)
public class Sendpackage {
	
	
	@EJB(mappedName="java:global/CMS/SendModel!com.model.SendRemote")
	SendRemote sp;

	@NotNull(message="Sender Name is Required!")
	
	private String sname;
	
	@NotNull(message="Sender Phone number is Required!")
	@Pattern(regexp = "\\d{10}", message = "Enter a 10-digit phone number.")
	private String snumber;
	
	@NotNull(message="Sender Pickup Adress is Required!")
	
	private String saddress;
	
	@NotNull(message="Sender Pincode is Required!")
	@Pattern(regexp = "\\d{6}", message = "Enter a 6 digit pincode .")
	private String spincode;
	
	@NotNull(message="Sender City Name is Requitred!")
	private String scity;
	
	
	@NotNull(message="Receiver name is Required!")
	private String rname;
	
	@NotNull(message="Receiver Phone number is Required!")
	private String rnumber;
	
	@NotNull(message="Receiver Address is Required!")
	
	private String raddress;
	
	@NotNull(message="Receiver pincode is Required!")
	@Pattern(regexp = "\\d{6}", message = "Enter a 6 digit pincode.")
	private String rpincode;
	
	@NotNull(message="Receiver City name is Required!")
	private String rcity;
	
	
	@NotNull(message="Package Type is Required!")
	private String ctype;
	
	@NotNull(message="Package Weight is Required in Kilo Grams!")
	@DecimalMin(value = "0.0", inclusive = true, message = "Value must be greater than  0.0")
    @DecimalMax(value = "200.0", inclusive = true, message = "Value must be less than or equal to 100.0")
	private double cweight;
	


	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSnumber() {
		return snumber;
	}

	public void setSnumber(String snumber) {
		this.snumber = snumber;
	}

	public String getSaddress() {
		return saddress;
	}

	public void setSaddress(String saddress) {
		this.saddress = saddress;
	}

	public String getSpincode() {
		return spincode;
	}

	public void setSpincode(String spincode) {
		this.spincode = spincode;
	}

	public String getScity() {
		return scity;
	}

	public void setScity(String scity) {
		this.scity = scity;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}


	public String getRnumber() {
		return rnumber;
	}

	public void setRnumber(String rnumber) {
		this.rnumber = rnumber;
	}

	public String getRaddress() {
		return raddress;
	}

	public void setRaddress(String raddress) {
		this.raddress = raddress;
	}

	public String getRpincode() {
		return rpincode;
	}

	public void setRpincode(String rpincode) {
		this.rpincode = rpincode;
	}

	public String getRcity() {
		return rcity;
	}
	public void setRcity(String rcity) {
		this.rcity = rcity;
	}

	public String getCtype() {
		return ctype;
	}


	public void setCtype(String ctype) {
		this.ctype = ctype;
	}


	public double getCweight() {
		return cweight;
	}


	public void setCweight(double cweight) {
		this.cweight = cweight;
	}


	public String ejbsend() {
		FacesContext context = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) context.getExternalContext().getSession(false);
        String currentUsername = (String) session.getAttribute("username");
        sp.insert(sname,snumber,saddress,spincode,scity,rname,rnumber,raddress,rpincode,rcity,ctype,cweight);
        return "orders.jsf";
      
    }
	
	
}