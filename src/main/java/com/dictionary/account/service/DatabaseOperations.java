package com.dictionary.account.service;

import javax.faces.context.FacesContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dictionary.account.entity.Account;
import com.dictionary.account.util.HibernateUtil;

public class DatabaseOperations {

	private static Transaction transObj;
	private static Session sessionObj = HibernateUtil.getSessionFactory().openSession();

	public void addStudentInDb(Account account) 
	{
		try 
		{
			transObj = sessionObj.beginTransaction();
			sessionObj.save(account);
			System.out.println("Account Record With username: " + account.getUsername() + " Is Successfully Created In Database");

			// XHTML Response Text
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("createdAccount", account.getAccountId());
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
}