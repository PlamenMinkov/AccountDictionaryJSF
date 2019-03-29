package com.dictionary.account.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import javax.faces.context.FacesContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dictionary.account.entity.Account;
import com.dictionary.account.entity.AccountRole;
import com.dictionary.account.entity.Address;
import com.dictionary.account.entity.Role;
import com.dictionary.account.util.HibernateUtil;

public class DatabaseOperations {

	private static Transaction transObj;
	private static Session sessionObj;
	
	public DatabaseOperations() 
	{
		if(sessionObj == null)
		{
			sessionObj = HibernateUtil.getSessionFactory().openSession();
		}	
	}
	
	public void addAddressInDb(Address address, int accountId) 
	{
		try 
		{
			address.setAccountId(accountId);
			
			transObj = sessionObj.beginTransaction();
			sessionObj.save(address);
		} 
		catch (Exception exceptionObj) 
		{
			exceptionObj.printStackTrace();
		} 
		finally 
		{
			transObj.commit();
		}
	}
	
	public void addAccountRoles(TreeSet<String> roles, int accountId) 
	{	
		for (Iterator<String> iterator = roles.iterator(); iterator.hasNext();) 
		{
			String roleId = (String) iterator.next();
			if(roleId.length() > 0)
			{
				try 
				{
					System.out.println("accId" + accountId + " roleId: " + roleId);
					AccountRole accountRole = new AccountRole(accountId, Integer.parseInt(roleId), true);
					transObj = sessionObj.beginTransaction();
					sessionObj.save(accountRole);
				} 
				catch (Exception exceptionObj) 
				{
					exceptionObj.printStackTrace();
				} 
				finally 
				{
					transObj.commit();
				}
			}		
		}
	}

	public void addAccountInDb(Account account, Address address, TreeSet<String> roles) 
	{
		String resultString = "";
		
		if(getAccountByUsername(account.getUsername()) == null)
		{
    		try 
    		{
				transObj = sessionObj.beginTransaction();
				sessionObj.save(account);

				resultString = "Account with username: " + account.getUsername() + "is created!";
    		} 
    		catch (Exception exceptionObj) 
    		{
    			exceptionObj.printStackTrace();
    		} 
    		finally 
    		{
    			transObj.commit();
    		}
    		
    		int accountId = getAccountByUsername(account.getUsername()).getAccountId();
    		
    		addAddressInDb(address, accountId);
    		addAccountRoles(roles, accountId);
		}
		else
		{
			resultString = "Username: " + account.getUsername() + " is used!";
		}
		
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("createdAccount", resultString);	
	}

	public void deleteAccountInDb(int delAccountId) 
	{
		try 
		{
			transObj = sessionObj.beginTransaction();
			Account accountId = (Account) sessionObj.load(Account.class, new Integer(delAccountId));
			sessionObj.delete(accountId);
			System.out.println("Student Record With Id: " + delAccountId + " Is Successfully Deleted From Database");

			// XHTML Response Text
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("deletedAccount",
					delAccountId);
		} 
		catch (Exception exceptionObj) 
		{
			exceptionObj.printStackTrace();
		} 
		finally 
		{
			transObj.commit();
		}
	}

	@SuppressWarnings({ "unchecked", "unused" })
	public Account getAccountById(int accountId) 
	{
		Account accountObj = new Account();
		
		try 
		{
			transObj = sessionObj.beginTransaction();
			Query queryObj = sessionObj.createQuery("from Account where id= :account_id").setInteger("account_id", accountId);
			accountObj = (Account) queryObj.uniqueResult();
			//particularStudentList = queryObj.list().get(0);

			// XHTML Response Text
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("findAccountById", accountId);
		} 
		catch (Exception exceptionObj) 
		{
			exceptionObj.printStackTrace();
		} 
		finally 
		{
			transObj.commit();
		}
		
		return accountObj;
	}
	
	@SuppressWarnings({ "unchecked", "unused" })
	public Account getAccountByUsername(String username) 
	{
		Account accountObj = null;
		
		try 
		{
			transObj = sessionObj.beginTransaction();
			Query queryObj = sessionObj.createQuery("from Account where username= :username").setString("username", username);
			
			if(queryObj.list().size() > 0)
			{
				accountObj = (Account) queryObj.list().get(0);
			}

			// XHTML Response Text
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("findAccountByUsername", username);
		} 
		catch (Exception exceptionObj) 
		{
			exceptionObj.printStackTrace();
		} 
		finally 
		{
			transObj.commit();
		}
		
		return accountObj;
	}

	public void updateAccountRecord(Account account) 
	{
		try 
		{
			transObj = sessionObj.beginTransaction();
			sessionObj.update(account);

			// XHTML Response Text
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("updatedAccount", "Success");
		} 
		catch (Exception exceptionObj) 
		{
			exceptionObj.printStackTrace();
		} 
		finally 
		{
			transObj.commit();
		}
	}
	
	public List<Account> getAllAccounts() 
	{
		List<Account> accountList = new ArrayList<Account>();
		
		try 
		{
			System.out.println(sessionObj);
			transObj = sessionObj.beginTransaction();
			Query queryObj = sessionObj.createQuery("from Account");
			
			for (int i = 0; i < queryObj.list().size(); i++) 
			{
				accountList.add((Account)queryObj.list().get(i));
			}
		} 
		catch (Exception exceptionObj) 
		{
			exceptionObj.printStackTrace();
		} 
		finally 
		{
			transObj.commit();
		}
		
		return accountList;
	}
	
	public List<Role> getAllRoles() 
	{
		List<Role> roletList = new ArrayList<Role>();
		
		try 
		{
			System.out.println(sessionObj);
			transObj = sessionObj.beginTransaction();
			Query queryObj = sessionObj.createQuery("from Role");
			
			for (int i = 0; i < queryObj.list().size(); i++) 
			{
				roletList.add((Role)queryObj.list().get(i));
			}
		} 
		catch (Exception exceptionObj) 
		{
			exceptionObj.printStackTrace();
		} 
		finally 
		{
			transObj.commit();
		}
		
		return roletList;
	}
}