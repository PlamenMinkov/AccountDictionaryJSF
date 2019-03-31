package com.dictionary.account.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account_role")
public class AccountRole implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8225371423303522725L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "account_id")
	private int accountId;
	
	@Column(name = "role_id")
	private int roleId;
	
	public AccountRole() 
	{
	}
	
	public AccountRole(int accountId, int roleId) 
	{
		this.accountId = accountId;
		this.roleId = roleId;
	}

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
}
