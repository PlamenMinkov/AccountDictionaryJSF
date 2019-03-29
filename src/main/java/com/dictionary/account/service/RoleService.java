package com.dictionary.account.service;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import com.dictionary.account.entity.Account;
import com.dictionary.account.entity.Role;

@ManagedBean(name="roleBean", eager = true)
@ApplicationScoped
public class RoleService implements Serializable 
{
	private static final long serialVersionUID = 1323191569671864920L;
	public static DatabaseOperations dbObj;
	
	public List<Role> allRoles;

	public RoleService() 
	{
		setAllRoles();
	}
	
	public void setAllRoles() 
	{
		dbObj = new DatabaseOperations();
		allRoles = dbObj.getAllRoles();
	}
	
	public List<Role> getAllRoles() 
	{
		return allRoles;
	}
}
