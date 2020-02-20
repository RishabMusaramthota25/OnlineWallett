package com.capgemini.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;
public class DaoClass {
	public ResultSet searchAccount(String phone) throws AccountNotFoundException,Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DbClass.getConnection1();
		String ins_str = "select * from account where phone=?";
		pstmt = con.prepareStatement(ins_str);
		pstmt.setString(1, phone);
		ResultSet accinfo = pstmt.executeQuery();
		if(accinfo.next())
		return accinfo;
		else{
			throw new AccountNotFoundException("Phone number not valid");
		}
	}
	public ResultSet searchAccount1(String accountId) throws AccountNotFoundException,Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DbClass.getConnection1();
		String ins_str = "select * from account where accountId=?";
		pstmt = con.prepareStatement(ins_str);
		pstmt.setString(1, accountId);
		ResultSet accinfo = pstmt.executeQuery();
		if(accinfo.next())
		return accinfo;
		else{
			throw new AccountNotFoundException("Account id not valid");
		}
	}
	static int a1 = 0;
	static int a2 = 0;
	public static int withdraw(String accountId, int amount) throws Exception {
		Connection con = null;
		PreparedStatement pstmt = null;
		con = DbClass.getConnection1();
		pstmt = con.prepareStatement("select balance from account where accountId=?");
		pstmt.setString(1, accountId);
		ResultSet rs = pstmt.executeQuery();
		if (rs.next())
			a1 = rs.getInt(1) - amount;
		if(a1<0)
			throw new LowBalance("Balance is less than amount");
		pstmt = con.prepareStatement("update account set balance=? where accountId=?");
		pstmt.setInt(1, a1);
		pstmt.setString(2, accountId);
		int update = pstmt.executeUpdate();
		if (update > 0) {

			pstmt = con.prepareStatement("select * from account where accountId=?");
			pstmt.setString(1, accountId);
			ResultSet rs2 = pstmt.executeQuery();
			while (rs2.next())
				System.out.println("Name " + rs2.getString(1) + "\nAccount type " + rs2.getString(2) + "\nBalance "
						+ rs2.getInt(3) + "\nPhone " + rs2.getString(4) + "\nCity " + rs2.getString(5) + "\nAccountId "
						+ rs2.getString(6));
		}
		return update;
	}
	
	  public int addValues(int balance, String name, String type, String phone,String city) throws Exception 
	  { 
		  Connection con = null;
		  int x = Math.abs(new Random().nextInt());
		  PreparedStatement pstmt = null;
	  con = DbClass.getConnection1(); String ins_str ="insert into account values(?,?,?,?,?,?)";
	  pstmt = con.prepareStatement(ins_str);
	  pstmt.setString(1, name); 
	  pstmt.setString(2,type); 
	  pstmt.setDouble(3, balance);
	  pstmt.setString(4, phone);
	  pstmt.setString(5, city);
	  pstmt.setString(6, Integer.toString(x));
	  int updateCount = pstmt.executeUpdate();
	  con.close(); 
	  return updateCount;
	  }
	  public void transactions(int amount, String accountId) throws Exception {
	  Connection con = null; PreparedStatement pstmt = null; con =DbClass.getConnection1();
	  String ins_str ="insert into transaction values(?,?,?,?)";
	  pstmt = con.prepareStatement(ins_str);
	  pstmt.setInt(1, amount);
	  pstmt.setInt(2, 0);
	  pstmt.setInt(3, 0);
	  pstmt.setString(4, accountId);
	  pstmt.executeUpdate();
	  con.close();
	  }
}
