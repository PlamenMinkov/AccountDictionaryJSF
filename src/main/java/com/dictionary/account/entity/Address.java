package com.dictionary.account.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "address")
public class Address implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3118414603056310015L;

	@Id
	@Column(name = "account_id")
	private int accountId;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "town")
	private String town;
	
	@Column(name = "neighborhood")
	private String neighborhood;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "number")
	private int number;
	
	public Address()
	{
	}
	
	public Address(String country, String town, String neighborhood, String street, int number)
	{
		this.country = country;
		this.town = town;
		this.neighborhood = neighborhood;
		this.street = street;
		this.number =  number;
	}

	public String getCountry() 
	{
		return country;
	}

	public void setCountry(String country) 
	{
		this.country = country;
	}

	public String getTown() 
	{
		return town;
	}

	public void setTown(String town) 
	{
		this.town = town;
	}

	public String getNeighborhood() 
	{
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) 
	{
		this.neighborhood = neighborhood;
	}

	public String getStreet() 
	{
		return street;
	}

	public void setStreet(String street) 
	{
		this.street = street;
	}

	public int getNumber() 
	{
		return number;
	}

	public void setNumber(int number) 
	{
		this.number = number;
	}
	
	public int getAccountId() 
	{
		return accountId;
	}

	public void setAccountId(int accountId2) 
	{
		this.accountId = accountId2;
	}
}
