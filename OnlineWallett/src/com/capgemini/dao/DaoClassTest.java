package com.capgemini.dao;
import java.sql.ResultSet;
import static org.junit.Assert.*;
import org.junit.Test;

public class DaoClassTest {
DaoClass dao=new DaoClass();
/*	@SuppressWarnings("unused")
	@Test
public void withdraw() throws AccountNotFoundException, Exception{
		int bal=0;
		ResultSet beanObj=dao.searchAccount("9000");
		if(beanObj.next())
		{
		bal=beanObj.getInt(3);
		}
		int updatedBal=DaoClass.withdraw("9000", 300);
		assertNotNull(updatedBal);
}*/
@SuppressWarnings("unused")
@Test
public void withdraw() throws AccountNotFoundException, Exception{
	int bal=0;
	ResultSet beanObj=dao.searchAccount("9000");
	if(beanObj.next())
	{
	bal=beanObj.getInt(3);
	}
	int updatedBal=DaoClass.withdraw("9000", 300);
	assertNotNull(updatedBal);
}
}
