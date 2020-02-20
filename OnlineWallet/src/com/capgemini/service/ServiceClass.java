package com.capgemini.service;

import java.sql.ResultSet;

import com.capgemini.bean.BeanClass;
import com.capgemini.dao.AccountNotFoundException;
import com.capgemini.dao.DaoClass;

public class ServiceClass {

	@SuppressWarnings("unused")
	
	public ResultSet searchAccount(String phone) throws AccountNotFoundException, Exception {
		DaoClass daoclass = new DaoClass();
		ResultSet accResult;
		return accResult = daoclass.searchAccount(phone);
	}
	public ResultSet searchAccount1(String accountId) throws AccountNotFoundException, Exception {
		DaoClass daoclass = new DaoClass();
		return daoclass.searchAccount(accountId);
	}


	public int addValues(String name, String phone, String type, int balance, String city) throws Exception {
		DaoClass daoclass = new DaoClass();
		new BeanClass(name, phone, type, balance, city);
		return daoclass.addValues(balance, name, type, phone, city);

	}

	public void fundTransfer(String accountId, String raccountid, int amount) throws Exception {
		DaoClass daoclass = new DaoClass();
		daoclass.fundTransfer(accountId, raccountid, amount);
	}


	public void transcations(int amount, String raccountid, String accountId) throws Exception {
		DaoClass daoclass = new DaoClass();
		daoclass.transactions(amount, raccountid, accountId);

	}

	public ResultSet details(String accountId) {
		DaoClass daoclass = new DaoClass();
		ResultSet accResult;
		try {
			accResult = daoclass.details(accountId);
			return accResult;
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}
}
