package com.capgemini.service;
import java.sql.ResultSet;
import com.capgemini.bean.BeanClass;
import com.capgemini.dao.AccountNotFoundException;
import com.capgemini.dao.DaoClass;
public class ServiceClass {
	@SuppressWarnings("unused")
	public ResultSet searchAccount(String phone) throws AccountNotFoundException, Exception 
	{
		DaoClass daoclass = new DaoClass();
		ResultSet accResult;
		return accResult = daoclass.searchAccount(phone);

	}
	public ResultSet searchAccount1(String accountId) throws AccountNotFoundException, Exception 
	{
		DaoClass daoclass = new DaoClass();
		@SuppressWarnings("unused")
		ResultSet accResult;
		return accResult = daoclass.searchAccount1(accountId);
	}
	@SuppressWarnings("unused")
	  public int withdraw(String accountId, int amount)
	{ 
		DaoClass daoclass = new  DaoClass();
	  int update;
	  try
	  { 
		  return DaoClass.withdraw(accountId, amount);
	  
	  }
	  catch(Exception ex) 
	  { 
		  System.out.println(ex.toString());
		  return 0;
		  }
	  }
	  public int addValues(String name, String phone, String type, int balance,String city) throws Exception
	  {
		  DaoClass daoclass = new DaoClass();
		  new BeanClass(name, phone, type, balance, city);
		  return daoclass.addValues(balance, name, type, phone, city);
	  }
	  public void transcations(int amount, String accountId) throws Exception
	  {   
	  DaoClass daoclass = new DaoClass();
	  daoclass.transactions(amount, accountId);
	  }
}
