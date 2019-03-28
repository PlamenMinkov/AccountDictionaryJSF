package com.dictionary.account.entity;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;

import com.dictionary.account.service.DatabaseOperations;

@ManagedBean(name="account")
@SessionScoped
@Entity
@Table(name = "account")
public class Account  implements java.io.Serializable
{
	@Id
	@Column(name = "account_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int accountId;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id", referencedColumnName = "account_id")
	private Address address;
	
	public static DatabaseOperations dbObj;
    private static final long serialVersionUID = 1L;
    
    public Account() 
    {
    }
    
    public Account(int id) 
    {
        this.accountId = id;
    }
 
    public Account(int id, String username, String email, String password) 
    {
        this.accountId = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
	
	public int getAccountId() 
	{
		return accountId;
	}
	
	public void setAccountId(int accountId) 
	{
		this.accountId = accountId;
	}
	
	public String getUsername() 
	{
		return username;
	}
	
	public void setUsername(String username) 
	{
		this.username = username;
	}
	
	public String getEmail() 
	{
		return email;
	}
	
	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	public String getPassword() 
	{
		return password;
	}
	
	public void setPassword(String password) 
	{
		this.password = password;
	}
	
	public void setAddress(Address address) 
	{
		this.address = address;
	}
	
	public Address getAddress() 
	{
		return address;
	}
	
	// Method To Add New Student Details In Database
    public void saveAccountRecord() 
    {
    	HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String country = request.getParameter("studentSaveForm:country");
        String town = request.getParameter("studentSaveForm:town");
        String neighborhood = request.getParameter("studentSaveForm:neighborhood");
        String street = request.getParameter("studentSaveForm:street");
        int number = Integer.parseInt(request.getParameter("studentSaveForm:number"));
    	
    	Address newAddress = new Address(country, town, street, neighborhood, number);
        
        dbObj = new DatabaseOperations();
        dbObj.addAccountInDb(this, newAddress);
    }
 
    // Method To Delete A Particular Student Record From The Database
	public void deleteAccountRecord() 
	{
		System.out.println("Calling deleteStudentRecord() Method To Delete Student Record");
		dbObj = new DatabaseOperations();
		dbObj.deleteAccountInDb(accountId);
	}

	// Method To Fetch Particular Student Details From The Database
	public Account getAccountDetailsById() 
	{
		dbObj = new DatabaseOperations();
		
		return dbObj.getAccountById(accountId);
	}

	// Method To Update Particular Student Details In Database
	public void updateAccountDetails() 
	{
		System.out.println("Calling updateStudentDetails() Method To Update Student Record");
		dbObj = new DatabaseOperations();
		dbObj.updateAccountRecord(this);
	}
}
