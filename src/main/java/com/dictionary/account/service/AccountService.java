package com.dictionary.account.service;

import java.io.Serializable;
import java.util.List;
import java.util.TreeSet;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.dictionary.account.entity.Account;
import com.dictionary.account.entity.Address;

@ManagedBean(name="accountBean", eager = true)
@ApplicationScoped
public class AccountService implements Serializable 
{
	private static final long serialVersionUID = 1323191569671864920L;
	public static DatabaseOperations dbObj;
	
	public List<Account> allAccounts;

	public String name = "Naaame";

	public AccountService() 
	{
		setAllAccounts();
		
		System.out.println(allAccounts.toString());
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void saveAccountRecord() 
    {
    	HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        
    	String username = request.getParameter("studentSaveForm:username");
        String email = request.getParameter("studentSaveForm:email");
        String password = request.getParameter("studentSaveForm:password");
    	
    	String country = request.getParameter("studentSaveForm:country");
        String town = request.getParameter("studentSaveForm:town");
        String neighborhood = request.getParameter("studentSaveForm:neighborhood");
        String street = request.getParameter("studentSaveForm:street");
        int number = Integer.parseInt(request.getParameter("studentSaveForm:number"));
        
        String roles = request.getParameter("allRoles");
        TreeSet<String> rolesSet = new TreeSet<String>();
        
        if(roles.length() > 0)
        {
        	roles.replaceFirst(",", "");
        	String[] splitArr = roles.split(",");
        	
        	
        	for (int i = 0; i < splitArr.length; i++) 
        	{
				rolesSet.add(splitArr[i]);
			}
        }
    	
    	Address newAddress = new Address(country, town, street, neighborhood, number);
    	Account newAccount = new Account(username, email, password);
        
        dbObj = new DatabaseOperations();
        dbObj.addAccountInDb(newAccount, newAddress, rolesSet);
    }
	
	public void setAllAccounts() 
	{
		dbObj = new DatabaseOperations();
		allAccounts = dbObj.getAllAccounts();
	}
	
	public List<Account> getAllAccounts() 
	{
		return allAccounts;
	}
}
