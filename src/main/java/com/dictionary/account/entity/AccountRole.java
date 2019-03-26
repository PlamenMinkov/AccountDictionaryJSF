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
@Table(name = "account_role")
public class AccountRole 
{
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "account_id")
	private int accountId;
	
	@Column(name = "role_id")
	private int roleId;
	
	@Column(name = "active")
	private boolean active;

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public int getAccount_id() 
	{
		return accountId;
	}

	public void setAccount_id(int account_id) 
	{
		this.accountId = account_id;
	}

	public int getRole_id() 
	{
		return roleId;
	}

	public void setRole_id(int role_id) 
	{
		this.roleId = role_id;
	}

	public boolean isActive() 
	{
		return active;
	}

	public void setActive(boolean active) 
	{
		this.active = active;
	}
}
