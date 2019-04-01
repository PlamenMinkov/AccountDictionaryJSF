package com.dictionary.account.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.dictionary.account.service.DatabaseOperations;

@Entity
@Table(name = "account")
public class Account  implements java.io.Serializable, Comparable<Account>
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
	
	@Column(name = "active")
	private boolean active;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "account_id", referencedColumnName = "account_id")
	private Address address;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "account_role", joinColumns =
	{ @JoinColumn(name = "account_id", referencedColumnName = "account_id") }, inverseJoinColumns =
	{ @JoinColumn(name = "role_id", referencedColumnName = "role_id") })
	private List<Role> roles;
	
	public static DatabaseOperations dbObj;
    private static final long serialVersionUID = 1L;
    
    public Account() 
    {   	
    }
 
    public Account(String username, String email, String password) 
    {
        this.username = username;
        this.email = email;
        this.password = password;
        this.active = true;
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

	public boolean getActive() 
	{
		return active;
	}

	public void setActive(boolean active) 
	{
		this.active = active;
	}

	public Address getAddress() 
	{
		return address;
	}
	

	public List<Role> getRoles() 
	{
		return roles;
	}

	public void setRoles(List<Role> roles) 
	{
		this.roles = roles;
	}

	@Override
	public int compareTo(Account account) 
	{
		return this.getUsername().compareTo(account.getUsername());
	}
}
