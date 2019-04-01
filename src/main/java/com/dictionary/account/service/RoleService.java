package com.dictionary.account.service;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

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
	
	public void saveRole()
	{
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        
    	String name = request.getParameter("roleSaveForm:name");
        String description = request.getParameter("roleSaveForm:description");
        
        Role newRole = new Role(name, description);
        
        dbObj = new DatabaseOperations();
        dbObj.addRole(newRole);
        
        setAllRoles();
	}
	
	public void addRole()
	{
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        
    	String roleId = request.getParameter("roleId");
        String accountId = request.getParameter("accountId");
        
        System.out.println("account: " + accountId);
        System.out.println("role: " + roleId);
        
        dbObj = new DatabaseOperations();
        dbObj.addAccountRole(Integer.parseInt(roleId), Integer.parseInt(accountId));
        
        setAllRoles();
	}
}
