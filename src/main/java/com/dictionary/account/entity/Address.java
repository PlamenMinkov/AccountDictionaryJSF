package com.dictionary.account.entity;

import javax.faces.bean.ManagedBean;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@ManagedBean
@Entity
@Table(name = "address")
public class Address 
{
	@Id
	@Column(name = "address_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int addressId;
	
	@Column(name = "country")
	public String country;
	
	@Column(name = "town")
	public String town;
	
	@Column(name = "neighborhood")
	private String neighborhood;
	
	@Column(name = "street")
	private String street;
	
	@Column(name = "number")
	private int number;
	
	public int getAddressId() 
	{
		return addressId;
	}

	public void setAddressId(int addressId) 
	{
		this.addressId = addressId;
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
}
