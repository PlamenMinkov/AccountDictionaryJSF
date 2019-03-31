package com.dictionary.account.service;

import java.io.Serializable;
import java.util.List;
import java.util.TreeSet;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import com.dictionary.account.entity.Account;
import com.dictionary.account.entity.Address;
import com.dictionary.account.entity.Role;

@ManagedBean(name="accountBean", eager = true)
@SessionScoped
public class AccountService implements Serializable 
{
	private static final long serialVersionUID = 1323191569671864920L;
	public static DatabaseOperations dbObj;
	
	public List<Account> allAccounts;
	
	public Account accountForEdit;
	public String selectedRole;

	public AccountService() 
	{
		sortByActiveState(true);
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
        	roles.replaceFirst(",","");
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
		
		allAccounts.sort((accountOne, accountTwo) -> accountOne.compareTo(accountTwo));;
	}
	
	public List<Account> getAllAccounts() 
	{		
		return allAccounts;
	}
	
	public String getSelectedRole() 
	{
		return selectedRole;
	}

	public void setSelectedRole(String selectedRole) 
	{
		this.selectedRole = selectedRole;
	}
		
	public Account getAccountForEdit() 
	{
		return accountForEdit;
	}

	public void setAccountForEdit(Account accountForEdit) 
	{
		this.accountForEdit = accountForEdit;
	}

	
	public void sortByActiveState(boolean active)
	{
		setAllAccounts();
		
		for (int i = 0; i < allAccounts.size(); i++) 
		{
			if(allAccounts.get(i).getActive() != active)
			{
				allAccounts.remove(i--);
			}
		}
	}
	
	public void changeActiveState(Account account)
	{
		dbObj = new DatabaseOperations();
		dbObj.changeAccountActiveState(account, account.getActive());
		
		sortByActiveState(true);
	}
	
	public void deleteAccountRole(int accountId, int roleId)
	{
		dbObj = new DatabaseOperations();
		dbObj.deleteAccountRole(accountId, roleId);
		
		sortByActiveState(true);
	}
	
	public void filterByParams()
	{
		setAllAccounts();
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        
    	String username = request.getParameter("filterForm:username");
        String email = request.getParameter("filterForm:email");   	
    	String country = request.getParameter("filterForm:country");
        String town = request.getParameter("filterForm:town");
        String roles = request.getParameter("filterRoles");
        
        if(username.length() > 0)
        {
        	username = username.toLowerCase();
        	for (int i = 0; i < allAccounts.size(); i++) 
        	{
				if(!allAccounts.get(i).getUsername().toLowerCase().contains(username))
				{
					allAccounts.remove(i--);
				}
			}
        }
        if(email.length() > 0)
        {
        	email = email.toLowerCase();
        	for (int i = 0; i < allAccounts.size(); i++) 
        	{
				if(!allAccounts.get(i).getEmail().toLowerCase().contains(email))
				{
					allAccounts.remove(i--);
				}
			}
        }
        if(country.length() > 0)
        {
        	country = country.toLowerCase();
        	for (int i = 0; i < allAccounts.size(); i++) 
        	{
				if(!allAccounts.get(i).getAddress().getCountry().toLowerCase().contains(country))
				{
					allAccounts.remove(i--);
				}
			}
        }
        if(town.length() > 0)
        {
        	town = town.toLowerCase();
        	for (int i = 0; i < allAccounts.size(); i++) 
        	{
				if(!allAccounts.get(i).getAddress().getTown().toLowerCase().contains(town))
				{
					allAccounts.remove(i--);
				}
			}
        }
        
        if(roles.length() > 0)
        {
        	roles = roles.substring(1);
        	String[] splitArr = roles.split(",");        	
        	
        	for (int i = 0; i < splitArr.length; i++) 
        	{
        		for (int j = 0; j < allAccounts.size(); j++) 
            	{
        			List<Role> thisRoles = allAccounts.get(j).getRoles();
        			boolean contains = false;
        			
        			for (int k = 0; k < thisRoles.size(); k++) 
        			{
						if(thisRoles.get(i).getRoleId() == Integer.parseInt(splitArr[i]))
						{
							contains = true;
						}
					}

        			if(!contains)
        			{
        				allAccounts.remove(j--);
        			}
    			}
			}
        }
	}
	
	public void cleanFilter()
	{
		sortByActiveState(true);
	}
	
	public void editAccount(String username)
	{
		dbObj = new DatabaseOperations();
		HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        
        String email = request.getParameter("studentSaveForm:email");
    	
    	String country = request.getParameter("studentSaveForm:country");
        String town = request.getParameter("studentSaveForm:town");
        String neighborhood = request.getParameter("studentSaveForm:neighborhood");
        String street = request.getParameter("studentSaveForm:street");
        int number = Integer.parseInt(request.getParameter("studentSaveForm:number"));
        
        Account thisAccount = dbObj.getAccountByUsername(username);
        Address thisAddress = thisAccount.getAddress();
        
        thisAccount.setEmail(email);
        thisAddress.setCountry(country);
        thisAddress.setNeighborhood(neighborhood);
        thisAddress.setTown(town);
        thisAddress.setStreet(street);
        thisAddress.setNumber(number);
        
        dbObj.updateAddress(thisAddress);
        dbObj.updateAccountRecord(thisAccount);
	}
}
