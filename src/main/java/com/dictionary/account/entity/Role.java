package com.dictionary.account.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role 
{
	@Id
	@Column(name = "role_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int roleId;

	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	public int getRoleId() 
	{
		return roleId;
	}

	public void setRoleId(int roleId) 
	{
		this.roleId = roleId;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}
}
